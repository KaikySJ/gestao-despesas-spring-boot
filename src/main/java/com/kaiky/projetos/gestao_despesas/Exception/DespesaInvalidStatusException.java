package com.kaiky.projetos.gestao_despesas.Exception;

public class DespesaInvalidStatusException extends RuntimeException{
    public DespesaInvalidStatusException(){
        super("Tipo de status para despesa inválido!");
    }

    public DespesaInvalidStatusException(String message){
        super(message);
    }

}
