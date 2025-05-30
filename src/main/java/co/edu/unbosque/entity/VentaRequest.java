package co.edu.unbosque.entity;

import java.util.ArrayList;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class VentaRequest {


	@JsonProperty("idCliente")
	private int idCliente;
	
	@JsonProperty("items")
    private ArrayList<ItemCompra> items;

}
