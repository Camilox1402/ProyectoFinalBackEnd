package co.edu.unbosque.repository;

import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

import co.edu.unbosque.entity.Parametro;


@Repository
public interface ParametroRepository extends CrudRepository<Parametro, Long> {

	Parametro findById(int id);

}
