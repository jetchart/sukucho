package com.jetchart.demo.Business.Usuario;

import java.util.Collection;

import model.CMenu;
import model.CUsuario;

import com.jetchart.demo.DAO.Usuario.CMenuHDAO;
import com.jetchart.demo.DAO.Usuario.CUsuarioHDAO;

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
