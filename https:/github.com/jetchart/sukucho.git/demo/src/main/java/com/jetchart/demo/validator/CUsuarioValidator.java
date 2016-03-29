package com.jetchart.demo.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.jetchart.demo.model.CUsuario;
import com.jetchart.demo.service.usuario.CUsuarioService;

public class CUsuarioValidator implements Validator{

	@Override
	public boolean supports(Class<?> clase) {		
		return CUsuario.class.equals(clase); // clase del bean al que da soporte este validador
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		CUsuario usuario = (CUsuario) target;
		
		/* Campos obligatorios */
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "usuario.nombre.required", "El nombre es obligatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "apellido", "usuario.apellido.required", "El apellido es obligatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "usuario.email.required", "El email es obligatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "contrasenia", "usuario.contrasenia.required", "La contraseña es obligatoria");
		/* Longitud de la contraseña */ 
		if (usuario.getContrasenia().length() < 8 || usuario.getContrasenia().length() > 24)
			errors.rejectValue("contrasenia", "usuario.contrasenia.longitud", "La contraseña debe tener una longitud entre 8 y 24 caracteres");
		
		/* Verificar que no haya otra persona con el mismo email */
		try {
			if (CUsuarioService.existsPersonaByEmail(usuario))
				errors.rejectValue("email", "usuario.email.unique", "Ya existe un usuario registrado con ese email");
		} catch (Exception e) {
			e.printStackTrace();
		}

		
	}
}
