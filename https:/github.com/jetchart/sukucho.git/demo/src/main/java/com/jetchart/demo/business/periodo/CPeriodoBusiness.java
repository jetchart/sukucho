package com.jetchart.demo.business.periodo;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;

import com.jetchart.demo.dao.periodo.CPeriodoHDAO;
import com.jetchart.demo.dao.usuario.CUsuarioHDAO;
import com.jetchart.demo.model.CEstadoPeriodo;
import com.jetchart.demo.model.CNivel;
import com.jetchart.demo.model.CPeriodo;
import com.jetchart.demo.service.nivel.CNivelService;

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
	
	public CPeriodo getPeriodoByMesAndAnioNoFuturo(Integer mes, Integer anio) throws Exception{
		CPeriodo periodo = CPeriodoHDAO.getPeriodoByMesAndAnio(mes, anio);
		if (periodo != null && !CEstadoPeriodo.ID_FUTURO.equals(periodo.getEstadoPeriodo().getId())){
			return periodo;
		}
		return null;
	}
	
	public CPeriodo getPeriodoVigente() throws Exception{
		return CPeriodoHDAO.getPeriodoVigente();
	}
	
	public Collection<CPeriodo> getPeriodosPendienteAviso() throws Exception{
		return CPeriodoHDAO.getPeriodosPendienteAviso();
	}
	
	public CPeriodo getMaximoPeriodo() throws Exception{
		return CPeriodoHDAO.getMaximoPeriodo();
	}
	
	public CPeriodo armarProximoPeriodo(){
		DateTime fechaActual = new DateTime(System.currentTimeMillis());
		Integer mes = fechaActual.getMonthOfYear()==12?1:fechaActual.getMonthOfYear()+1;
		Integer anio = fechaActual.getMonthOfYear()==12?fechaActual.getYear()+1:fechaActual.getYear();
		CPeriodo periodo = new CPeriodo(mes,anio);
		CEstadoPeriodo estadoPeriodo = new CEstadoPeriodo();
		estadoPeriodo.setId(CEstadoPeriodo.ID_FUTURO);
		periodo.setEstadoPeriodo(estadoPeriodo);
		return periodo;
	}
	
	public CPeriodo armarPeriodoVigente(){
		DateTime fechaActual = new DateTime(System.currentTimeMillis());
		Integer mes = fechaActual.getMonthOfYear();
		Integer anio = fechaActual.getYear();
		CPeriodo periodo = new CPeriodo(mes,anio);
		CEstadoPeriodo estadoPeriodo = new CEstadoPeriodo();
		estadoPeriodo.setId(CEstadoPeriodo.ID_VIGENTE);
		periodo.setEstadoPeriodo(estadoPeriodo);
		return periodo;
	}
	
	public Map<Integer,String> getMesDropDown() throws Exception{
		Collection<String> colMes = Arrays.asList("Enero","Febrero","Marzo","Abril","Mayo","Junio",
									"Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre");
		Map<Integer,String> mesDropDown = new LinkedHashMap<Integer,String>();
		int i = 1;
		for(String mes : colMes){
			mesDropDown.put(i++, mes);
		}
		return mesDropDown;
	}
	
	public Map<Integer,String> getAnioDropDown() throws Exception{
		Collection<Integer> colAnio = CPeriodoHDAO.getAniosExistentes();
		Map<Integer,String> anioDropDown = new LinkedHashMap<Integer,String>();
		for(Integer anio : colAnio){
			anioDropDown.put(anio, String.valueOf(anio));
		}
		return anioDropDown;
	}
}
