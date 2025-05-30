package co.edu.unbosque.repository;

import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

import co.edu.unbosque.entity.Transaccion;


@Repository
public interface TransaccionRepository extends CrudRepository<Transaccion, Long> {

}
