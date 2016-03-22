package com.jetchart.demo.business.usuario;

import java.util.Collection;

import com.jetchart.demo.model.CMenu;
import com.jetchart.demo.model.CUsuario;

import com.jetchart.demo.dao.usuario.CUsuarioHDAO;
import com.jetchart.demo.dao.menu.CMenuHDAO;

public class CUsuarioBusiness {

	public CUsuarioBusiness(){
		
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
	
	public CUsuario getUsuarioByEmailAndContrasenia(String email, String contrasenia) throws Exception
	{
		return CUsuarioHDAO.getUsuarioByEmailAndContrasenia(email, contrasenia);
	}
	
	public Collection<CMenu> getMenuByUsuario(CUsuario usuario) throws Exception{
		return CMenuHDAO.getMenuByUsuario(usuario);
	}
}
