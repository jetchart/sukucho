package com.jetchart.demo.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


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

	private float precio;

	//bi-directional many-to-one association to CUsuario
	@ManyToOne
	private CUsuario usuario;

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

	public float getPrecio() {
		return this.precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public CUsuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(CUsuario usuario) {
		this.usuario = usuario;
	}

}