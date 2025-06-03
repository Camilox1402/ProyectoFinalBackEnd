package co.edu.unbosque.repository;

import co.edu.unbosque.entity.Usuario;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	Optional <Usuario>findByCorreoUsuario(String correoUsario);
	

}