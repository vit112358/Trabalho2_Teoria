package Modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Vitor
 */
public class MT {

    private Map<String, bloco> lista_blocos;
    private Integer cabecote;
    private ArrayList<Character> fita = new ArrayList<>();

    public MT() {

    }

    public Map<String, bloco> getLista_blocos() {
        return lista_blocos;
    }

    public void setLista_blocos(Map<String, bloco> lista_blocos) {
        this.lista_blocos = lista_blocos;
    }

    /**
     * Computa a palavra passada para a MT definida
     *
     * @param palavra palavra a ser computada
     * @param lista_blocos blocos da máquina
     * @param contador contador de passos
     * @param r modo resume: executa o programa até o fim em modo silencioso e
     * depois imprime o conteúdo final da fita.
     * @param v modo verbose: mostra a execução passo a passo do programa até o
     * fim.
     */
    public void computacao(String palavra, Map<String, bloco> lista_blocos, Integer contador, boolean r, boolean v, String delimitadores) {
        cabecote = 20;
        boolean parada = false;
        char[] del = new char[2];

        int estado_atual;
        int estado_retorno = 0;
        del[0] = delimitadores.charAt(0);
        del[1] = delimitadores.charAt(1);
        //em toda a contagem irá apontar para o começo da palavra
        int aux = 0;
        //Quebrando a palavra em caracteres
        char[] auxiliar = palavra.toCharArray();
        int size_aux_cabecote = auxiliar.length + 2;
        //colocando cada caracter na fita
        for (int i = 0; i < 43; i++) {
            if (i < 20) {
                fita.add('_');
            } else if (i >= 20 && aux < auxiliar.length) {
                //if (i == cabecote-1) {
                    //fita.add(del[0]);
                //} else if (cabecote+1 == i) {
                    //fita.add(del[1]);
                //} else {
                    fita.add(auxiliar[aux]);
                    aux++;
                //}
            } else {
                fita.add('_');
            }

        }
        //DEBUG
        fita.forEach((c) -> {
            System.out.print(c);
        });
        System.out.println("");

        bloco bloco_aux = lista_blocos.get("main");
        estado_atual = bloco_aux.getEstado_inicial();

        while (estado_atual != -2) {
            if(estado_atual == -1){
                estado_atual = estado_retorno;
                bloco_aux = lista_blocos.get("main");
            }
            for (TransicaoSimples t : bloco_aux.getTransicoesSimples()) {
                if (t.getOrigem().equals(estado_atual) && fita.get(cabecote).equals(t.getLeitura().charAt(0))) {
                    if (fita.get(cabecote).equals(t.getLeitura().charAt(0))) {
                        fita.set(cabecote, t.getEscrita().charAt(0));
                        fita.forEach((c) -> {
                            System.out.print(c);
                        });
                        System.out.println("");
                    }
                    estado_atual = t.getDestino();
                    if (t.getDirecao_cabecote().equals("d")) {
                        cabecote++;
                    } else if (t.getDirecao_cabecote().equals("e")) {
                        cabecote--;
                    }
                    break;
                }
            }

            for (TransicaoBloco t : bloco_aux.getTransicoesBlocos()) {
                if (t.getOrigem().equals(estado_atual)) {
                    estado_retorno = t.getDestino();

                    bloco_aux = lista_blocos.get(t.getBloco());

                    estado_atual = bloco_aux.getEstado_inicial();
                    while (estado_atual != -1) {
                        for (TransicaoSimples t1 : bloco_aux.getTransicoesSimples()) {
                            if (t1.getLeitura().equals("*")) {
                                if (t1.getEscrita().equals("*")) {

                                } else {
                                    fita.set(cabecote, t1.getEscrita().charAt(0));
                                }
                                fita.forEach((c) -> {
                                    System.out.print(c);
                                });
                                System.out.println("");
                                estado_atual = t1.getDestino();
                                if (t1.getDirecao_cabecote().equals("d")) {
                                    cabecote++;
                                } else if (t1.getDirecao_cabecote().equals("e")) {
                                    cabecote--;
                                }
                            } else {
                                if (t1.getOrigem().equals(estado_atual) && fita.get(cabecote).equals(t1.getLeitura().charAt(0))) {
                                    if (fita.get(cabecote).equals(t1.getLeitura().charAt(0))) {
                                        if (!t1.getEscrita().equals("*")) {
                                            fita.set(cabecote, t1.getEscrita().charAt(0));
                                        }

                                        fita.forEach((c) -> {
                                            System.out.print(c);
                                        });
                                        System.out.println("");
                                    }
                                    estado_atual = t1.getDestino();
                                    if (t1.getDirecao_cabecote().equals("d")) {
                                        cabecote++;
                                    } else if (t1.getDirecao_cabecote().equals("e")) {
                                        cabecote--;
                                    }       
                                }
                            }
                            if (estado_atual == -1) {
                                break;
                            }
                        }
                    }
                }
            }
        }
    }
}
