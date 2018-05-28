package Modelo;

/**
 *
 * @author Vitor
 */
public class TransicaoBloco{
    
    private Integer origem;
    private Integer destino;
    private String bloco;

    public TransicaoBloco() {
    }

    public TransicaoBloco(Integer origem, Integer destino, String bloco) {
        this.origem = origem;
        this.destino = destino;
        this.bloco = bloco;
    }

    public Integer getOrigem() {
        return origem;
    }

    public void setOrigem(Integer origem) {
        this.origem = origem;
    }

    public Integer getDestino() {
        return destino;
    }

    public void setDestino(Integer destino) {
        this.destino = destino;
    }

    public String getBloco() {
        return bloco;
    }

    public void setBloco(String bloco) {
        this.bloco = bloco;
    }

    
}
