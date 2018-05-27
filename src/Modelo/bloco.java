package Modelo;

import java.util.ArrayList;

/**
 *
 * @author Vitor
 */
public class bloco {
    private String nome;
    private Integer estado_inicial;
    
    private ArrayList<Transicao> transicoes = new ArrayList<>();

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

    public ArrayList<Transicao> getTransicoes() {
        return transicoes;
    }

    public void setTransicoes(ArrayList<Transicao> transicoes) {
        this.transicoes = transicoes;
    }

}
