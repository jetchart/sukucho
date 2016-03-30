package com.jetchart.demo.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the NIVEL database table.
 * 
 */
@Entity
@Table(name="ESTADO_PERIODO")
@NamedQuery(name="CEstadoPeriodo.findAll", query="SELECT e FROM CEstadoPeriodo e")
public class CEstadoPeriodo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final Integer ID_VIGENTE = 1;
	public static final Integer ID_PENDIENTE_AVISO = 2;
	public static final Integer ID_CERRADO = 3;
	public static final Integer ID_FUTURO = 4;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	private String descripcion;


	public CEstadoPeriodo() {
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


}