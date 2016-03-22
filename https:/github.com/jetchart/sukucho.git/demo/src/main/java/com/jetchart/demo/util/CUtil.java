package com.jetchart.demo.util;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import com.jetchart.demo.model.CMenu;
import com.jetchart.demo.model.CNivel;
import com.jetchart.demo.model.CUsuario;

public class CUtil {

	public static Boolean puedeEditar(HttpServletRequest request){
		CUsuario usuarioLogueado = (CUsuario) request.getSession(true).getAttribute("usuario"); 
		if (usuarioLogueado.getNivel().getId() == CNivel.ID_ADMINISTRADOR){
			return Boolean.TRUE;
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
}
