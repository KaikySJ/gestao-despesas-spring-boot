package com.kaiky.projetos.gestao_despesas.ExceptionHandler;

import com.kaiky.projetos.gestao_despesas.Exception.DespesaInvalidStatusException;

import com.kaiky.projetos.gestao_despesas.Exception.DespesaNotFoundException;
import org.jspecify.annotations.Nullable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DespesaInvalidStatusException.class)
    public ResponseEntity<String> DespesaInvalidStatusHandler(DespesaInvalidStatusException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tipo de status da despesa é invalido");
    }


    @ExceptionHandler(DespesaNotFoundException.class)
    public ResponseEntity<String> DespesaNotFoundHandler(DespesaNotFoundException despesaNotFoundException){
     return ResponseEntity.status(HttpStatus.NOT_FOUND).body(despesaNotFoundException.getMessage());
    }
}
