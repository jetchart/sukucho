package com.jetchart.demo.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigDecimal;


/**
 * The persistent class for the GASTO database table.
 * 
 */
@Entity
@Table(name="GASTO")
@NamedQuery(name="CGasto.findAll", query="SELECT c FROM CGasto c")
public class CGasto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	private String descripcion;

	private Timestamp fecha;

	private Float precio;

	//bi-directional many-to-one association to CUsuario
	@ManyToOne
	private CUsuario usuario;

	//bi-directional many-to-one association to CPeriodo
	@ManyToOne
	private CPeriodo periodo;

	public CGasto() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Timestamp getFecha() {
		return this.fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public Float getPrecio() {
		return this.precio;
	}

	public void setPrecio(Float precio) {
		this.precio = precio;
	}

	public CUsuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(CUsuario usuario) {
		this.usuario = usuario;
	}

	public CPeriodo getPeriodo() {
		return this.periodo;
	}

	public void setPeriodo(CPeriodo periodo) {
		this.periodo = periodo;
	}

}