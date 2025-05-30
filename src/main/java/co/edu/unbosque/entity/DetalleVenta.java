package co.edu.unbosque.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.*;
import lombok.Data;


/**
 * The persistent class for the detalle_venta database table.
 * 
 */
@Data
@Entity
@Table(name = "detalle_venta", uniqueConstraints = @UniqueConstraint(columnNames = "id"))
public class DetalleVenta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name="cant_comp")
	private int cantComp;

	@Column(name="id_producto")
	private short idProducto;

	@Column(name="id_venta")
	private int idVenta;

	@Column(name="valor_dscto")
	private BigDecimal valorDscto;

	@Column(name="valor_iva")
	private BigDecimal valorIva;

	@Column(name="valor_unit")
	private BigDecimal valorUnit;


}