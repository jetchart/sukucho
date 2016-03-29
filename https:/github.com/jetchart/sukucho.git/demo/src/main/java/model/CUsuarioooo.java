package model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the USUARIO database table.
 * 
 */
@Entity
@Table(name="USUARIO")
@NamedQuery(name="CUsuarioooo.findAll", query="SELECT c FROM CUsuarioooo c")
public class CUsuarioooo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String activado;

	private String apellido;

	private String contrasenia;

	private String email;

	@Column(name="fecha_alta")
	private Timestamp fechaAlta;

	@Column(name="nivel_id")
	private int nivelId;

	private String nombre;

	public CUsuarioooo() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getActivado() {
		return this.activado;
	}

	public void setActivado(String activado) {
		this.activado = activado;
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

	public Timestamp getFechaAlta() {
		return this.fechaAlta;
	}

	public void setFechaAlta(Timestamp fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public int getNivelId() {
		return this.nivelId;
	}

	public void setNivelId(int nivelId) {
		this.nivelId = nivelId;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}