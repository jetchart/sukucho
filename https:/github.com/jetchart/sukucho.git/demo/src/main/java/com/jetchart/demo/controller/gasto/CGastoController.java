package com.jetchart.demo.controller.gasto;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jetchart.demo.model.CGasto;
import com.jetchart.demo.model.CUsuario;
import com.jetchart.demo.service.gasto.CGastoService;
import com.jetchart.demo.util.CHDAOService;
import com.jetchart.demo.util.CUtil;

@Controller
@RequestMapping(value = "/gasto")
public class CGastoController {
	
	private static final Logger logger = LoggerFactory.getLogger(CGastoController.class);
	
	@RequestMapping(method = RequestMethod.GET)
	public String doGet(String gastoId, Model model, HttpServletRequest request) throws Exception {
		logger.info("GET");
		CGasto gasto = null;
		if ("new".equals(gastoId) || gastoId == null ){
			logger.info("Creacion de nuevo gasto");
			gasto = new CGasto();
			CUsuario usuarioLogueado = (CUsuario) request.getSession(true).getAttribute("usuario");
			gasto.setUsuario(usuarioLogueado);
			gasto.setFecha(new Timestamp(System.currentTimeMillis()));
			gasto.setPrecio(0);
		}else if (!CUtil.puedeEditarGasto(request, gastoId)){
			logger.info("No tiene permisos");
			return "redirect:errorPermiso";
		}else {
			logger.info("Modificar gasto con id=gastoId");
			gasto = (CGasto) CHDAOService.findById(new CGasto(), Integer.valueOf(gastoId));
		}
		model.addAttribute("gasto", gasto);
		return "gasto";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String doPost(@RequestParam(value = "accion") String accion, CGasto gasto, HttpServletRequest request, Model model) throws Exception {
		logger.info("POST");
		if ("Guardar".equals(accion) || "Modificar".equals(accion)){
			if ("Guardar".equals(accion)){
				CGastoService.insert(gasto);
				model.addAttribute("accionEjecutada", "Gasto creado");
			}
			if ("Modificar".equals(accion)){
				CGastoService.update(gasto);
				model.addAttribute("accionEjecutada", "Gasto modificado");
			}
			model.addAttribute("gasto", gasto);
			return "gasto";
		}else if ("Volver".equals(accion)){
			return "redirect:abmGasto";
		}
		return "gasto";
	}
}
