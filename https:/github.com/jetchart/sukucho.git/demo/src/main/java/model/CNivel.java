package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the NIVEL database table.
 * 
 */
@Entity
@Table(name="NIVEL")
@NamedQuery(name="CNivel.findAll", query="SELECT c FROM CNivel c")
public class CNivel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final Integer ID_ADMINISTRADOR = 1;
	public static final Integer ID_USUARIO = 2;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	private String descripcion;

	//bi-directional many-to-one association to CMenu
	@OneToMany(mappedBy="nivel")
	private List<CMenu> menus;

	public CNivel() {
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

	public List<CMenu> getMenus() {
		return this.menus;
	}

	public void setMenus(List<CMenu> menus) {
		this.menus = menus;
	}

	public CMenu addMenus(CMenu menus) {
		getMenus().add(menus);
		menus.setNivel(this);

		return menus;
	}

	public CMenu removeMenus(CMenu menus) {
		getMenus().remove(menus);
		menus.setNivel(null);

		return menus;
	}

}