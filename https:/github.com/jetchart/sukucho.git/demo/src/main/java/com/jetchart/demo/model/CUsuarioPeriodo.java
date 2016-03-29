package com.jetchart.demo.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the USUARIO_PERIODO database table.
 * 
 */
@Entity
@Table(name="USUARIO_PERIODO")
@NamedQuery(name="CUsuarioPeriodo.findAll", query="SELECT c FROM CUsuarioPeriodo c")
public class CUsuarioPeriodo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	//bi-directional many-to-one association to CPeriodo
	@ManyToOne
	private CPeriodo periodo;

	//bi-directional many-to-one association to CUsuario
	@ManyToOne
	private CUsuario usuario;

	public CUsuarioPeriodo() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public CPeriodo getPeriodo() {
		return this.periodo;
	}

	public void setPeriodo(CPeriodo periodo) {
		this.periodo = periodo;
	}

	public CUsuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(CUsuario usuario) {
		this.usuario = usuario;
	}

}