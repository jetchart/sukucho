package com.jetchart.demo.business.gasto;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;

import org.joda.time.DateTime;

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
	
	public Collection<Object[]> findTotalPersonasByPeriodo(CPeriodo periodo) throws Exception{
		return CGastoHDAO.findTotalPersonasByPeriodo(periodo);
	}
	
	public Boolean isPeriodoVigente(DateTime fecha){
		Integer mes = fecha.getMonthOfYear();
		Integer anio = fecha.getYear();
		DateTime fechaActual = new DateTime(System.currentTimeMillis());
		Integer mesActual = fechaActual.getMonthOfYear();
		Integer anioActual = fechaActual.getYear();
		if (mes.equals(mesActual) && anio.equals(anioActual)){
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}
	
	public String getEstadoPeriodo(CPeriodo periodo){
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.set(periodo.getAnio(), periodo.getMes()-1, 01,0,0,0);
		Timestamp fechaDesde = new Timestamp(calendar.getTimeInMillis());
		if (isPeriodoVigente(new DateTime(fechaDesde.getTime()))){
			return CPeriodo.ESTADO_VIGENTE;
		}
		return CPeriodo.ESTADO_CERRADO;
	}
	
	public CPeriodo getPeriodoVigente(){
		DateTime fechaActual = new DateTime(System.currentTimeMillis());
		return new CPeriodo(fechaActual.getMonthOfYear(), fechaActual.getYear());
		
	}
	
	public static CPeriodo getPeriodoByFecha(DateTime fecha){
		return new CPeriodo(fecha.getMonthOfYear(), fecha.getYear());
	}
}
