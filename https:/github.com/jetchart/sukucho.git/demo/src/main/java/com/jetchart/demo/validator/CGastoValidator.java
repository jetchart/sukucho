package com.jetchart.demo.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.jetchart.demo.model.CGasto;

public class CGastoValidator implements Validator{

	@Override
	public boolean supports(Class<?> clase) {		
		return CGasto.class.equals(clase); // clase del bean al que da soporte este validador
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		CGasto gasto = (CGasto) target;
		
		/* Campos obligatorios */
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "descripcion", "gasto.descripcion.required", "La descripcion es obligatoria");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "precio", "gasto.precio.required", "El precio es obligatorio");
		
		if (!(gasto.getPrecio()>0)){
			errors.rejectValue("precio","gasto.precio.positivo", "El precio debe ser mayor a 0");
		}
		if (gasto.getPrecio().toString().indexOf(",") >= 0){
			errors.rejectValue("precio","gasto.precio.formato", "Para separar los decimales debe hacerlo con punto y no con coma");
		}
		
	}
}
