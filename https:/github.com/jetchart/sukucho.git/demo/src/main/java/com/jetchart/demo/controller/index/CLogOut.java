package com.jetchart.demo.controller.index;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jetchart.demo.model.CUsuario;

@Controller
public class CLogOut {
	
	private static final Logger logger = LoggerFactory.getLogger(CLogOut.class);
	
	@RequestMapping(value = "/logOut", method = RequestMethod.GET)
	public String home(HttpServletRequest request) {
		CUsuario usuario = (CUsuario) request.getSession(true).getAttribute("usuario");
		request.getSession(true).removeAttribute("usuario");
		logger.info("Deslogueado: " + usuario.getEmail());
		return "redirect:index";
	}
	
}
