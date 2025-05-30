package co.edu.unbosque.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.edu.unbosque.entity.Auditoria;

@Repository
public interface AuditoriaRepository extends CrudRepository<Auditoria, Long> {

}
