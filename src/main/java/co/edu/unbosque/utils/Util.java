package co.edu.unbosque.utils;

import java.math.BigDecimal;

import co.edu.unbosque.service.impl.ProductoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import co.edu.unbosque.entity.Categoria;
import co.edu.unbosque.entity.Parametro;
import co.edu.unbosque.entity.Producto;
import co.edu.unbosque.entity.RawgGame;
import co.edu.unbosque.entity.RawgGameResponse;
import co.edu.unbosque.repository.ProductoRepository;
import co.edu.unbosque.service.api.CategoriaServiceAPI;
import co.edu.unbosque.service.api.ParametroServiceAPI;

@Component
public class Util {
	
    @Autowired
	private  ParametroServiceAPI parametroServiceAPI;
    @Autowired
    private ProductoRepository productoRepository;
    
    @Autowired
    private CategoriaServiceAPI categoriaServiceAPI;
    
    @Autowired
    private RawgIntegrationService rawgIntegrationService;

    public CrudRepository<Producto, Long> getDao() {
        return productoRepository;
    }

	
	private final Long ID_PARAMETRO_IVA = 6L;
	private final Long ID_PARAMETRO_DESCUENTO = 7L;

	
	public double calcularIVA(double precio) {
		
		double valorPar = parametroServiceAPI.get(ID_PARAMETRO_IVA).getValorNumero();
		double valorIVA = valorPar/100.0; 
		
		return (valorIVA*precio);
	}
	
	public double calcularDescuento(double valor) {
		double descuentoPar = parametroServiceAPI.get(ID_PARAMETRO_DESCUENTO).getValorNumero();
		double descuento = descuentoPar/100.0; 
		return (valor*(descuento));
	}
	public double calcularValor(double valorIVA, double valorDescuento, double valor) {
		return valor+valorIVA-valorDescuento;
	}
	

    public Producto saveWithDetails(Producto producto) {

        if ((producto.getFotoProducto() == null || producto.getFotoProducto().isEmpty())) {
            RawgGame gameData = rawgIntegrationService.fetchGameData(producto.getNombre());
            
            if (gameData != null) {
                producto.setFotoProducto(gameData.getBackground_image());
                producto.setReferencia(gameData.getSlug());
            } else {
                System.out.println("No se encontraron resultados en RAWG para: " + producto.getNombre());
            }
        }

        if (producto.getCategoria() != null && producto.getCategoria().getId() != null) {
            Categoria categoria = categoriaServiceAPI.get(producto.getCategoria().getId());
            producto.setCategoria(categoria);
        }

        return productoRepository.save(producto); 
    }


}
