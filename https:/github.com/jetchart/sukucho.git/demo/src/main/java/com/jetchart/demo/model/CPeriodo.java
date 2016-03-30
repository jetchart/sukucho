package com.jetchart.demo.model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the PERIODO database table.
 * 
 */
@Entity
@Table(name="PERIODO")
@NamedQuery(name="CPeriodo.findAll", query="SELECT c FROM CPeriodo c")
public class CPeriodo implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String ESTADO_VIGENTE = "Vigente";
	public static final String ESTADO_CERRADO = "Cerrado";
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	private Integer anio;

	private Integer mes;

	//bi-directional many-to-one association to CEstadoPeriodo
	@ManyToOne
	@JoinColumn(name="estado_periodo_id")
	private CEstadoPeriodo estadoPeriodo;

	public CPeriodo() {
	}

	public CPeriodo(Integer mes, Integer anio) {
		this.mes = mes;
		this.anio = anio;
	}
	
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAnio() {
		return this.anio;
	}

	public void setAnio(Integer anio) {
		this.anio = anio;
	}

	public Integer getMes() {
		return this.mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
	}

	public CEstadoPeriodo getEstadoPeriodo() {
		return this.estadoPeriodo;
	}

	public void setEstadoPeriodo(CEstadoPeriodo estadoPeriodo) {
		this.estadoPeriodo = estadoPeriodo;
	}

}