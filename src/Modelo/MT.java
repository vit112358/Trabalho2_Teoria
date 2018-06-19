package Modelo;

import java.util.ArrayList;
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
     * @param delimitadores delimitadores do cabeçote
     */
    public void computacaoVerbose(String palavra, Map<String, bloco> lista_blocos, Integer contador, String delimitadores) {
        cabecote = 20;
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
        System.out.print("Inicio: ");
        fita.forEach((c) -> {
            System.out.print(c);
        });
        System.out.println("");

        bloco blocoAtual = lista_blocos.get("main");
        estado_atual = blocoAtual.getEstado_inicial();

        /*Enquanto não parar*/
        while (estado_atual != -2) {
            /*Saindo do bloco, ou seja, voltando para o main*/
            if (estado_atual == -1) {
                estado_atual = estado_retorno;
                blocoAtual = lista_blocos.get("main");
            }

            /*Procurando a transicao Simples correspondente*/
            for (TransicaoSimples t : blocoAtual.getTransicoesSimples()) {
                /*Se a origem da minha transicao for igual ao estado que estou 
                e o caractere que está na posicao sob o cabecote for igual a minha escrita
                então posso computar
                 */
                if (t.getOrigem().equals(estado_atual) && fita.get(cabecote).equals(t.getLeitura().charAt(0))
                        || (t.getOrigem().equals(estado_atual) && t.getLeitura().equals("*"))) {
                    estado_atual = t.getDestino();
                    if (fita.get(cabecote).equals(t.getLeitura().charAt(0))) {
                        if (t.getEscrita().equals("*")) {

                        } else {
                            fita.set(cabecote, t.getEscrita().charAt(0));
                        }
                  
                        System.out.print(blocoAtual.getNome() + ".");
                        System.out.print(estado_atual + ":");

                        fita.forEach((c) -> {
                            System.out.print(c);
                        });
                        System.out.println("");
                    }

                    if (t.getDirecao_cabecote().equals("d")) {
                        cabecote++;
                    } else if (t.getDirecao_cabecote().equals("e")) {
                        cabecote--;
                    }
                    break;
                }
            }

            /*Procurando a transicao que estrará em um bloco correspondente*/
            for (TransicaoBloco tb : blocoAtual.getTransicoesBlocos()) {
                /*se a origem da miha transicao for igual ao estado em que estou*/
                if (tb.getOrigem().equals(estado_atual)) {
                    /*armazenando o estado que deverá ser retornado quando sair do bloco*/
                    estado_retorno = tb.getDestino();
                    /*bloco em que eu irei entrar*/
                    blocoAtual = lista_blocos.get(tb.getBloco());
                    /*atualizando o estado atual para o estado inicial do bloco*/
                    estado_atual = blocoAtual.getEstado_inicial();

                    /*enquando eu nao chegar no retorne faça
                    entrando em um bloco
                     */
                    while (estado_atual != -1) {
                        for (TransicaoSimples t1 : blocoAtual.getTransicoesSimples()) {
                            if (t1.getLeitura().equals("*")) {
                                if (t1.getEscrita().equals("*")) {

                                } else {
                                    fita.set(cabecote, t1.getEscrita().charAt(0));
                                }
                                estado_atual = t1.getDestino();
                                System.out.print(blocoAtual.getNome() + ".");
                                System.out.print(estado_atual + ":");
                                fita.forEach((c) -> {
                                    System.out.print(c);
                                });
                                System.out.println("");
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
                                        estado_atual = t1.getDestino();
                                        System.out.print(blocoAtual.getNome() + ".");
                                        System.out.print(estado_atual + ":");
                                        fita.forEach((c) -> {
                                            System.out.print(c);
                                        });
                                        System.out.println("");
                                    }
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

                        for (TransicaoBloco tb1 : blocoAtual.getTransicoesBlocos()) {
                            if (tb1.getOrigem().equals(estado_atual)) {
                                estado_retorno = tb.getDestino();
                                /*bloco em que eu irei entrar*/
                                blocoAtual = lista_blocos.get(tb.getBloco());
                                /*atualizando o estado atual para o estado inicial do bloco*/
                                estado_atual = blocoAtual.getEstado_inicial();

                                /*enquando eu nao chegar no retorne faça
                                entrando em um bloco
                                 */
                                while (estado_atual != -1) {
                                    for (TransicaoSimples t1 : blocoAtual.getTransicoesSimples()) {
                                        if (t1.getLeitura().equals("*")) {
                                            if (t1.getEscrita().equals("*")) {

                                            } else {
                                                fita.set(cabecote, t1.getEscrita().charAt(0));
                                            }
                                            estado_atual = t1.getDestino();
                                            System.out.print(blocoAtual.getNome() + ".");
                                            System.out.print(estado_atual + ":");
                                            fita.forEach((c) -> {
                                                System.out.print(c);
                                            });
                                            System.out.println("");

                                            if (t1.getDirecao_cabecote().equals("d")) {
                                                cabecote++;
                                            } else if (t1.getDirecao_cabecote().equals("e")) {
                                                cabecote--;
                                            }
                                        } else {
                                            if (t1.getOrigem().equals(estado_atual) && fita.get(cabecote).equals(t1.getLeitura().charAt(0))) {
                                                estado_atual = t1.getDestino();
                                                if (fita.get(cabecote).equals(t1.getLeitura().charAt(0))) {
                                                    if (!t1.getEscrita().equals("*")) {
                                                        fita.set(cabecote, t1.getEscrita().charAt(0));
                                                    }
                                                    estado_atual = t1.getDestino();
                                                    System.out.print(blocoAtual.getNome() + ".");
                                                    System.out.print(estado_atual + ":");
                                                    fita.forEach((c) -> {
                                                        System.out.print(c);
                                                    });
                                                    System.out.println("");
                                                }

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
        }
    }

    public void computacaoResume(String palavra, Map<String, bloco> lista_blocos, Integer contador, String delimitadores) {
        cabecote = 20;
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
        System.out.print("Inicio: ");
        fita.forEach((c) -> {
            System.out.print(c);
        });
        System.out.println("");

        bloco blocoAtual = lista_blocos.get("main");
        estado_atual = blocoAtual.getEstado_inicial();

        /*Enquanto não parar*/
        while (estado_atual != -2) {
            /*Saindo do bloco, ou seja, voltando para o main*/
            if (estado_atual == -1) {
                estado_atual = estado_retorno;
                blocoAtual = lista_blocos.get("main");
            }

            /*Procurando a transicao Simples correspondente*/
            for (TransicaoSimples t : blocoAtual.getTransicoesSimples()) {
                /*Se a origem da minha transicao for igual ao estado que estou 
                e o caractere que está na posicao sob o cabecote for igual a minha escrita
                então posso computar
                 */
                if (t.getOrigem().equals(estado_atual) && fita.get(cabecote).equals(t.getLeitura().charAt(0))
                        || (t.getOrigem().equals(estado_atual) && t.getLeitura().equals("*"))) {
                    if (fita.get(cabecote).equals(t.getLeitura().charAt(0))) {
                        fita.set(cabecote, t.getEscrita().charAt(0));
                        /*System.out.print(blocoAtual.getNome() + ".");
                        System.out.print(estado_atual + ":");

                        fita.forEach((c) -> {
                            System.out.print(c);
                        });
                        System.out.println("");*/
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

            /*Procurando a transicao que estrará em um bloco correspondente*/
            for (TransicaoBloco tb : blocoAtual.getTransicoesBlocos()) {
                /*se a origem da miha transicao for igual ao estado em que estou*/
                if (tb.getOrigem().equals(estado_atual)) {
                    /*armazenando o estado que deverá ser retornado quando sair do bloco*/
                    estado_retorno = tb.getDestino();
                    /*bloco em que eu irei entrar*/
                    blocoAtual = lista_blocos.get(tb.getBloco());
                    /*atualizando o estado atual para o estado inicial do bloco*/
                    estado_atual = blocoAtual.getEstado_inicial();

                    /*enquando eu nao chegar no retorne faça
                    entrando em um bloco
                     */
                    while (estado_atual != -1) {
                        for (TransicaoSimples t1 : blocoAtual.getTransicoesSimples()) {
                            if (t1.getLeitura().equals("*")) {
                                if (t1.getEscrita().equals("*")) {

                                } else {
                                    fita.set(cabecote, t1.getEscrita().charAt(0));
                                }
                                /*System.out.print(blocoAtual.getNome() + ".");
                                System.out.print(estado_atual + ":");
                                fita.forEach((c) -> {
                                    System.out.print(c);
                                });
                                System.out.println("");*/
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
                                        /*System.out.print(blocoAtual.getNome() + ".");
                                        System.out.print(estado_atual + ":");
                                        fita.forEach((c) -> {
                                            System.out.print(c);
                                        });
                                        System.out.println("");*/
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

        System.out.print("Fita: ");
        fita.forEach((c) -> {
            System.out.print(c);
        });
        System.out.println("");
    }
}
