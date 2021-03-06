package com.jetchart.demo.controller.gasto;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jetchart.demo.business.periodo.CPeriodoBusiness;
import com.jetchart.demo.model.CGasto;
import com.jetchart.demo.model.CPeriodo;
import com.jetchart.demo.model.CUsuario;
import com.jetchart.demo.service.gasto.CGastoService;
import com.jetchart.demo.service.periodo.CPeriodoService;
import com.jetchart.demo.util.CHDAOService;
import com.jetchart.demo.util.CUtil;
import com.jetchart.demo.validator.CGastoValidator;
import com.jetchart.demo.validator.CUsuarioValidator;

@Controller
@RequestMapping(value = "/gasto")
public class CGastoController {
	
	private static final Logger logger = LoggerFactory.getLogger(CGastoController.class);
	
	@RequestMapping(method = RequestMethod.GET)
	public String doGet(String gastoId, Model model, HttpServletRequest request) throws Exception {
		logger.debug("GET");
		CGasto gasto = null;
		if ("new".equals(gastoId) || gastoId == null ){
			logger.debug("Creacion de nuevo gasto");
			gasto = new CGasto();
			CUsuario usuarioLogueado = (CUsuario) request.getSession(true).getAttribute("usuario");
			gasto.setUsuario(usuarioLogueado);
			gasto.setFecha(new Timestamp(System.currentTimeMillis()));
			gasto.setPrecio(BigDecimal.valueOf(0));
			CPeriodo periodo = new CPeriodoBusiness().getPeriodoVigente();
			gasto.setPeriodo(periodo);
		}else if (!CUtil.puedeEditarGasto(request, gastoId)){
			logger.debug("No tiene permisos");
			return "redirect:errorPermiso";
		}else {
			logger.debug("Modificar gasto con id=gastoId");
			gasto = (CGasto) CHDAOService.findById(new CGasto(), Integer.valueOf(gastoId));
		}
		model.addAttribute("gasto", gasto);
		return "gasto";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String doPost(@RequestParam(value = "accion") String accion, @ModelAttribute("gasto") @Valid CGasto gasto, 
			BindingResult result, ModelMap model) throws Exception {
		logger.debug("POST");
		if ("Guardar".equals(accion) || "Modificar".equals(accion)){
			/* Valido errores */
			if (result.hasErrors())
		           return "gasto";
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
	
	@InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new CGastoValidator());
    }

}
