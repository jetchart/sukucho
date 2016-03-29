package com.jetchart.demo.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;


/**
 * The persistent class for the USUARIO database table.
 * 
 */
@Entity
@Table(name="USUARIO")
@NamedQuery(name="CUsuario.findAll", query="SELECT c FROM CUsuario c")
public class CUsuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	private String apellido;

	private String contrasenia;

	private String email;

	@ManyToOne
	private CNivel nivel;

	private String nombre;
	
	private Integer activado;
	
	@Column(name="fecha_alta")
	private Timestamp fechaAlta;

	public CUsuario() {
		this.setActivado(0);
		this.setFechaAlta(new Timestamp(System.currentTimeMillis()));
		CNivel nivel = new CNivel();
		nivel.setId(CNivel.ID_USUARIO);
		this.setNivel(nivel);
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getApellido() {
		return this.apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getContrasenia() {
		return this.contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getActivado() {
		return this.activado;
	}

	public void setActivado(Integer activado) {
		this.activado = activado;
	}
	
	public CNivel getNivel() {
		return this.nivel;
	}

	public void setNivel(CNivel nivel) {
		this.nivel = nivel;
	}

	public Timestamp getFechaAlta() {
		return this.fechaAlta;
	}

	public void setFechaAlta(Timestamp fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}