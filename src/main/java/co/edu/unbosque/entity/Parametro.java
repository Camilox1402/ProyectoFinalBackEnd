package co.edu.unbosque.entity;

import java.io.Serializable;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;


/**
 * The persistent class for the parametro database table.
 * 
 */
@Data
@Entity
@Table(name = "parametro", uniqueConstraints = @UniqueConstraint(columnNames = "id"))
public class Parametro implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	private String descripcion;

	private byte estado;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_final")
	private Date fechaFinal;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_inicial")
	private Date fechaInicial;

	@Column(name="valor_numero")
	private int valorNumero;

	@Column(name="valor_texto")
	private String valorTexto;

}