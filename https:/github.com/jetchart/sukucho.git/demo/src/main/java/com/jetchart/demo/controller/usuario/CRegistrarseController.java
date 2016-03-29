package com.jetchart.demo.controller.usuario;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

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

import com.jetchart.demo.model.CNivel;
import com.jetchart.demo.model.CUsuario;
import com.jetchart.demo.service.nivel.CNivelService;
import com.jetchart.demo.service.usuario.CUsuarioService;
import com.jetchart.demo.validator.CUsuarioValidator;

@Controller
@RequestMapping(value = "/registrarse")
public class CRegistrarseController {
	
	private static final Logger logger = LoggerFactory.getLogger(CRegistrarseController.class);
	
	@RequestMapping(method = RequestMethod.GET)
	public String doGet(String usuarioId, Model model, HttpServletRequest request){
		logger.info("GET");
		CUsuario usuario = null;
		try{
			logger.info("Registro de nuevo usuario");
			usuario = new CUsuario();
			CNivel nivel = new CNivel();
			nivel.setId(CNivel.ID_USUARIO);
			usuario.setNivel(nivel);
			usuario.setActivado(0);
			model.addAttribute("usuario", usuario);
			model.addAttribute("nivelDropDown", getNivelDropDown());
		}catch(Exception e){
			model.addAttribute("exception", e);
			return "redirect:error";
		}
		return "registrarse";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String doPost(@RequestParam(value = "accion") String accion,@ModelAttribute("usuario")  @Valid CUsuario usuario,
			BindingResult result, ModelMap model){
		logger.info("POST");
		try{
			if ("Registrarse".equals(accion)){
				model.addAttribute("nivelDropDown", getNivelDropDown());
				/* Valido errores */
				if (result.hasErrors())
			           return "usuario";
				if ("Registrarse".equals(accion)){
					CUsuarioService.insert(usuario);
					model.addAttribute("accionEjecutada", "Usuario registrado!<br>Se ha enviado un mail para activar su usuario.");
				}
				model.addAttribute("usuario", usuario);
				return "registrarse";
			}else if ("Volver".equals(accion)){
				return "redirect:index";
			}
		}catch(Exception e){
			model.addAttribute("exception", e);
			return "redirect:error";
		}
		return "registrarse";
	}
	
	@InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new CUsuarioValidator());
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
