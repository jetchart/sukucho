package com.jetchart.demo.controller.index;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CLogOut {
	
	private static final Logger logger = LoggerFactory.getLogger(CLogOut.class);
	
	@RequestMapping(value = "/logOut", method = RequestMethod.GET)
	public String home(HttpServletRequest request) {
		request.getSession(true).removeAttribute("usuario");
		logger.info("Deslogueado");
		return "redirect:index";
	}
	
}
