package com.jetchart.demo.business.nivel;

import java.util.Collection;

import com.jetchart.demo.model.CNivel;

import com.jetchart.demo.dao.usuario.CUsuarioHDAO;
import com.jetchart.demo.dao.nivel.CNivelHDAO;

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
