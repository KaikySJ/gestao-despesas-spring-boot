package com.kaiky.projetos.gestao_despesas.model;

import com.kaiky.projetos.gestao_despesas.enums.TipoGasto;
import jakarta.persistence.*;


@Entity
@Table(name = "Despesas")
public class DespesaModel {

    public DespesaModel(String nome, String motivo, double valor, TipoGasto tipo) {
        this.nome = nome;
        this.motivo = motivo;
        this.valor = valor;
        this.tipo = tipo;
    }

    public DespesaModel() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;
    @Column(name = "motivo")
    private String motivo;
    @Column(name = "valor")
    private double valor;

    @Column(name = "tipo")
    @Enumerated
    private TipoGasto tipo;


    public String getNome() {
        return nome;
    }

    public Long getId() {
        return id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public TipoGasto getTipo() {
        return tipo;
    }

    public void setTipo(TipoGasto tipo) {
        this.tipo = tipo;
    }
}
