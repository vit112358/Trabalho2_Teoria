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

    public MT(Map<String, bloco> lista_blocos) {
        this.lista_blocos = lista_blocos;
    }

    public Map<String, bloco> getLista_blocos() {
        return lista_blocos;
    }

    public void setLista_blocos(Map<String, bloco> lista_blocos) {
        this.lista_blocos = lista_blocos;
    }
    
    public void computacao(String palavra, Map<String, bloco> lista_blocos, Integer contador){
        cabecote = 0;
        
        //Quebrando a palavra em caracteres
        char[] auxiliar = palavra.toCharArray();
        
        //colocando cada caracter na fita
        for (int i = 0; i < auxiliar.length; i++) {
            fita.add(auxiliar[i]);
        }
    }
    
}
