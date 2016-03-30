package com.jetchart.demo.controller.index;

import java.util.Collection;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jetchart.demo.model.CMenu;
import com.jetchart.demo.model.CUsuario;
import com.jetchart.demo.service.usuario.CUsuarioService;
import com.jetchart.demo.util.CUtil;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/index")
public class CIndexController {
	
	private static final Logger logger = LoggerFactory.getLogger(CIndexController.class);
	
	@RequestMapping(method = RequestMethod.GET)
	public String doGet(HttpServletRequest request, Model model){
		logger.info("GET");
		/* Coloco en sesion el Menu disponible para el Usuario logueado */
		CUsuario usuario = (CUsuario) request.getSession(true).getAttribute("usuario");
		Collection<CMenu> colMenu;
		try {
			colMenu = CUsuarioService.getMenuByUsuario(usuario);
		} catch (Exception e) {
			request.getSession(true).setAttribute("exception", e);
			return "redirect:error";
		}
		request.getSession(true).setAttribute("colMenu", colMenu);
		
		return "index";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String goPost(HttpServletRequest request, Locale locale, Model model){
		logger.info("POST");
		String accion = request.getParameter("accion");
		try {
			if ("Ingresar".equals(accion)){
				String email = request.getParameter("email");
				String contrasenia = request.getParameter("contrasenia");
//				String passEncriptada = CUtil.encriptarClave(contrasenia);
				/* Login */
				CUsuario usuario;
				usuario = CUsuarioService.getUsuarioByEmailAndContrasenia(email, contrasenia);
				if (usuario != null){
					if (usuario.getActivado() != 1){
						logger.info("Usuario no activado");
						model.addAttribute("errorLogin", "Usuario no activado.<br>Por favor revise su email para realizar la activación. Click <a href='./enviarMailActivacion?usuarioId="+usuario.getId()+"'>aquí</a> para reenviar mail.");
						return "index";
					}else{
						logger.info("Login correcto");
						/* Coloco en sesion al Usuario logueado */
						request.getSession(true).setAttribute("usuario", usuario);
						/* Coloco en sesion el Menu disponible para el Usuario logueado */
						Collection<CMenu> colMenu = CUsuarioService.getMenuByUsuario(usuario);
						request.getSession(true).setAttribute("colMenu", colMenu);
					}
				}else{
					request.getSession(true).removeAttribute("usuario");
					logger.info("Login incorrecto");
					model.addAttribute("errorLogin", "Login incorrecto");
				}
				logger.info("index");
				return "index";
			}else if ("ABM Usuario".equals(accion)){
				logger.info("redirect:listarUsuarios");
				return "redirect:listarUsuarios";
			}
		} catch (Exception e) {
			request.getSession(true).setAttribute("exception", e);
			return "redirect:error";
		}
		logger.info("index");
		return "index";
	}
}
