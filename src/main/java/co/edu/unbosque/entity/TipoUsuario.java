package co.edu.unbosque.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.*;
import lombok.Data;


/**
 * The persistent class for the tipo_usuario database table.
 * 
 */
@Data
@Entity
@Table(name = "tipo_usuario", uniqueConstraints = @UniqueConstraint(columnNames = "id"))
public class TipoUsuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	private String descripcion;

	private byte estado;


}