package co.edu.unbosque.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.*;
import lombok.Data;


/**
 * The persistent class for the empresa database table.
 * 
 */
@Data
@Entity
@Table(name = "empresa", uniqueConstraints = @UniqueConstraint(columnNames = "id"))
public class Empresa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name="correo_electronico")
	private String correoElectronico;

	private String direccion;

	private byte estado;

	@Column(name="razon_social")
	private String razonSocial;

	private String telefono;


}