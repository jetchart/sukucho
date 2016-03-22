package com.jetchart.demo.Controller.Usuario;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import model.CNivel;
import model.CUsuario;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jetchart.demo.Service.Usuario.CNivelService;
import com.jetchart.demo.Service.Usuario.CUsuarioService;
import com.jetchart.demo.Util.CHDAOService;
import com.jetchart.demo.Util.CPersistenceUtil;
import com.jetchart.demo.Util.CUtil;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/usuario")
public class CUsuarioController {
	
	private static final Logger logger = LoggerFactory.getLogger(CUsuarioController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String doGet(Integer usuarioId, Model model, HttpServletRequest request) throws Exception {
		logger.info("GET");
		CUsuario usuario = null;
		if (usuarioId != null){
			/* Validar si puede editar */
			if (!CUtil.puedeEditar(request, usuarioId)){
				logger.info("No tiene permisos");
				return "redirect:errorPermiso";
			}				
			usuario = (CUsuario) CHDAOService.findById(new CUsuario(), usuarioId);
		}
		if (usuario == null){
			usuario = new CUsuario();
		}
		model.addAttribute("usuario", usuario);
		model.addAttribute("nivelDropDown", getNivelDropDown());
		return "usuario";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String doPost(@RequestParam(value = "accion") String accion, CUsuario usuario, HttpServletRequest request, Locale locale, Model model) throws Exception {
		logger.info("POST");
		if ("Guardar".equals(accion) || "Modificar".equals(accion)){
			if ("Guardar".equals(accion)){
				CUsuarioService.insert(usuario);
			}
			if ("Modificar".equals(accion)){
				CUsuarioService.update(usuario);
			}
			model.addAttribute("accionEjecutada", "Guardado!");
			model.addAttribute("nivelDropDown", getNivelDropDown());
			model.addAttribute("usuario", usuario);
			return "usuario";
		}else if ("Volver".equals(accion)){
			return "redirect:listarUsuarios";
		}
		return "usuario";
	}
	
	private Map<Integer,String> getNivelDropDown() throws Exception{
		Collection<CNivel> colNivel = CNivelService.getAllNivel();
		Map<Integer,String> nivelDropDown = new LinkedHashMap<Integer,String>();
		for(CNivel nivel : colNivel){
			nivelDropDown.put(nivel.getId(), nivel.getDescripcion());
		}
		return nivelDropDown;
	}
}
