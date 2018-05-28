package Modelo;

import java.util.ArrayList;

/**
 *
 * @author Vitor
 */
public class bloco {
    private String nome;
    private Integer estado_inicial;
    
    private ArrayList<TransicaoBloco> transicoesBlocos = new ArrayList<>();
    private ArrayList<TransicaoSimples> transicoesSimples = new ArrayList<>();
    
    public bloco() {
    }

    public bloco(String nome, Integer estado_inicial) {
        this.nome = nome;
        this.estado_inicial = estado_inicial;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getEstado_inicial() {
        return estado_inicial;
    }

    public void setEstado_inicial(Integer estado_inicial) {
        this.estado_inicial = estado_inicial;
    }

    public ArrayList<TransicaoBloco> getTransicoesBlocos() {
        return transicoesBlocos;
    }

    public void setTransicoesBlocos(ArrayList<TransicaoBloco> transicoesBlocos) {
        this.transicoesBlocos = transicoesBlocos;
    }

    public ArrayList<TransicaoSimples> getTransicoesSimples() {
        return transicoesSimples;
    }

    public void setTransicoesSimples(ArrayList<TransicaoSimples> transicoesSimples) {
        this.transicoesSimples = transicoesSimples;
    }

    

}
