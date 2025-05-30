package co.edu.unbosque.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.edu.unbosque.entity.Categoria;

@Repository
public interface CategoriaRepository extends CrudRepository<Categoria, Long> {

}
