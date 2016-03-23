package com.jetchart.demo.util;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;

import com.jetchart.demo.model.CGasto;
import com.jetchart.demo.model.CMenu;
import com.jetchart.demo.model.CNivel;
import com.jetchart.demo.model.CPeriodo;
import com.jetchart.demo.model.CUsuario;

public class CUtil {

	public static Boolean puedeEditarUsuario(HttpServletRequest request){
		CUsuario usuarioLogueado = (CUsuario) request.getSession(true).getAttribute("usuario"); 
		if (usuarioLogueado.getNivel().getId() == CNivel.ID_ADMINISTRADOR){
			return Boolean.TRUE;
		}	
		return Boolean.FALSE;
	}
	
	public static Boolean puedeEditarGasto(HttpServletRequest request, String gastoId) throws Exception{
		/* Si es nuevo se puede editar */
		if (gastoId == null)
			return Boolean.TRUE;
		/* Si es el mismo usuario que lo creó y está en el periodo vigente se puede editar */
		CUsuario usuarioLogueado = (CUsuario) request.getSession(true).getAttribute("usuario");
		CGasto gasto = (CGasto) CHDAOService.findById(new CGasto(), Integer.valueOf(gastoId));
		if (usuarioLogueado.getId().equals(gasto.getUsuario().getId())){
			if (isPeriodoVigente(new DateTime(gasto.getFecha()))){
				return Boolean.TRUE;
			}
		}	
		return Boolean.FALSE;
	}
	
	public static Boolean tienePermiso(HttpServletRequest request){
		@SuppressWarnings("unchecked")
		Collection<CMenu> colMenu = (Collection<CMenu>) request.getSession(true).getAttribute("colMenu"); 
		String uri = request.getServletPath();
		if (CMenu.PATH_INDEX.equals(uri)){
			return Boolean.TRUE;
		}else{
			for (CMenu menu : colMenu){
				if (uri.equals(menu.getPath())){
					return Boolean.TRUE;
				}
			}
		}
		return Boolean.FALSE;
	}
	
	public static Boolean isPeriodoVigente(DateTime fecha){
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
	
	public static String getEstadoPeriodo(CPeriodo periodo){
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.set(periodo.getAnio(), periodo.getMes()-1, 01,0,0,0);
		Timestamp fechaDesde = new Timestamp(calendar.getTimeInMillis());
		if (isPeriodoVigente(new DateTime(fechaDesde.getTime()))){
			return CPeriodo.ESTADO_VIGENTE;
		}
		return CPeriodo.ESTADO_CERRADO;
	}
	
	public static CPeriodo getPeriodoVigente(){
		DateTime fechaActual = new DateTime(System.currentTimeMillis());
		return new CPeriodo(fechaActual.getMonthOfYear(), fechaActual.getYear());
		
	}
	
	public static CPeriodo getPeriodoByFecha(DateTime fecha){
		return new CPeriodo(fecha.getMonthOfYear(), fecha.getYear());
	}
}
