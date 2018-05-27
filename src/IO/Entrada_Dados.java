package IO;

import Modelo.Transicao;
import Modelo.TransicaoSimples;
import Modelo.bloco;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Vitor
 */
public class Entrada_Dados {

    //=================Leitura=================================================//
    public boolean Leitura_Dados(File Caminho, Map<String, bloco> blocos) {
        ArrayList<String> tokens;
        StringBuilder builder;
        ArrayList<Transicao> transicoes;

        try {

            //instanciando o leitor
            BufferedReader buffer = new BufferedReader(new FileReader(Caminho));

            //linha corrente
            String linha;

            try {
                //Enquanto eu conseguir ler o arquivo faça
                while (buffer.ready()) {
                    //lendo a linha
                    linha = buffer.readLine();

                    //verificando comentários e pegando a parte válida da linha
                    if (linha.contains(";")) {
                        System.out.println("Comentário: " + linha);
                        linha = linha.substring(0, linha.indexOf(";"));
                        System.out.print("Parte válida: ");
                        linha = linha.trim();
                        System.out.println(linha);
                        System.out.println("");
                    }

                    //começando a leitura do bloco
                    if (linha.contains("bloco")) {
                        //DEBUG
                        System.out.println(linha);
                        System.out.println("");
                        System.out.println("");
                        System.out.println("Inicio da Construção do bloco");
                        //começo a construir o bloco
                        /*
                        Enquanto a linha nao ler o fim do bloco e estiver tudo certo 
                        com o buffer eu continuo a ler e construir o bloco
                         */

                        /*
                        Criando o bloco
                         */
                        linha = linha.trim();
                        bloco novo_bloco = new bloco();

                        tokens = new ArrayList<>();
                        builder = new StringBuilder();
                        for (String palavra : linha.split(" ")) {
                            if (!(palavra.equals("−−"))) {
                                tokens.add(palavra);
                            }
                        }

                        //Setando o nome do bloco
                        novo_bloco.setNome(tokens.get(1));

                        //setando e verificando o estado inicial
                        try {
                            novo_bloco.setEstado_inicial(Integer.parseInt(tokens.get(2)));
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, e.getMessage());
                            return false;
                        }
                        transicoes = new ArrayList<Transicao>();
                        linha = buffer.readLine();
                        while (!(linha.contains("fim")) && buffer.ready()) {

                            //limpando as tabulações
                            linha = linha.trim();

                            /*
                            Pegando cada parte da linha que estiver seprado 
                            por um espaço
                             */
                            tokens = new ArrayList<>();
                            builder = new StringBuilder();
                            for (String palavra : linha.split(" ")) {
                                if (!(palavra.equals("−−"))) {
                                    tokens.add(palavra);
                                }
                            }
                            System.out.println(tokens.size());

                            /*
                            Verificando qual tipo de transição preciso adotar
                             */
                            switch (tokens.size()) {
                                /*
                                caso tenha uma transicao do tipo que nao seja 
                                de bloco
                                 */
                                case 5:
                                    //verificando se a origem está correta
                                    Integer origem;
                                    Integer destino;
                                    try {
                                        origem = Integer.parseInt(tokens.get(0));

                                        if (tokens.get(4).equals("retorne")) {
                                            destino = -1;
                                        } else if (tokens.get(4).equals("pare")) {
                                            destino = -2;
                                        } else {
                                            destino = Integer.parseInt(tokens.get(4));
                                        }
                                    } catch (NumberFormatException e) {
                                        JOptionPane.showMessageDialog(null, e.getMessage());
                                        return false;
                                    }

                                    String leitura = tokens.get(1);
                                    String escrita = tokens.get(2);

                                    String direcao = tokens.get(3);

                                    //verificando a direção
                                    if ((!(direcao.equals("i")))
                                            || (!(direcao.equals("e")))
                                            || (!(direcao.equals("d")))) {
                                        JOptionPane.showMessageDialog(null, "Existe uma direção inválida");
                                        return false;
                                    }

                                    TransicaoSimples tran = new TransicaoSimples(leitura, direcao, escrita, origem, destino);

                                    transicoes.add(tran);
                                    break;
                                /*
                                caso tenha uma transicao do tipo que seja de bloco    
                                 */
                                case 3:
                                    break;
                            }

                            //Lendo a linha
                            linha = buffer.readLine();
                            System.out.println(linha);
                        }

                        blocos.put(novo_bloco.getNome(), novo_bloco);
                        System.out.println("Fim da Construção do bloco");
                    }

                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Erro de leitura do Arquivo");
                Logger.getLogger(Entrada_Dados.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao ler o caminho do Arquivo");
            Logger.getLogger(Entrada_Dados.class.getName()).log(Level.SEVERE, null, ex);
        }

        return true;
    }

}
