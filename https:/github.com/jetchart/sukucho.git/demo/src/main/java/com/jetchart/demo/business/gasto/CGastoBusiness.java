package com.jetchart.demo.business.gasto;

import java.util.Collection;

import com.jetchart.demo.model.CGasto;
import com.jetchart.demo.model.CMenu;
import com.jetchart.demo.model.CPeriodo;
import com.jetchart.demo.model.CUsuario;
import com.jetchart.demo.dao.usuario.CUsuarioHDAO;
import com.jetchart.demo.dao.gasto.CGastoHDAO;
import com.jetchart.demo.dao.menu.CMenuHDAO;

public class CGastoBusiness {

	public CGastoBusiness(){
		
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
	
	public Collection<CGasto> findByPeriodo(CPeriodo periodo) throws Exception{
		return CGastoHDAO.findByPeriodo(periodo);
	}
}
