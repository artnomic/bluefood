package br.com.artnomic.bluefood.infraestucture.web.validator;

import br.com.artnomic.bluefood.util.FileType;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Retention(RUNTIME)
@Target({ FIELD, METHOD })
@Constraint(validatedBy = UploadValidator.class)
public @interface UploadConstraint {

    String message() default "Arquivo inválido";
    FileType[] acceptedTypes();

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
