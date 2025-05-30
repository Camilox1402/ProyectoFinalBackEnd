package co.edu.unbosque.repository;

import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

import co.edu.unbosque.entity.Venta;


@Repository
public interface VentaRepository extends CrudRepository<Venta, Long> {

}
