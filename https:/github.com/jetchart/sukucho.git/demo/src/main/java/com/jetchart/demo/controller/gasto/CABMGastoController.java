package com.jetchart.demo.controller.gasto;

import java.math.BigDecimal;
import java.math.RoundingMode;
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

import com.jetchart.demo.business.gasto.CGastoBusiness;
import com.jetchart.demo.business.periodo.CPeriodoBusiness;
import com.jetchart.demo.business.usuario.CUsuarioBusiness;
import com.jetchart.demo.model.CEstadoPeriodo;
import com.jetchart.demo.model.CGasto;
import com.jetchart.demo.model.CPeriodo;
import com.jetchart.demo.model.CUsuario;
import com.jetchart.demo.service.gasto.CGastoService;
import com.jetchart.demo.service.usuario.CUsuarioService;
import com.jetchart.demo.util.CHDAOService;
import com.jetchart.demo.util.CUtil;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/abmGasto")
public class CABMGastoController {
	
	private static final Logger logger = LoggerFactory.getLogger(CABMGastoController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String doGET(HttpServletRequest request, Model model, @ModelAttribute("periodoBuscado") CPeriodo periodoBuscado) throws Exception {
		logger.debug("GET");
		String accion = request.getParameter("accion");
		if ("Eliminar".equals(accion)){
			String id = request.getParameter("id");
			if (!CUtil.puedeEditarGasto(request, id)){
				logger.debug("No tiene permisos");
				return "redirect:errorPermiso";
			}
			logger.debug("Eliminar gasto con Id " + id);
			CGasto gasto = (CGasto) CHDAOService.findById(new CGasto(), Integer.valueOf(id));
			CUsuarioService.delete(gasto);
			return "redirect:abmGasto";
		}else if ("Modificar".equals(accion)){
			String id = request.getParameter("id");
			logger.debug("Modificar gasto con Id " + id);
			model.addAttribute("gastoId",id);
			return "redirect:gasto";
		}
		showData(request, model, periodoBuscado);
		return "abmGasto";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String doPOST(@ModelAttribute("periodoBuscado") CPeriodo periodoBuscado, @RequestParam(value = "accion") String accion, HttpServletRequest request, Locale locale, Model model) throws Exception {
		logger.debug("POST");
		if ("Registrar gasto".equals(accion)){
			logger.debug("redirect:gasto");
			model.addAttribute("gastoId", "new");
			return "redirect:gasto";
		}else if ("Buscar".equals(accion)){
			showData(request, model, periodoBuscado);
			logger.debug("abmGasto");
			return "abmGasto";
		} else if ("Volver".equals(accion)){
			logger.debug("redirect:index");
			return "redirect:index";
		}
		return "redirect:abmGasto";
	}
	
	private void showData(HttpServletRequest request, Model model, CPeriodo periodoBuscado) throws Exception{
		/* Verifico si se indicó periodo */
		CPeriodo periodo = new CPeriodo();
		Collection<CUsuario> usuariosPeriodo = new ArrayList<CUsuario>();
		if (periodoBuscado.getMes() != null && periodoBuscado.getAnio() != null){
			periodo = new CPeriodoBusiness().getPeriodoByMesAndAnioNoFuturo(periodoBuscado.getMes(), periodoBuscado.getAnio());
		}else{
			periodo = new CPeriodoBusiness().getPeriodoVigente();
			/* Si no se seleccionó mes ni año, se colocan los actuales */
			DateTime fechaActual = new DateTime(System.currentTimeMillis());
			periodoBuscado.setMes(fechaActual.getMonthOfYear());
			periodoBuscado.setAnio(fechaActual.getYear());
		}
		/* Verifico que el periodo no sea null */
		if (periodo != null){
			/* El usuario logueado debe participar del periodo, sino no se debe mostrar 
			 * la informacion */
			CUsuario usuario = (CUsuario) request.getSession(true).getAttribute("usuario");
			if (CUsuarioService.isUsuarioParticipantePeriodo(usuario, periodo)){
				/* Gastos por periodo */
				model.addAttribute("periodo",periodo);
				Collection<CGasto> gastos = (Collection<CGasto>) CGastoService.findByPeriodo(periodo);
				model.addAttribute("gastos",gastos);
				/* Gasto por persona y periodo */
				Collection<Object[]> personaGastos = (Collection<Object[]>) CGastoService.findTotalPersonasByPeriodo(periodo);
				model.addAttribute("personaGastos",personaGastos);
				/* Estado del periodo */
				model.addAttribute("estadoPeriodo",new CGastoBusiness().getEstadoPeriodo(periodo));
				/* Total */
				BigDecimal totalPeriodo = BigDecimal.valueOf(0);
				for (CGasto gasto : gastos){
					totalPeriodo = totalPeriodo.add(gasto.getPrecio());
				}
				model.addAttribute("totalPeriodo",totalPeriodo);
				/* TODO Una cosa:
				 * 		1- Ojo con la division por cero
				*/
				@SuppressWarnings("unchecked")
				Collection<CUsuario> usuarios = new CUsuarioBusiness().findUsuariosByPeriodo(periodo);
				model.addAttribute("cantidadPersonas", usuarios.size());
				BigDecimal montoPorPersona = totalPeriodo.divide(BigDecimal.valueOf(usuarios.size()), RoundingMode.HALF_UP);
				model.addAttribute("montoPorPersona", montoPorPersona);
			}
				usuariosPeriodo = new CUsuarioBusiness().findUsuariosByPeriodo(periodo);
		}
		/* Dejo cargado el mes y anio y cargo mas datos al modelo */
		model.addAttribute("periodoBuscado",periodoBuscado);
		model.addAttribute("usuariosPeriodo",usuariosPeriodo);
				model.addAttribute("mesDropDown", new CPeriodoBusiness().getMesDropDown());
		model.addAttribute("anioDropDown", new CPeriodoBusiness().getAnioDropDown());
		/* Coloco constantes */
		model.addAttribute("ID_VIGENTE",CEstadoPeriodo.ID_VIGENTE);
		model.addAttribute("ID_CERRADO",CEstadoPeriodo.ID_CERRADO);
	}
}
