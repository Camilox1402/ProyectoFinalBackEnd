package co.edu.unbosque.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;


/**
 * The persistent class for the auditoria database table.
 * 
 */
@Data
@Entity
@Table(name = "auditoria", uniqueConstraints = @UniqueConstraint(columnNames = "id"))
public class Auditoria implements Serializable {
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name="accion_audtria")
	private String accionAudtria;

	@Column(name="address_audtria")
	private String addressAudtria;

	@Column(name="comentario_audtria")
	private String comentarioAudtria;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fcha_audtria")
	private Date fchaAudtria;

	@Column(name="id_tabla")
	private int idTabla;

	@Column(name="tabla_accion")
	private String tablaAccion;

	@Column(name="usrio_audtria")
	private String usrioAudtria;

	
}