package com.jetchart.demo.business.email;

import java.math.BigDecimal;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import com.jetchart.demo.business.gasto.CGastoBusiness;
import com.jetchart.demo.business.usuario.CUsuarioBusiness;
import com.jetchart.demo.model.CEmail;
import com.jetchart.demo.model.CGasto;
import com.jetchart.demo.model.CPeriodo;
import com.jetchart.demo.model.CUsuario;
import com.jetchart.demo.service.gasto.CGastoService;

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
	
	public CEmail getEmailCierrePeriodo(CPeriodo periodo, HttpServletRequest request) throws Exception{
		//TODO ver como recibir el REQUEST
//		String host = request.getRequestURL().toString().substring(0, request.getRequestURL().toString().length() - request.getServletPath().length());
		String host = "AcaIriaElHost";
		/* Gastos por periodo */
		Collection<CGasto> gastos = (Collection<CGasto>) CGastoService.findByPeriodo(periodo);
		/* Gasto por persona y periodo */
		Collection<Object[]> personaGastos = (Collection<Object[]>) CGastoService.findTotalPersonasByPeriodo(periodo);
		/* Total */
		BigDecimal totalPeriodo = BigDecimal.valueOf(0);
		for (CGasto gasto : gastos){
			totalPeriodo = totalPeriodo.add(gasto.getPrecio());
		}
		/* Gasto por persona */
		BigDecimal montoPorPersona = totalPeriodo.divide(BigDecimal.valueOf(personaGastos.size()));
		/* Creo el cuerpo del mensaje */
		String cuerpo = "Estos son los gastos del periodo "+ periodo.getMes() + "/" + periodo.getAnio() + ".<br><br>"
				+ "<b>Total gastado: $" + totalPeriodo + "</b><br><br>";
		/* Escribo los gastos de cada persona y lo que debe */
		for (Object[] object : personaGastos ){
			BigDecimal debe = montoPorPersona.subtract((BigDecimal)object[1]);
			debe = BigDecimal.valueOf(0).compareTo(debe) == 1?BigDecimal.valueOf(0):debe;
			cuerpo += object[0] + " gastó $" + object[1] + " y debe $" + (debe)+"<br>";
		}
		cuerpo += "<br>Gasto por persona: $" + montoPorPersona;
		cuerpo += "<br><br>Para mayor detalle haga click <a href='" + host + "/abmGasto?mes="+periodo.getMes()+"&anio="+ periodo.getAnio() +"'>click</a>";
		/* Creo mail */
		CEmail email = new CEmail();
		email.setAsunto("Cierre del periodo "+ periodo.getMes() + "/" + periodo.getAnio() + "- Sukucho");
		email.setCuerpo(cuerpo);
		/* Agrego todos los mails */
		String[] colMails = new String[personaGastos.size()];
		int i = 0;
		for (Object[] object : personaGastos ){
			colMails[i++] = (String) object[2];
		}
		email.setColEmails(colMails);
		return email;
	}
}
