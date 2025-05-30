package co.edu.unbosque.entity;


import java.io.Serializable;


import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;


/**
 * The persistent class for the producto database table.
 * 
 */
@Data
@Entity
@Table(name = "producto", uniqueConstraints = @UniqueConstraint(columnNames = "id"))
public class Producto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name="nombre")
	private String nombre;

	@Column(name="costo_compra")
	private BigDecimal costoCompra;

	private String descripcion;

	private byte estado;

	private int existencia;

	@Column(name="foto_producto")
	private String fotoProducto;

	@ManyToOne
	@JoinColumn(name = "id_categoria")
	private Categoria categoria;

	@Column(name="precio_venta_actual")
	private BigDecimal precioVentaActual;

	@Column(name="precio_venta_anterior")
	private BigDecimal precioVentaAnterior;

	private String referencia;

	@Column(name="stock_maximo")
	private int stockMaximo;

	@Column(name="tiene_iva")
	private byte tieneIva;
	
	
	@Column(name="tiene_descuento")
	private byte tieneDescuento;


}