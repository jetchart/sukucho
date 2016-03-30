package com.jetchart.demo.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;

import com.jetchart.demo.business.gasto.CGastoBusiness;
import com.jetchart.demo.model.CEmail;
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
	
	public static void enviarMail(CEmail email) throws UnsupportedEncodingException, MessagingException{
		 // El correo gmail de envío  
		  String correoEnvia = "sukucho22@gmail.com";
		  String claveCorreo = "argento1";  
		  String quienEnvia = "Sukucho";
		  
		  // La configuración para enviar correo  
		  Properties properties = new Properties();  
		  properties.put("mail.smtp.host", "smtp.gmail.com");  
		  properties.put("mail.smtp.starttls.enable", "true");  
		  properties.put("mail.smtp.port", "587");  
		  properties.put("mail.smtp.auth", "true");  
		  properties.put("mail.user", correoEnvia);  
		  properties.put("mail.password", claveCorreo);  
		  
		  // Obtener la sesion  
		  Session session = Session.getInstance(properties, null);  
		  
		  // Crear el cuerpo del mensaje  
		   MimeMessage mimeMessage = new MimeMessage(session);  
		   // Agregar quien envía el correo  
		   mimeMessage.setFrom(new InternetAddress(correoEnvia, quienEnvia));  
		     
		   // Los destinatarios  
		   InternetAddress[] internetAddresses = new InternetAddress[email.getColEmails().length];
		   int i = 0;
		   for (String direccionEmail : email.getColEmails()){
			   internetAddresses[i++] = new InternetAddress(direccionEmail);
		   }
		  
		   // Agregar los destinatarios al mensaje  
		   mimeMessage.setRecipients(Message.RecipientType.TO,  
		     internetAddresses);  
		  
		   // Agregar el asunto al correo  
		   mimeMessage.setSubject(email.getAsunto());  
		  
		   // Creo la parte del mensaje  
		   MimeBodyPart mimeBodyPart = new MimeBodyPart();  
		   mimeBodyPart.setText(email.getCuerpo(), "UTF-8", "html");  
		  
		   // Crear el multipart para agregar la parte del mensaje anterior  
		   Multipart multipart = new MimeMultipart();  
		   multipart.addBodyPart(mimeBodyPart);  
		  
		   // Agregar el multipart al cuerpo del mensaje  
		   mimeMessage.setContent(multipart);  
		  
		   // Enviar el mensaje  
		   Transport transport = session.getTransport("smtp");  
		   transport.connect(correoEnvia, claveCorreo);  
		   transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());  
		   transport.close();  

		  System.out.println("Correo enviado");  
	}
}
