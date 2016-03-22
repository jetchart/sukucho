package com.jetchart.demo.controller.usuario;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.jetchart.demo.model.CNivel;
import com.jetchart.demo.model.CUsuario;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jetchart.demo.service.nivel.CNivelService;
import com.jetchart.demo.service.usuario.CUsuarioService;
import com.jetchart.demo.util.CHDAOService;
import com.jetchart.demo.util.CUtil;

@Controller
@RequestMapping(value = "/usuario")
public class CUsuarioController {
	
	private static final Logger logger = LoggerFactory.getLogger(CUsuarioController.class);
	
	@RequestMapping(method = RequestMethod.GET)
	public String doGet(String usuarioId, Model model, HttpServletRequest request) throws Exception {
		logger.info("GET");
		CUsuario usuario = null;
		if (!CUtil.puedeEditar(request)){
			logger.info("No tiene permisos, se le ofrece modificar sus propios datos");
			usuario = (CUsuario) request.getSession(true).getAttribute("usuario");
		}else if ("new".equals(usuarioId)){
			logger.info("Creacion de nuevo usuario");
			usuario = new CUsuario();
		}else if (usuarioId == null){
			logger.info("No se indicó usuarioId, se le ofrece modificar sus propios datos");
			usuario = (CUsuario) request.getSession(true).getAttribute("usuario");
		}else{
			logger.info("Modificar usuario con id=usuarioId");
			usuario = (CUsuario) CHDAOService.findById(new CUsuario(), Integer.valueOf(usuarioId));
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
				model.addAttribute("accionEjecutada", "Usuario creado");
			}
			if ("Modificar".equals(accion)){
				CUsuarioService.update(usuario);
				model.addAttribute("accionEjecutada", "Usuario modificado");
			}
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
