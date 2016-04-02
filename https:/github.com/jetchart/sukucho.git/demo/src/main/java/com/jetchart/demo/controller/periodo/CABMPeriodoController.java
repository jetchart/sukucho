package com.jetchart.demo.controller.periodo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jetchart.demo.business.periodo.CPeriodoBusiness;
import com.jetchart.demo.business.usuario.CUsuarioBusiness;
import com.jetchart.demo.model.CEstadoPeriodo;
import com.jetchart.demo.model.CPeriodo;
import com.jetchart.demo.model.CUsuario;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/abmPeriodo")
public class CABMPeriodoController {
	
	private static final Logger logger = LoggerFactory.getLogger(CABMPeriodoController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String doGET(HttpServletRequest request, Model model, @ModelAttribute("periodoBuscado") CPeriodo periodoBuscado) throws Exception {
		logger.info("GET");
		showData(request, model, periodoBuscado);
		return "abmPeriodo";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String doPOST(@ModelAttribute("periodoBuscado") CPeriodo periodoBuscado, @RequestParam(value = "accion") String accion, HttpServletRequest request, Locale locale, Model model) throws Exception {
		logger.info("POST");
		if ("Buscar".equals(accion)){
			request.removeAttribute("mes");
			request.removeAttribute("anio");
			showData(request, model, periodoBuscado);
			logger.info("abmPeriodo");
			return "abmPeriodo";
		} else if ("Volver".equals(accion)){
			model.addAttribute("mes",periodoBuscado.getMes());
			model.addAttribute("anio",periodoBuscado.getAnio());
			return "redirect:abmGasto";
		}
		return "redirect:abmPeriodo";
	}
	
	private void showData(HttpServletRequest request, Model model, CPeriodo periodoBuscado) throws Exception{
		/* Verifico si se indicó periodo */
		CPeriodo periodo = new CPeriodo();
		Collection<CUsuario> usuariosPeriodo = new ArrayList<CUsuario>();
		if (periodoBuscado.getMes() != null && periodoBuscado.getAnio() != null){
			periodo = new CPeriodoBusiness().getPeriodoByMesAndAnio(periodoBuscado.getMes(), periodoBuscado.getAnio());
		}else{
			periodo = new CPeriodoBusiness().getPeriodoVigente();
			/* Si no se seleccionó mes ni año, se colocan los actuales */
			DateTime fechaActual = new DateTime(System.currentTimeMillis());
			periodoBuscado.setMes(fechaActual.getMonthOfYear());
			periodoBuscado.setAnio(fechaActual.getYear());
		}
		/* Verifico que el periodo no sea null */
		if (periodo != null){
			usuariosPeriodo = new CUsuarioBusiness().findUsuariosByPeriodo(periodo);
		}
		/* Periodo buscado */
		model.addAttribute("periodoBuscado",periodoBuscado);
		model.addAttribute("periodo",periodo);
		/* Dejo cargado el mes y anio */
		model.addAttribute("usuariosPeriodo",usuariosPeriodo);
		model.addAttribute("mesDropDown", new CPeriodoBusiness().getMesDropDown());
		model.addAttribute("anioDropDown", new CPeriodoBusiness().getAnioDropDown());
		/* Coloco constantes */
		model.addAttribute("ID_VIGENTE",CEstadoPeriodo.ID_VIGENTE);
		model.addAttribute("ID_CERRADO",CEstadoPeriodo.ID_CERRADO);
	}
}
