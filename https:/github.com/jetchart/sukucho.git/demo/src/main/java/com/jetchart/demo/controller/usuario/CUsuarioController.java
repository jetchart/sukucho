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
import com.jetchart.demo.util.CHDAOService;
import com.jetchart.demo.util.CUtil;
import com.jetchart.demo.validator.CUsuarioValidator;

@Controller
@RequestMapping(value = "/usuario")
public class CUsuarioController {
	
	private static final Logger logger = LoggerFactory.getLogger(CUsuarioController.class);
	
	@RequestMapping(method = RequestMethod.GET)
	public String doGet(String usuarioId, Model model, HttpServletRequest request){
		logger.info("GET");
		CUsuario usuario = null;
		try{
			if (!CUtil.puedeEditarUsuario(request)){
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
			model.addAttribute("passwordOriginal",usuario.getContrasenia());
			model.addAttribute("usuario", usuario);
			model.addAttribute("nivelDropDown", getNivelDropDown());
		}catch(Exception e){
			request.getSession(true).setAttribute("exception", e);
			return "redirect:error";
		}
		return "usuario";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String doPost(HttpServletRequest request, @RequestParam(value = "accion") String accion, @ModelAttribute("usuario")  @Valid CUsuario usuario,
			BindingResult result, ModelMap model){
//		@ModelAttribute("passwordOriginal") String passwordOriginal
		logger.info("POST");
			try{
			if ("Guardar".equals(accion) || "Modificar".equals(accion)){
				model.addAttribute("nivelDropDown", getNivelDropDown());
				/* Valido errores */
				if (result.hasErrors())
			           return "usuario";
				/* Si cambió la contraseña, la encripto */
//				if (!passwordOriginal.equals(usuario.getContrasenia())){
//					String passEncriptada = CUtil.encriptarClave(usuario.getContrasenia());
//					usuario.setContrasenia(passEncriptada);
//				}
				if ("Guardar".equals(accion)){
					CUsuarioService.insert(usuario);
					model.addAttribute("accionEjecutada", "Usuario creado!<br>Se ha enviado un mail para activar su usuario.");
				}
				if ("Modificar".equals(accion)){
					CUsuarioService.update(usuario);
					model.addAttribute("accionEjecutada", "Usuario modificado");
				}
				model.addAttribute("usuario", usuario);
				return "usuario";
			}else if ("Volver".equals(accion)){
				return "redirect:listarUsuarios";
			}
		}catch(Exception e){
			request.getSession(true).setAttribute("exception", e);
			return "redirect:error";
		}	
		return "usuario";
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
