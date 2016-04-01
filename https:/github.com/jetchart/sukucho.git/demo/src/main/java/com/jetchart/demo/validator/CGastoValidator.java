package com.jetchart.demo.validator;

import java.math.BigDecimal;

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
		
//		if (gasto.getPrecio() != null && gasto.getPrecio().toString().indexOf(",") >= 0){
//			errors.rejectValue("precio","gasto.precio.formato", "Para separar los decimales debe hacerlo con punto y no con coma");
//		}else
		if (gasto.getPrecio() == null || gasto.getPrecio().compareTo(BigDecimal.valueOf(0)) != 1){
			errors.rejectValue("precio","gasto.precio.positivo", "El precio debe ser mayor a 0");
		}
		
	}
}
