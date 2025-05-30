package co.edu.unbosque.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.*;
import lombok.Data;


/**
 * The persistent class for the metodo_pago database table.
 * 
 */
@Data
@Entity
@Table(name = "metodo_pago", uniqueConstraints = @UniqueConstraint(columnNames = "id"))
public class MetodoPago implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	private String descripcion;

	private byte estado;

}