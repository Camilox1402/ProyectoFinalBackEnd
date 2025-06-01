package co.edu.unbosque.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import jakarta.validation.constraints.Email;

@Data
public class LoginRequest {
	
	@NotNull(message = "El correo no puede ser nulo")
	@NotBlank(message = "El correo es obligatorio")
	@Email(message = "El correo debe tener un formato valido")
	@JsonProperty("correo")
	private String correoUsuario;
	
	@NotNull(message = "La clave no puede ser nula")
	@NotBlank(message = "La clave es obligatoria")
	@JsonProperty("clave")
	private String clave;
	

}
