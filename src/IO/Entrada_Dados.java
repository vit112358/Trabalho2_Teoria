package IO;

import Modelo.TransicaoBloco;
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

    /**
     * Fará a leitura de um arquivo que define a MT, cujo o formato deverá ser
     * .MT
     * @param Caminho caminho do arquivos
     * @param blocos Map onde será armazenado os blocos
     * @return True-se a leitura for feita com sucesso, caso contrário retorna falso
     */
    public boolean Leitura_Dados(File Caminho, Map<String, bloco> blocos) {
        ArrayList<String> tokens;

        try {

            //instanciando o leitor
            BufferedReader buffer = new BufferedReader(new FileReader(Caminho));

            Integer origem;
            Integer destino;
            //linha corrente
            String linha;

            TransicaoSimples tran;
            TransicaoBloco trans;

            ArrayList<TransicaoSimples> transSimples;
            ArrayList<TransicaoBloco> transBlocos;

            try {
                //Enquanto eu conseguir ler o arquivo faça
                while (buffer.ready()) {
                    //lendo a linha
                    linha = buffer.readLine();

                    //verificando comentários e pegando a parte válida da linha
                    if (linha.contains(";")) {
                        linha = linha.substring(0, linha.indexOf(";"));
                        linha = linha.trim();
                    }

                    //começando a leitura do bloco
                    if (linha.contains("bloco")) {

                        /*
                        Enquanto a linha nao ler o fim do bloco e estiver tudo certo 
                        com o buffer eu continuo a ler e construir o bloco
                         */

 /*
                        Criando o bloco
                         */
                        linha = linha.trim();
                        bloco novo_bloco = new bloco();
                        transSimples = new ArrayList<>();
                        transBlocos = new ArrayList<>();

                        //separando as informaçoes de cada linha
                        tokens = new ArrayList<>();
                        for (String palavra : linha.split(" ")) {
                            if (!(palavra.equals("−−"))) {
                                tokens.add(palavra);
                            }
                        }

                        //Setando o nome do bloco
                        if (tokens.get(1).length() <= 16) {
                            novo_bloco.setNome(tokens.get(1));
                        } else {
                            JOptionPane.showMessageDialog(null, "Tamanho maior que o máximo permitido!");
                            return false;
                        }

                        //setando e verificando o estado inicial
                        try {
                            novo_bloco.setEstado_inicial(Integer.parseInt(tokens.get(2)));
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, e.getMessage());
                            return false;
                        }
                        linha = buffer.readLine();
                        while (!(linha.contains("fim")) && buffer.ready()) {

                            if (!(linha.trim().equals(""))) {
                                //limpando as tabulações
                                linha = linha.trim();

                                /*
                                Pegando cada parte da linha que estiver seprado 
                                por um espaço
                                 */
                                tokens = new ArrayList<>();
                                for (String palavra : linha.split(" ")) {
                                    if (!(palavra.equals("−−"))) {
                                        tokens.add(palavra);
                                    }
                                }
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
                                        try {
                                            origem = Integer.parseInt(tokens.get(0));
                                            if (origem > 9999 || origem < -2) {
                                                JOptionPane.showMessageDialog(null, "ID do estado fora do permitido!");
                                                return false;
                                            }
                                            switch (tokens.get(4)) {
                                                //transformando os estados de retorno e parada para inteiros
                                                case "retorne":
                                                    destino = -1;
                                                    break;
                                                case "pare":
                                                    destino = -2;
                                                    break;
                                                default:
                                                    //verificando se o destino atende as especificaçoes
                                                    destino = Integer.parseInt(tokens.get(4));
                                                    if (destino > 9999 || destino < -2) {
                                                        JOptionPane.showMessageDialog(null, "ID do estado fora do permitido!");
                                                        return false;
                                                    }
                                                    break;
                                            }
                                        } catch (NumberFormatException e) {
                                            JOptionPane.showMessageDialog(null, e.getMessage());
                                            return false;
                                        }
                                        
                                        //armazenando a leitura e a escrita
                                        String leitura = tokens.get(1);
                                        String escrita = tokens.get(2);

                                        String direcao = tokens.get(3);

                                        //verificando a direção
                                        if ((!(direcao.equals("i")))
                                                && (!(direcao.equals("e")))
                                                && (!(direcao.equals("d")))) {
                                            JOptionPane.showMessageDialog(null, "Existe uma direção inválida");
                                            return false;
                                        }

                                        //montando uma transicao Simples, ou seja sem ser de bloco
                                        tran = new TransicaoSimples(origem, destino, leitura, direcao, escrita);
                                        transSimples.add(tran);
                                        break;
                                    /*
                                    caso tenha uma transicao do tipo que seja de bloco    
                                     */
                                    case 3:
                                        try {
                                            origem = Integer.parseInt(tokens.get(0));
                                            //verificando se a origem atende as especificacoes
                                            if (origem > 9999 || origem < -2) {
                                                JOptionPane.showMessageDialog(null, "ID do estado fora do permitido!");
                                                return false;
                                            }
                                            switch (tokens.get(2)) {
                                                //definindo inteiros para os estados de saida e de parada
                                                case "retorne":
                                                    destino = -1;
                                                    break;
                                                case "pare":
                                                    destino = -2;
                                                    break;
                                                default:
                                                    //verificando se o destino atende as especificacoes
                                                    destino = Integer.parseInt(tokens.get(2));
                                                    if (destino > 9999 || destino < -2) {
                                                        JOptionPane.showMessageDialog(null, "ID do estado fora do permitido!");
                                                        return false;
                                                    }
                                                    break;
                                            }
                                        } catch (NumberFormatException e) {
                                            JOptionPane.showMessageDialog(null, e.getMessage());
                                            return false;
                                        }
                                            
                                        //adicionando a transicao de blocos
                                        //no conjunto de transicoes de blocos
                                        trans = new TransicaoBloco(origem, destino, tokens.get(1));
                                        transBlocos.add(trans);
                                        break;
                                }
                            }

                            //Lendo a linha
                            linha = buffer.readLine();
                        }
                        //Inserindo as transicoes no bloco
                        novo_bloco.setTransicoesSimples(transSimples);
                        novo_bloco.setTransicoesBlocos(transBlocos);
                        //inserindo o novo bloco no conjunto de blocos
                        blocos.put(novo_bloco.getNome(), novo_bloco);
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
