package co.edu.unbosque.repository;

import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

import co.edu.unbosque.entity.MetodoPago;

@Repository
public interface MetodoPagoRepository extends CrudRepository<MetodoPago, Long> {

}
