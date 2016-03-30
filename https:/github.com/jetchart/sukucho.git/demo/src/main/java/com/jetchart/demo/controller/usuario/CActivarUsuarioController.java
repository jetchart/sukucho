package com.jetchart.demo.controller.usuario;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jetchart.demo.business.email.CEmailBusiness;
import com.jetchart.demo.model.CUsuario;
import com.jetchart.demo.service.usuario.CUsuarioService;
import com.jetchart.demo.util.CHDAOService;
import com.jetchart.demo.util.CUtil;

@Controller

public class CActivarUsuarioController {
	
	private static final Logger logger = LoggerFactory.getLogger(CActivarUsuarioController.class);
	
	@RequestMapping(value = "/activarUsuario", method = RequestMethod.GET)
	public String doGetActivarUsuario(String usuarioId, Model model, HttpServletRequest request){
		logger.info("GET");
		try{
			if (usuarioId != null){
				CUsuario usuario = (CUsuario) CHDAOService.findById(new CUsuario(), Integer.valueOf(usuarioId));
				if (usuario != null){
					if (usuario.getActivado() == 0){
						logger.info("Se activó el usuario!");
						usuario.setActivado(1);
						CUsuarioService.update(usuario);
						return "redirect:index";
					}else{
						logger.info("Usuario ya activado");
						return "redirect:index";
					}
				}
			}
			logger.info("Registro de nuevo usuario");
		}catch(Exception e){
			request.getSession(true).setAttribute("exception", e);
			return "redirect:error";
		}
		return "redirect:index";
	}
	
	@RequestMapping(value = "/enviarMailActivacion", method = RequestMethod.GET)
	public String doGetEnviarMailActivacion(@RequestParam(value = "usuarioId") String usuarioId, Model model, HttpServletRequest request){
		logger.info("GET");
		try{
			CUsuario usuario = (CUsuario) CHDAOService.findById(new CUsuario(), Integer.valueOf(usuarioId));
			CUtil.enviarMail(new CEmailBusiness().getEmailActivarRegistro(usuario, request));
			logger.info("Email de activacion de usuario enviado");
//			model.addAttribute("errorLogin","Se ha enviado un mail para que active su usuario.");
		}catch(Exception e){
			request.getSession(true).setAttribute("exception", e);
			return "redirect:error";
		}
		return "redirect:index";
	}

}
