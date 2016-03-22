package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the MENU database table.
 * 
 */
@Entity
@Table(name="MENU")
@NamedQuery(name="CMenu.findAll", query="SELECT c FROM CMenu c")
public class CMenu implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String PATH_INDEX = "/WEB-INF/views/index.jsp";
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String descripcion;

	private String url;
	
	private String path;
	
	private String visible;

	//bi-directional many-to-one association to CNivel
	@ManyToOne
	private CNivel nivel;

	public CMenu() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	public String getVisible() {
		return this.visible;
	}

	public void setVisible(String visible) {
		this.visible = visible;
	}
	
	public CNivel getNivel() {
		return this.nivel;
	}

	public void setNivel(CNivel nivel) {
		this.nivel = nivel;
	}

}