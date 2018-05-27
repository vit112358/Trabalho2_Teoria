package trabalho_teoria;

import IO.Entrada_Dados;
import Modelo.bloco;
import java.io.File;
import java.util.HashMap;

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
        String del1 = "(";
        String del2 = ")";
        
        //instanciando lista de blocos
        HashMap<String, bloco> lista_blocos = new HashMap<>();
        
        //lendo arquivo
        Entrada_Dados IO = new Entrada_Dados();
        File arquivo = new File("C://Users//Vitor//Desktop//palindromo.MT");
        if (IO.Leitura_Dados(arquivo, lista_blocos)) {
            System.out.println("Legal");
        }else System.out.println("Erro");
    }
    
}
