package com.jetchart.demo.business.usuario;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import com.jetchart.demo.model.CMenu;
import com.jetchart.demo.model.CPeriodo;
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
	
	public Boolean existsPersonaByEmail(CUsuario usuario) throws Exception{
		return CUsuarioHDAO.existsPersonaByEmail(usuario);
	}
	
	public Collection<CUsuario> findUsuariosByPeriodo(CPeriodo periodo) throws Exception{
		return CUsuarioHDAO.findUsuariosByPeriodo(periodo);
	}
	
	public Map<Integer,String> getEstadoCuentaDropDown() throws Exception{
		Collection<String> estadosCuenta = Arrays.asList("No activada", "Activada");
		Map<Integer,String> estadoCuentaDropDown = new LinkedHashMap<Integer,String>();
		int i = 0;
		for(String estadoCuenta : estadosCuenta){
			estadoCuentaDropDown.put(i++, estadoCuenta);
		}
		return estadoCuentaDropDown;
	}
}
