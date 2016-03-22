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
@RequestMapping(value = "/errorPermiso")
public class CErrorPermisoController {
	
	private static final Logger logger = LoggerFactory.getLogger(CErrorPermisoController.class);
	
	@RequestMapping(method = RequestMethod.GET)
	public String doGet(Locale locale, Model model) {
		logger.info("GET");
		return "errorPermiso";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String goPost(HttpServletRequest request, Locale locale, Model model) throws Exception {
		logger.info("POST");
		return "errorPermiso";
	}

}
