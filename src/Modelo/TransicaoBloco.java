package Modelo;

/**
 *
 * @author Vitor
 */
public class TransicaoBloco extends Transicao{
    
    private bloco bloco;

    public TransicaoBloco(bloco bloco, Integer origem, Integer destino) {
        super(origem, destino);
        this.bloco = bloco;
    }

    public bloco getBloco() {
        return bloco;
    }

    public void setBloco(bloco bloco) {
        this.bloco = bloco;
    }    
}
