package com.jetchart.demo.Controller.Usuario;

import java.util.Collection;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import model.CUsuario;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jetchart.demo.Service.Usuario.CUsuarioService;
import com.jetchart.demo.Util.CHDAOService;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/listarUsuarios")
public class CABMUsuarioController {
	
	private static final Logger logger = LoggerFactory.getLogger(CABMUsuarioController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String doGET(HttpServletRequest request, Locale locale, Model model) throws Exception {
		logger.info("GET");
		String accion = request.getParameter("accion");
		if (accion == null){
			
		}else if ("Eliminar".equals(accion)){
			String id = request.getParameter("id");
			logger.info("Eliminar usuario con ID " + id);
			CUsuario usuario = (CUsuario) CHDAOService.findById(new CUsuario(), Integer.valueOf(id));
			CUsuarioService.delete(usuario);
			return "redirect:listarUsuarios";
		}else if ("Modificar".equals(accion)){
			String id = request.getParameter("id");
			logger.info("Modificar usuario con ID " + id);
			model.addAttribute("usuarioId",Integer.valueOf(id));
			return "redirect:usuario";
		}
//		Listar usuarios
		@SuppressWarnings("unchecked")
		Collection<CUsuario> usuarios = (Collection<CUsuario>) CHDAOService.findAll(new CUsuario());
		model.addAttribute("usuarios",usuarios);
		return "listarUsuarios";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String doPOST(@RequestParam(value = "accion") String accion, HttpServletRequest request, Locale locale, Model model) {
		logger.info("POST");
		if ("Crear".equals(accion)){
			logger.info("redirect:usuario");
			return "redirect:usuario";
		}else if ("Volver".equals(accion)){
			logger.info("redirect:index");
			return "redirect:index";
		}
		return "redirect:listarUsuarios";
	}
}
