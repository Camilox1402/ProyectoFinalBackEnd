package co.edu.unbosque.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

/**
 * The persistent class for the usuario database table.
 * 
 */
@Data
@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;
	
	@Column(name="clave_usrio")
	private String claveUsrio;

	@Column(name="correo_usuario")
	private String correoUsuario;

	private byte estado;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fcha_ultma_clave")
	private Date fchaUltmaClave;

	@Column(name="id_tipo_usuario")
	private String idTipoUsuario;

	private int intentos;

	@Column(name="login_usrio")
	private String loginUsrio;


}