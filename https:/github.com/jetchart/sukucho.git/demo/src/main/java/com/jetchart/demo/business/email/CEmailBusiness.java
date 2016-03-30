package com.jetchart.demo.business.email;

import javax.servlet.http.HttpServletRequest;

import com.jetchart.demo.model.CEmail;
import com.jetchart.demo.model.CUsuario;

public class CEmailBusiness {
	
	public CEmailBusiness(){
		
	}
	
	public CEmail getEmailActivarRegistro(CUsuario usuario, HttpServletRequest request){
		String host = request.getRequestURL().toString().substring(0, request.getRequestURL().toString().length() - request.getServletPath().length());
		CEmail email = new CEmail();
		email.setAsunto("Activación de cuenta - Sukucho");
		email.setCuerpo("Su registro en el sistema ha sido satisfactorio. <br>Solo debe activar su cuenta para utilizarla haciendo <a href='" + host + "/activarUsuario?usuarioId=" + usuario.getId() + "'>click</a> aquí.");
		String[] colMails = new String[1];
		colMails[0] = usuario.getEmail();
		email.setColEmails(colMails);
		return email;
	}
	
	public CEmail getEmailCierrePeriodo(){
		CEmail email = new CEmail();
		return email;
	}
}
