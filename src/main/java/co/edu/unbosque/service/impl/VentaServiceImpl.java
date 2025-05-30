package co.edu.unbosque.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import co.edu.unbosque.entity.Usuario;
import co.edu.unbosque.entity.Venta;
import co.edu.unbosque.repository.VentaRepository;
import co.edu.unbosque.service.api.VentaServiceAPI;
import co.edu.unbosque.utils.GenericServiceImpl;


@Service
public class VentaServiceImpl extends GenericServiceImpl<Venta, Long> implements VentaServiceAPI {

	@Autowired
	private VentaRepository ventaRepository;

	@Override
	public CrudRepository<Venta, Long> getDao() {
		return ventaRepository;
	}
	 public Venta update(Venta ventaActualizada) {
	        Optional<Venta> ventaExistenteOpt = ventaRepository.findById(ventaActualizada.getId());
	        
	        if (ventaExistenteOpt.isPresent()) {
	            Venta ventaExistente = ventaExistenteOpt.get();
	            
	            ventaExistente.setEstado(ventaActualizada.getEstado());
	            ventaExistente.setFechaVenta(ventaActualizada.getFechaVenta());
	            ventaExistente.setIdCliente(ventaActualizada.getIdCliente());
	            ventaExistente.setValorDscto(ventaActualizada.getValorDscto());
	            ventaExistente.setValorIva(ventaActualizada.getValorIva());
	            ventaExistente.setValorVenta(ventaActualizada.getValorVenta());

	            return ventaRepository.save(ventaExistente);
	        } else {
	            return null;
	        }
	    }
}
