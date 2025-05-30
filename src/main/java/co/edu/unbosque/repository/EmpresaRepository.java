package co.edu.unbosque.repository;

import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

import co.edu.unbosque.entity.Empresa;


@Repository
public interface EmpresaRepository extends CrudRepository<Empresa, Long> {

}
