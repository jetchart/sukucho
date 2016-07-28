package com.jetchart.demo.controller.index;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/errorPermiso")
public class CErrorPermisoController {
	
	private static final Logger logger = LoggerFactory.getLogger(CErrorPermisoController.class);
	
	@RequestMapping(method = RequestMethod.GET)
	public String doGet(Model model) {
		logger.debug("GET");
		return "errorPermiso";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String goPost(HttpServletRequest request, Model model) throws Exception {
		logger.debug("POST");
		return "errorPermiso";
	}

}
