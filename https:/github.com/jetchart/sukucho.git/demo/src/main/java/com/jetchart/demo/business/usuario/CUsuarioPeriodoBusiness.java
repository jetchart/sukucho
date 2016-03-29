package com.jetchart.demo.business.usuario;

import org.joda.time.DateTime;

import com.jetchart.demo.dao.periodo.CPeriodoHDAO;
import com.jetchart.demo.dao.usuario.CUsuarioHDAO;
import com.jetchart.demo.dao.usuario.CUsuarioPeriodoHDAO;
import com.jetchart.demo.model.CPeriodo;

public class CUsuarioPeriodoBusiness {

	public CUsuarioPeriodoBusiness(){
		
	}
	
	public void insert(Object object) throws Exception{
		CUsuarioPeriodoHDAO.insert(object);
	}
	
	public void update(Object object) throws Exception{
		CUsuarioPeriodoHDAO.update(object);
	}
	
	public void delete(Object object) throws Exception{
		CUsuarioPeriodoHDAO.delete(object);
	}
	
	public CPeriodo getPeriodoByMesAndAnio(Integer mes, Integer anio) throws Exception{
		return CPeriodoHDAO.getPeriodoByMesAndAnio(mes, anio);
	}

}
