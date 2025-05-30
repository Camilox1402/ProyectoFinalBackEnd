package co.edu.unbosque.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.edu.unbosque.entity.DetalleVenta;


@Repository
public interface DetalleVentaRepository extends CrudRepository<DetalleVenta, Long> {

}