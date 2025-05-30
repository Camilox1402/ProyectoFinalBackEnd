package co.edu.unbosque.service.impl;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import co.edu.unbosque.entity.Producto;
import co.edu.unbosque.repository.ProductoRepository;
import co.edu.unbosque.service.api.ProductoServiceAPI;
import co.edu.unbosque.utils.GenericServiceImpl;



@Service
public class ProductoServiceImpl extends GenericServiceImpl<Producto, Long> implements ProductoServiceAPI {

	@Autowired
	private ProductoRepository productoRepository;

	@Override
	public CrudRepository<Producto, Long> getDao() {
		return productoRepository;
	}
}
