package co.edu.unbosque.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.*;
import lombok.Data;



/**
 * The persistent class for the cliente database table.
 * 
 */
@Data
@Entity
@Table(name = "cliente", uniqueConstraints = @UniqueConstraint(columnNames = "id"))
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name="correo_cliente")
	private String correoCliente;

	@Column(name="direccion_cliente")
	private String direccionCliente;

	private byte estado;

	@Column(name="nombre_cliente")
	private String nombreCliente;

	private String telefono;

}