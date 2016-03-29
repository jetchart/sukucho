package com.jetchart.demo.controller.index;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/error")
public class CErrorController {
	
	private static final Logger logger = LoggerFactory.getLogger(CErrorController.class);
	
	@RequestMapping(method = RequestMethod.GET)
	public String doGet(HttpServletRequest request, Model model, @ModelAttribute("exception") Exception exception) {
		logger.info("GET");
		model.addAttribute("cause", exception.getMessage());
		model.addAttribute("stackTrace", exception.getStackTrace().toString());
		return "error";
	}

}
