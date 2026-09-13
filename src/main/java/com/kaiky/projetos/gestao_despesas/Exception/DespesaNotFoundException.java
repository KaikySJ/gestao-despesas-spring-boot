package com.kaiky.projetos.gestao_despesas.Exception;

public class DespesaNotFoundException extends RuntimeException {
    public DespesaNotFoundException(String message) {
        super(message);
    }

    public DespesaNotFoundException() {
        super("Despesa não encontrada");
    }
}
