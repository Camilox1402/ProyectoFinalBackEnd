package co.edu.unbosque.service.api;

import co.edu.unbosque.utils.GenericServiceAPI;

import java.util.Optional;

import co.edu.unbosque.entity.Usuario;
 
public interface UsuarioServiceAPI extends GenericServiceAPI<Usuario, Long>  {
	
	Optional <Usuario>findByCorreoUsuario(String correoUsario);
	Usuario update(Usuario usuario);
  
}
