package com.jetchart.demo.controller.index;

import java.io.CharArrayWriter;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/error")
public class CErrorController {
	
	private static final Logger logger = LoggerFactory.getLogger(CErrorController.class);
	
	@RequestMapping(method = RequestMethod.GET)
	public String doGet(HttpServletRequest request, Model model) {
		logger.info("GET");
		Exception exception = (Exception) request.getSession(true).getAttribute("exception");
		
		/* Paso el stacktrace a String */
		CharArrayWriter cw = new CharArrayWriter();
		PrintWriter w = new PrintWriter(cw);
		exception.printStackTrace(w);
		w.close();
		String stackTraceString = cw.toString();
		
		model.addAttribute("cause", exception.getMessage());
		model.addAttribute("stackTrace", stackTraceString);
		request.getSession(true).removeAttribute("exception");
		return "error";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String doPost(Model model) {
		logger.info("POST");
//		model.addAttribute("cause", exception.getMessage());
//		model.addAttribute("stackTrace", exception.getStackTrace().toString());
		return "error";
	}
}
