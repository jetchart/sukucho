package com.jetchart.demo.business.periodo;

import org.joda.time.DateTime;

import com.jetchart.demo.dao.periodo.CPeriodoHDAO;
import com.jetchart.demo.dao.usuario.CUsuarioHDAO;
import com.jetchart.demo.model.CPeriodo;

public class CPeriodoBusiness {

	public CPeriodoBusiness(){
		
	}
	
	public void insert(Object object) throws Exception{
		CPeriodoHDAO.insert(object);
	}
	
	public void update(Object object) throws Exception{
		CPeriodoHDAO.update(object);
	}
	
	public void delete(Object object) throws Exception{
		CPeriodoHDAO.delete(object);
	}
	
	public CPeriodo getPeriodoByMesAndAnio(Integer mes, Integer anio) throws Exception{
		return CPeriodoHDAO.getPeriodoByMesAndAnio(mes, anio);
	}
	
	public CPeriodo getPeriodoVigente() throws Exception{
		DateTime fechaActual = new DateTime(System.currentTimeMillis());
		return CPeriodoHDAO.getPeriodoByMesAndAnio(fechaActual.getMonthOfYear(), fechaActual.getYear());
	}
	
	public CPeriodo getMaximoPeriodo() throws Exception{
		return CPeriodoHDAO.getMaximoPeriodo();
	}
	
	public CPeriodo armarProximoPeriodo(){
		DateTime fechaActual = new DateTime(System.currentTimeMillis());
		Integer mes = fechaActual.getMonthOfYear()==12?1:fechaActual.getMonthOfYear()+1;
		Integer anio = fechaActual.getMonthOfYear()==12?fechaActual.getYear()+1:fechaActual.getYear();
		CPeriodo periodo = new CPeriodo(mes,anio);
		return periodo;
	}
}
