package com.jetchart.demo.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


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

	//bi-directional many-to-one association to CGasto
	@OneToMany(mappedBy="periodo")
	private List<CGasto> gastos;

	//bi-directional many-to-one association to CUsuarioPeriodo
	@OneToMany(mappedBy="periodo")
	private List<CUsuarioPeriodo> usuarioPeriodos;

	public CPeriodo() {
	}

	public CPeriodo(Integer mes, Integer anio){
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

	public List<CGasto> getGastos() {
		return this.gastos;
	}

	public void setGastos(List<CGasto> gastos) {
		this.gastos = gastos;
	}

	public CGasto addGasto(CGasto gasto) {
		getGastos().add(gasto);
		gasto.setPeriodo(this);

		return gasto;
	}

	public CGasto removeGasto(CGasto gasto) {
		getGastos().remove(gasto);
		gasto.setPeriodo(null);

		return gasto;
	}

	public List<CUsuarioPeriodo> getUsuarioPeriodos() {
		return this.usuarioPeriodos;
	}

	public void setUsuarioPeriodos(List<CUsuarioPeriodo> usuarioPeriodos) {
		this.usuarioPeriodos = usuarioPeriodos;
	}

	public CUsuarioPeriodo addUsuarioPeriodo(CUsuarioPeriodo usuarioPeriodo) {
		getUsuarioPeriodos().add(usuarioPeriodo);
		usuarioPeriodo.setPeriodo(this);

		return usuarioPeriodo;
	}

	public CUsuarioPeriodo removeUsuarioPeriodo(CUsuarioPeriodo usuarioPeriodo) {
		getUsuarioPeriodos().remove(usuarioPeriodo);
		usuarioPeriodo.setPeriodo(null);

		return usuarioPeriodo;
	}

}