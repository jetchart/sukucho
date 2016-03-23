package com.jetchart.demo.controller.gasto;

import java.util.Collection;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import com.jetchart.demo.business.gasto.CGastoBusiness;
import com.jetchart.demo.model.CGasto;
import com.jetchart.demo.model.CPeriodo;
import com.jetchart.demo.model.CUsuario;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
	public String doGET(HttpServletRequest request, Model model) throws Exception {
		logger.info("GET");
		String accion = request.getParameter("accion");
		if ("Eliminar".equals(accion)){
			String id = request.getParameter("id");
			if (!CUtil.puedeEditarGasto(request, id)){
				logger.info("No tiene permisos");
				return "redirect:errorPermiso";
			}
			logger.info("Eliminar gasto con Id " + id);
			CGasto gasto = (CGasto) CHDAOService.findById(new CGasto(), Integer.valueOf(id));
			CUsuarioService.delete(gasto);
			return "redirect:abmGasto";
		}else if ("Modificar".equals(accion)){
			String id = request.getParameter("id");
			logger.info("Modificar gasto con Id " + id);
			model.addAttribute("gastoId",id);
			return "redirect:gasto";
		}
		showData(request, model);
		return "abmGasto";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String doPOST(@RequestParam(value = "accion") String accion, HttpServletRequest request, Locale locale, Model model) throws Exception {
		logger.info("POST");
		if ("Registrar gasto".equals(accion)){
			logger.info("redirect:gasto");
			model.addAttribute("gastoId", "new");
			return "redirect:gasto";
		}else if ("Buscar".equals(accion)){
			showData(request, model);
			logger.info("abmGasto");
			return "abmGasto";
		} else if ("Volver".equals(accion)){
			logger.info("redirect:index");
			return "redirect:index";
		}
		return "redirect:abmGasto";
	}
	
	private void showData(HttpServletRequest request, Model model) throws Exception{
		/* Verifico si se indicó periodo */
		CPeriodo periodo = new CPeriodo();
		String mes = request.getParameter("mes");
		String anio = request.getParameter("anio");
		if (mes != null && anio != null){
			periodo.setMes(Integer.valueOf(mes));
			periodo.setAnio(Integer.valueOf(anio));
		}else{
			periodo = new CGastoBusiness().getPeriodoVigente();
		}
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
		Float totalPeriodo = Float.valueOf(0);
		for (CGasto gasto : gastos){
			totalPeriodo += gasto.getPrecio();
		}
		model.addAttribute("totalPeriodo",totalPeriodo);
		/* TODO Dos cosas:
		 * 		1- Deben obtenerse solo los usuarios que participan del periodo (por ahora todos)
		 * 		2- Ojo con la division por cero
		*/
		@SuppressWarnings("unchecked")
		Collection<CUsuario> usuarios = CHDAOService.findAll(new CUsuario());
		model.addAttribute("cantidadPersonas", usuarios.size());
		Float montoPorPersona = totalPeriodo / usuarios.size();
		model.addAttribute("montoPorPersona", montoPorPersona);
	}
}
