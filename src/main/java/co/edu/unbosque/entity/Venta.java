package co.edu.unbosque.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;


/**
 * The persistent class for the venta database table.
 * 
 */
@Data
@Entity
@Table(name = "venta", uniqueConstraints = @UniqueConstraint(columnNames = "id"))
public class Venta implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	private byte estado;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_venta")
	private Date fechaVenta;

	@Column(name="id_cliente")
	private int idCliente;

	@Column(name="valor_dscto")
	private BigDecimal valorDscto;

	@Column(name="valor_iva")
	private BigDecimal valorIva;

	@Column(name="valor_venta")
	private BigDecimal valorVenta;



}