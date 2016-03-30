package com.jetchart.demo.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the ESTADO_PERIODO database table.
 * 
 */
@Entity
@Table(name="ESTADO_PERIODO")
@NamedQuery(name="CEstadoPeriodo.findAll", query="SELECT c FROM CEstadoPeriodo c")
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

	//bi-directional many-to-one association to CPeriodo
	@OneToMany(mappedBy="estadoPeriodo")
	private List<CPeriodo> periodos;

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

	public List<CPeriodo> getPeriodos() {
		return this.periodos;
	}

	public void setPeriodos(List<CPeriodo> periodos) {
		this.periodos = periodos;
	}

	public CPeriodo addPeriodo(CPeriodo periodo) {
		getPeriodos().add(periodo);
		periodo.setEstadoPeriodo(this);

		return periodo;
	}

	public CPeriodo removePeriodo(CPeriodo periodo) {
		getPeriodos().remove(periodo);
		periodo.setEstadoPeriodo(null);

		return periodo;
	}

}