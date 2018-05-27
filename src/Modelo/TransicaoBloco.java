package Modelo;

/**
 *
 * @author Vitor
 */
public class TransicaoBloco extends Transicao{
    
    private String bloco;

    public TransicaoBloco(String bloco, Integer origem, Integer destino) {
        super(origem, destino);
        this.bloco = bloco;
    }

    public String getBloco() {
        return bloco;
    }

    public void setBloco(String bloco) {
        this.bloco = bloco;
    }    
}
