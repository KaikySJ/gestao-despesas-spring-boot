package com.kaiky.projetos.gestao_despesas.validation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Constraint(validatedBy = ValueOfEnumValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValueOfEnum {

    Class<? extends Enum<?>> enumClass();

    String message() default "Tipo inválido!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
