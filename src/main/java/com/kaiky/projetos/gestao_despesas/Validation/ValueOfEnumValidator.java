package com.kaiky.projetos.gestao_despesas.Validation;

import java.util.Arrays;
import java.util.List;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import static java.util.stream.Collectors.toList;

public class ValueOfEnumValidator implements ConstraintValidator<ValueOfEnum, String> {

    private List<String> valoresAceitos;

    @Override
    public void initialize(ValueOfEnum annotation) {
        valoresAceitos = Arrays.stream(annotation.enumClass().getEnumConstants())
                .map(Enum::name)
                .toList();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if(value == null){
            return true;
        }

        if(!valoresAceitos.contains(value)){
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate("Status inválido")
                    .addConstraintViolation();
            return false;
        }

        return true;
    }
}
