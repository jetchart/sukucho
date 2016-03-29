package com.jetchart.demo.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;

import com.jetchart.demo.business.gasto.CGastoBusiness;
import com.jetchart.demo.model.CGasto;
import com.jetchart.demo.model.CMenu;
import com.jetchart.demo.model.CNivel;
import com.jetchart.demo.model.CUsuario;

public class CUtil {

	public static Boolean puedeEditarUsuario(HttpServletRequest request){
		CUsuario usuarioLogueado = (CUsuario) request.getSession(true).getAttribute("usuario"); 
		if (CNivel.ID_ADMINISTRADOR.equals(usuarioLogueado.getNivel().getId())){
			return Boolean.TRUE;
		}	
		return Boolean.FALSE;
	}
	
	public static Boolean puedeEditarGasto(HttpServletRequest request, String gastoId) throws Exception{
		/* Si es nuevo se puede editar */
		if (gastoId == null)
			return Boolean.TRUE;
		/* Si es el mismo usuario que lo creó o es administrador, y está en el periodo vigente se puede editar */
		CUsuario usuarioLogueado = (CUsuario) request.getSession(true).getAttribute("usuario");
		CGasto gasto = (CGasto) CHDAOService.findById(new CGasto(), Integer.valueOf(gastoId));
		if (usuarioLogueado.getId().equals(gasto.getUsuario().getId()) || CNivel.ID_ADMINISTRADOR.equals(usuarioLogueado.getNivel().getId())){
			if (new CGastoBusiness().isPeriodoVigente(new DateTime(gasto.getFecha()))){
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
	
	public static String encriptarClave(String clave) throws NoSuchAlgorithmException{
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] b = md.digest(clave.getBytes());
		int size = b.length;
		StringBuffer h = new StringBuffer(size);
		for (int i = 0; i < size; i++) {
			int u = b[i] & 255;
			if (u < 16) {
				h.append("0" + Integer.toHexString(u));
			} else {
				h.append(Integer.toHexString(u));
			}
		}
		return h.toString();
	}
}
