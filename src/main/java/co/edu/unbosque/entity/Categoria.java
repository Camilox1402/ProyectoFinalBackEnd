package co.edu.unbosque.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.*;
import lombok.Data;


/**
 * The persistent class for the categoria database table.
 * 
 */
@Data
@Entity
@Table(name = "categoria", uniqueConstraints = @UniqueConstraint(columnNames = "nombre"))
public class Categoria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	
	private Long id;

	private byte estado;
	
	@Column(name="nombre")
	private String nombre;

}