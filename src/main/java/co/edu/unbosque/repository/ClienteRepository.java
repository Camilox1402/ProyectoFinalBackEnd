package co.edu.unbosque.repository;


import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.edu.unbosque.entity.Cliente;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long> {
	
	Optional<Cliente> findByCorreoCliente(String correoCliente);

}