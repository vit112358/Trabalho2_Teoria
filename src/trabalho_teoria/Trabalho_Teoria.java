package trabalho_teoria;

import IO.Entrada_Dados;
import Modelo.MT;
import Modelo.bloco;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


/**
 *
 * @author Vitor
 */
public class Trabalho_Teoria {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //Delimitadores
        String delimitadores = "()";
        
        //instanciando lista de blocos
        Map<String, bloco> lista_blocos = new HashMap<>();
        
        //palavra a ser testada
        String palavra;
               
        //========Instanciaçoes e variáveis=====================================
        
        System.out.println("Simulador de Máquina de Turing ver 1.0");
        System.out.println("Desenvolvido como trabalho prático da Disciplina de "
                + "Teoria da Computação");
        System.out.println("Vitor Fernandes, Renato Lucas & Luís Nogueira, IFMG, 2018");
        
        //lendo arquivo
        Entrada_Dados IO = new Entrada_Dados();
        File arquivo = new File("C://Users//Vitor//Desktop//palindromo.MT");
        if (IO.Leitura_Dados(arquivo, lista_blocos)) {
            //System.out.println("Legal");
        }
        //else System.out.println("Erro");
        
        MT mt = new MT(delimitadores);
        
        /*modo do programa*/
        char modo = mt.prompt();
        
        /*Pegando a palavra*/
        System.out.print("Forneça a palavra inicial: ");
        Scanner ler = new Scanner(System.in);
        palavra = ler.nextLine();
        
        mt.computacao(palavra, lista_blocos, modo, 500);
        //mt.computacaoResume(palavra, lista_blocos, contador, delimitadores);
        System.out.println("olá");
    }
    
}
