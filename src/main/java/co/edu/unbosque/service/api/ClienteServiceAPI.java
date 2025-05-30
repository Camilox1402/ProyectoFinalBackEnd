package co.edu.unbosque.service.api;

import co.edu.unbosque.utils.GenericServiceAPI;

import java.util.Optional;

import co.edu.unbosque.entity.Cliente;

public interface ClienteServiceAPI extends GenericServiceAPI<Cliente, Long>  {
	
	Optional <Cliente>findByCorreoCliente(String correoCliente);
	

  
}
