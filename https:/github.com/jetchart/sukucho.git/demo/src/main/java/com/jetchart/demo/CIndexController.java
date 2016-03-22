package com.jetchart.demo;

import java.util.Collection;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import model.CMenu;
import model.CUsuario;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jetchart.demo.Service.Usuario.CUsuarioService;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/index")
public class CIndexController {
	
	private static final Logger logger = LoggerFactory.getLogger(CIndexController.class);
	
	@RequestMapping(method = RequestMethod.GET)
	public String doGet(Locale locale, Model model) {
		logger.info("GET");
		return "index";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String goPost(HttpServletRequest request, Locale locale, Model model) throws Exception {
		logger.info("POST");
		String accion = request.getParameter("accion");
		if ("Ingresar".equals(accion)){
			String email = request.getParameter("email");
			String contrasenia = request.getParameter("contrasenia");
			/* Login */
			CUsuario usuario = CUsuarioService.getUsuarioByEmailAndContrasenia(email, contrasenia);
			if (usuario != null){
				logger.info("Login correcto");
				/* Coloco en sesion al Usuario logueado */
				request.getSession(true).setAttribute("usuario", usuario);
				/* Coloco en sesion el Menu disponible para el Usuario logueado */
				Collection<CMenu> colMenu = CUsuarioService.getMenuByUsuario(usuario);
				request.getSession(true).setAttribute("colMenu", colMenu);
			}else{
				request.getSession(true).removeAttribute("usuario");
				logger.info("Login incorrecto");
			}
			logger.info("index");
			return "index";
		}else if ("ABM Usuario".equals(accion)){
			logger.info("redirect:listarUsuarios");
			return "redirect:listarUsuarios";
		}
		logger.info("index");
		return "index";
	}
}
