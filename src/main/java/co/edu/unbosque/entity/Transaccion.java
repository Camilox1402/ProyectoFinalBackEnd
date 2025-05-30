package co.edu.unbosque.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;


/**
 * The persistent class for the transaccion database table.
 * 
 */
@Data
@Entity
@Table(name = "transaccion", uniqueConstraints = @UniqueConstraint(columnNames = "id"))
public class Transaccion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;


	private byte estado;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_hora")
	private Date fechaHora;

	@Column(name="id_banco")
	private String idBanco;

	@Column(name="id_compra")
	private int idCompra;

	@Column(name="id_franquicia")
	private String idFranquicia;

	@Column(name="id_metodo_pago")
	private short idMetodoPago;

	private String identificacion;

	@Column(name="num_tarjeta")
	private String numTarjeta;

	@Column(name="valor_tx")
	private int valorTx;


}