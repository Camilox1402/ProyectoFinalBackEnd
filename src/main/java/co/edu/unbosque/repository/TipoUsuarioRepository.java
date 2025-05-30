package co.edu.unbosque.repository;

import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

import co.edu.unbosque.entity.TipoUsuario;


@Repository
public interface TipoUsuarioRepository extends CrudRepository<TipoUsuario, Long> {

}
