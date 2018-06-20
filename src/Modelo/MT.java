package Modelo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Vitor
 */
public class MT {

    private Map<String, bloco> lista_blocos;
    private Integer cabecote;
    private ArrayList<Character> fita = new ArrayList<>();
    private int contador;
    private String delimitadores;

    /**
     * Construtor da classe
     * @param delimtadores delimitadores da MT
     */
    public MT(String delimtadores) {
        this.delimitadores = delimtadores;
    }

    /**
     * Retorna os blocos que compoem uma MT
     *
     * @return lista de blocos
     */
    public Map<String, bloco> getLista_blocos() {
        return lista_blocos;
    }

    /**
     * Seta os blocos de uma determinada MT
     *
     * @param lista_blocos blocos de transicoes que irão compor uma MT
     */
    public void setLista_blocos(Map<String, bloco> lista_blocos) {
        this.lista_blocos = lista_blocos;
    }

    /**
     * Computa a palavra passada para a MT definida
     *
     * @param palavra palavra a ser computada
     * @param lista_blocos blocos da máquina
     * @param modo verbose, resume ou step
     */
    public void computacao(String palavra, Map<String, bloco> lista_blocos, char modo, int passos) {

        /*Posicao inicial do cabecote*/
        cabecote = 20;

        /*vetor com os delimitadores*/
        char[] del = new char[2];

        /*esta variavel guardara o modo passado de inicio, para que caso o prompt
        for chamado e receber vazio o modo seja o ultimo escolhido*/
        char modo_inicial = modo;

        /*conta os passos, ela que verificará se passou a quantidade de passos desejados no modo step*/
        int contador_passos = 0;

        /*variavel que irá armazenar o estado atual da computacao*/
        int estado_atual;

        /*Variavel que irá armazenar o estado que deverá continuar a computação ao
        sair de um bloco*/
        int estado_retorno = 0;

        /*Pegando os delimitadores*/
        del[0] = delimitadores.charAt(0);
        del[1] = delimitadores.charAt(1);

        //em toda a contagem irá apontar para o começo da palavra
        int aux = 0;

        //Quebrando a palavra em caracteres
        char[] auxiliar = palavra.toCharArray();
        //colocando cada caractere na fita
        for (int i = 0; i < 43; i++) {
            if (i < 20) {
                fita.add('_');
            } else if (i >= 20 && aux < auxiliar.length) {
                fita.add(auxiliar[aux]);
                aux++;
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

                        switch (modo) {
                            case 'v':
                                System.out.print(blocoAtual.getNome() + ".");
                                System.out.print(estado_atual + ":");
                                desenha_fita(fita, cabecote, del);
                                break;
                            case 's':
                                System.out.print(blocoAtual.getNome() + ".");
                                System.out.print(estado_atual + ":");
                                desenha_fita(fita, cabecote, del);
                                contador_passos++;
                                if (contador_passos > passos) {
                                    modo_inicial = modo;
                                    modo = prompt();
                                }
                                break;
                            case 'r':
                                break;
                            default:
                                break;
                        }
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
                                switch (modo) {
                                    case 'v':
                                        System.out.print(blocoAtual.getNome() + ".");
                                        System.out.print(estado_atual + ":");
                                        desenha_fita(fita, cabecote, del);
                                        break;
                                    case 's':
                                        System.out.print(blocoAtual.getNome() + ".");
                                        System.out.print(estado_atual + ":");
                                        desenha_fita(fita, cabecote, del);
                                        contador_passos++;
                                        if (contador_passos > passos) {
                                            modo_inicial = modo;
                                            modo = prompt();
                                        }
                                        break;
                                    case 'r':
                                        break;
                                    default:
                                        break;
                                }
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
                                        switch (modo) {
                                            case 'v':
                                                System.out.print(blocoAtual.getNome() + ".");
                                                System.out.print(estado_atual + ":");
                                                desenha_fita(fita, cabecote, del);
                                                break;
                                            case 's':
                                                System.out.print(blocoAtual.getNome() + ".");
                                                System.out.print(estado_atual + ":");
                                                desenha_fita(fita, cabecote, del);
                                                contador_passos++;
                                                if (contador_passos > passos) {
                                                    modo_inicial = modo;
                                                    modo = prompt();
                                                }
                                                break;
                                            case 'r':
                                                break;
                                            default:
                                                break;
                                        }
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
                                    /*Procurando a transicao referente ao estado atual*/
                                    for (TransicaoSimples t1 : blocoAtual.getTransicoesSimples()) {
                                        if (t1.getLeitura().equals("*")) {
                                            if (t1.getEscrita().equals("*")) {

                                            } else {
                                                fita.set(cabecote, t1.getEscrita().charAt(0));
                                            }
                                            estado_atual = t1.getDestino();
                                            switch (modo) {
                                                case 'v':
                                                    System.out.print(blocoAtual.getNome() + ".");
                                                    System.out.print(estado_atual + ":");
                                                    desenha_fita(fita, cabecote, del);
                                                    break;
                                                case 's':
                                                    System.out.print(blocoAtual.getNome() + ".");
                                                    System.out.print(estado_atual + ":");
                                                    desenha_fita(fita, cabecote, del);
                                                    contador_passos++;
                                                    if (contador_passos > passos) {
                                                        modo_inicial = modo;
                                                        modo = prompt();
                                                    }
                                                    break;
                                                case 'r':
                                                    break;
                                                default:
                                                    break;
                                            }
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
                                                    switch (modo) {
                                                        case 'v':
                                                            System.out.print(blocoAtual.getNome() + ".");
                                                            System.out.print(estado_atual + ":");
                                                            desenha_fita(fita, cabecote, del);
                                                            break;
                                                        case 's':
                                                            System.out.print(blocoAtual.getNome() + ".");
                                                            System.out.print(estado_atual + ":");
                                                            desenha_fita(fita, cabecote, del);
                                                            contador_passos++;
                                                            if (contador_passos > passos) {
                                                                modo_inicial = modo;
                                                                modo = prompt();
                                                            }
                                                            break;
                                                        case 'r':
                                                            break;
                                                        default:
                                                            break;
                                                    }
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
        System.out.print("Fim:" + blocoAtual.getNome() + ".");
        System.out.print(estado_atual + ":");
        desenha_fita(fita, cabecote, del);
    }

    public void desenha_fita(ArrayList<Character> fita, int cabecote, char[] del) {
        List<Character> resultado = new LinkedList<>();
        List<Character> aux1 = fita.subList(0, cabecote);
        List<Character> aux2 = fita.subList(cabecote + 1, fita.size() - 1);
        resultado.addAll(aux1);
        resultado.add(del[0]);
        resultado.add(fita.get(cabecote));
        resultado.add(del[1]);
        resultado.addAll(aux2);
        resultado.forEach((c) -> {
            System.out.print(c);
        });
        System.out.println("");
    }

    public char prompt() {
        System.out.print("Escolha as opcoes? Digite -r(resume), -v(verbose), -s <n>(Passos), -head \"'delimitador1''delimitador2'\" :");
        Scanner ler = new Scanner(System.in);
        String opcoes = ler.nextLine();
        char op = opcoes.charAt(1);
        if(opcoes.contains("-head")){
            delimitadores = opcoes.substring(opcoes.indexOf("\"")+1, opcoes.indexOf("\"")+3);
        }
        switch (op) {
            case 'r':
                System.out.println("Modo Resume:");
                return op;
            case 'v':
                System.out.println("Modo Verbose:");
                return op;
            case 's':
                System.out.println("Modo Step:");
                contador = Integer.parseInt(String.valueOf(ler.nextLine().charAt(4)));
                return op;
            default:
                System.out.println("Parametro desconhecido!");
                break;
        }

        return ler.nextLine().charAt(1);/*Erro*/
    }
}
