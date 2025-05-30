package co.edu.unbosque.controller;
import co.edu.unbosque.entity.DetalleVenta;

import co.edu.unbosque.entity.ErrorResponse;
import co.edu.unbosque.entity.ItemCompra;
import co.edu.unbosque.entity.Producto;

import co.edu.unbosque.entity.Venta;


import co.edu.unbosque.entity.VentaRequest;
import co.edu.unbosque.service.api.DetalleVentaServiceAPI;
import co.edu.unbosque.service.api.ParametroServiceAPI;
import co.edu.unbosque.service.api.ProductoServiceAPI;

import co.edu.unbosque.service.api.VentaServiceAPI;
import co.edu.unbosque.utils.GenericServiceImpl;
import co.edu.unbosque.utils.ResourceNotFoundException;
import co.edu.unbosque.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import java.util.List;


//@CrossOrigin(origins = "http://localhost:8181",maxAge = 3600)
@RestController
@RequestMapping("/venta")
public class VentaRestController {

	private final GenericServiceImpl<Venta, Long> ventaGenericService;
	@Autowired
	private VentaServiceAPI ventaServiceAPI;
	
	@Autowired
	private ProductoServiceAPI productoServiceAPI;
	
	@Autowired
	private DetalleVentaServiceAPI detalleVentaServiceAPI;
	
	@Autowired
	private ParametroServiceAPI parametroServiceAPI;
	
    @Autowired
    private Util util;
    
    private final long ID_PARAMETRO_MAXCOMPRASDIA = 1;

    @Autowired
    VentaRestController(@Qualifier("ventaServiceImpl") GenericServiceImpl<Venta, Long> ventaGenericService) {
    	this.ventaGenericService = ventaGenericService;    }


    @PostMapping("/procesarVentaCompleta")
    public ResponseEntity<?> procesoVenta(@RequestBody VentaRequest request) {
    	        
        List<Producto> productos = productoServiceAPI.getAll();
        List<String> productosSinStock = new ArrayList<>();
        
        for(ItemCompra i : request.getItems()) {
            for(Producto p : productos) {
                if(i.getIdProducto() == p.getId() && p.getExistencia() < i.getCantidad()) {
                    productosSinStock.add(p.getDescripcion());
                }
            }
        }
        
        if(!productosSinStock.isEmpty()) {
            String mensajeError = "No hay suficiente stock para los productos: " + String.join(", ", productosSinStock);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("STOCK_INSUFICIENTE", mensajeError));
        }
        
        LocalDate fechaActual = LocalDate.now();
        List<Venta> ventas = ventaServiceAPI.getAll();
        List<DetalleVenta> detallesDeVenta = detalleVentaServiceAPI.getAll();
        int contadorVentasHoy = 0;
        
        for(Venta venta : ventas) {
        	LocalDate fechaVenta = new java.util.Date(venta.getFechaVenta().getTime())
                    .toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();

            


            if(request.getIdCliente() == venta.getIdCliente() && fechaActual.equals(fechaVenta)) {

                for(DetalleVenta d : detallesDeVenta) {
                    if(venta.getId() == d.getIdVenta()) {
                        contadorVentasHoy += d.getCantComp();
                    }
                }
            }
        }
        
        int maxComprasDia = parametroServiceAPI.get(ID_PARAMETRO_MAXCOMPRASDIA).getValorNumero();
        if(contadorVentasHoy >= maxComprasDia) {
            String mensajeError = "El cliente ha alcanzado el límite de " + maxComprasDia + " compras diarias";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("LIMITE_COMPRAS", mensajeError));
        }
        
        try {
            Venta ventaNueva = new Venta();
            ventaNueva.setEstado((byte) 0);
            ventaNueva.setFechaVenta(new Date());
            ventaNueva.setIdCliente(request.getIdCliente());
            ventaNueva.setValorDscto(BigDecimal.ZERO);
            ventaNueva.setValorIva(BigDecimal.ZERO);
            ventaNueva.setValorVenta(BigDecimal.ZERO);
            
            Venta ventaCreada = ventaServiceAPI.save(ventaNueva);
            long idVenta = ventaCreada.getId();
            
            double[] valores = crearDetalles(request.getItems(), productos, idVenta);
            
            ventaCreada.setValorIva(BigDecimal.valueOf(valores[0]));
            ventaCreada.setValorDscto(BigDecimal.valueOf(valores[1]));
            ventaCreada.setValorVenta(BigDecimal.valueOf(valores[2]));
            
            ventaServiceAPI.update(ventaCreada);
            
            
            return ResponseEntity.ok(ventaCreada);
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("ERROR_PROCESO", "Ocurrió un error al procesar la venta: " + e.getMessage()));
        }
    }
    
	public double[] crearDetalles(ArrayList<ItemCompra> requestProductos, List<Producto> productos, long idVenta) {
		
		double[] valores = new double[3]; 
		valores[0] = 0;
		valores[1] = 0;
		valores[2] = 0;
		//1 iva total
		//2 descuento total
		//3 valor
		
		ArrayList<DetalleVenta> detalles;
		double valorTotal;
		
		for(Producto p : productos ) {
			for(ItemCompra i : requestProductos) {
				if(p.getId() == i.getIdProducto()) { 
					DetalleVenta detalle = new DetalleVenta();
					detalle.setCantComp(i.getCantidad());
					detalle.setIdProducto((short) p.getId().longValue());	//para poder hacer el cast de Long no primitivo
					detalle.setIdVenta((short) idVenta);
					
					double valorProducto = p.getPrecioVentaActual().doubleValue();

					double valorIVA;
					
					if(p.getTieneIva() == 1 ) {
						valorIVA = util.calcularIVA(valorProducto);
					}else {
						valorIVA = 0;
					}
					double valorDscto;
					if(p.getTieneDescuento() == 1) {
						valorDscto = util.calcularDescuento(valorProducto);
					}else {
						valorDscto = 0;
					}

										
					double valorUnitario = util.calcularValor(valorIVA, valorDscto, valorProducto);
					
					valorIVA = valorIVA*i.getCantidad();
					valorDscto = valorDscto*i.getCantidad();
					
					detalle.setCantComp(i.getCantidad());
					detalle.setIdProducto((short) p.getId().longValue());
					detalle.setIdVenta((int) idVenta);
					
					detalle.setValorDscto(BigDecimal.valueOf(valorDscto));
					detalle.setValorIva(BigDecimal.valueOf(valorIVA));
					detalle.setValorUnit(BigDecimal.valueOf(valorUnitario));
					
					valorTotal = util.calcularValor(valorIVA, valorDscto, p.getPrecioVentaActual().doubleValue());
					
					valores[0] = valores[0] + valorIVA;
					valores[1] = valores[1] + valorDscto;
					valores[2] = valores[2] + valorTotal;
					
					detalleVentaServiceAPI.save(detalle);
					
					p.setExistencia(p.getExistencia()-detalle.getCantComp());
					
					productoServiceAPI.save(p);
				}	
			}
		}
		return valores;
	}


	@GetMapping(value = "/getAll")
//ResponseEntity List<Venta> getAll(){
	public List<Venta> getAll() {
		return ventaServiceAPI.getAll();
	}

	@PostMapping(value = "/saveVenta")
	public ResponseEntity<Venta> save(@RequestBody Venta venta) {
		Venta obj = ventaServiceAPI.save(venta);
		return new ResponseEntity<Venta>(obj, HttpStatus.OK); // 200
	}

	@GetMapping(value = "/findRecord/{id}")
	public ResponseEntity<Venta> getVentaoById(@PathVariable Long id) throws ResourceNotFoundException {
		Venta venta = ventaServiceAPI.get(id);
		if (venta == null) {
			new ResourceNotFoundException("Record not found for <Venta>" + id);
		}
		return ResponseEntity.ok().body(venta);
	}

	@DeleteMapping(value = "/deleteVenta/{id}")
	public ResponseEntity<Venta> delete(@PathVariable Long id) {
		Venta venta = ventaServiceAPI.get(id);
		if (venta != null) {
			ventaServiceAPI.delete(id);
		} else {
			return new ResponseEntity<Venta>(venta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Venta>(venta, HttpStatus.OK);
	}
}