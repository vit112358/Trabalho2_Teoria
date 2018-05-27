package Modelo;

/**
 *
 * @author Vitor
 */
public abstract class Transicao {
    
    private Integer origem;
    private Integer destino;

    public Transicao(Integer origem, Integer destino) {
        this.origem = origem;
        this.destino = destino;
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
    
}
