package IO;

import Modelo.bloco;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Vitor
 */
public class Entrada_Dados {

    //=================Leitura=================================================//
    public boolean Leitura_Dados(File Caminho, HashMap<String, bloco> blocos) {
        ArrayList<String> tokens;
        StringBuilder builder;

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
                                    try {
                                        Integer origem = Integer.parseInt(tokens.get(0));
                                    } catch (NumberFormatException e) {
                                        JOptionPane.showMessageDialog(null, e.getMessage());
                                        return false;
                                    }
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
