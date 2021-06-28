package ar.edu.unju.edm.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = ValidEmail.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)

public @interface validacionEmail {
	
	String message() default "Debe ingresar con @gmail - @ facebook";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
