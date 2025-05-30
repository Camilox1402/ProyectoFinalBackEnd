package co.edu.unbosque.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ItemCompra {
	
	@JsonProperty("idProducto")
	private long idProducto;
	
	@JsonProperty("cantidad")
	private int cantidad;

}
