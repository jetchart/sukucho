package com.jetchart.demo.Business.Usuario;

import java.util.Collection;

import model.CMenu;
import model.CNivel;
import model.CUsuario;

import com.jetchart.demo.DAO.Usuario.CMenuHDAO;
import com.jetchart.demo.DAO.Usuario.CNivelHDAO;
import com.jetchart.demo.DAO.Usuario.CUsuarioHDAO;

public class CNivelBusiness {

	public CNivelBusiness(){
		
	}
	
	public void insert(Object object) throws Exception{
		CUsuarioHDAO.insert(object);
	}
	
	public void update(Object object) throws Exception{
		CUsuarioHDAO.update(object);
	}
	
	public void delete(Object object) throws Exception{
		CUsuarioHDAO.delete(object);
	}
	
	public Collection<CNivel> getAllNivel() throws Exception{
		return CNivelHDAO.getAllNivel();
	}
}
