package Modelo;

/**
 *
 * @author Vitor
 */
public class TransicaoSimples{
    
    private Integer origem;
    private Integer destino;
    private String leitura;
    private String direcao_cabecote;
    private String escrita;

    public TransicaoSimples() {
    }

    public TransicaoSimples(Integer origem, Integer destino, String leitura, String direcao_cabecote, String escrita) {
        this.origem = origem;
        this.destino = destino;
        this.leitura = leitura;
        this.direcao_cabecote = direcao_cabecote;
        this.escrita = escrita;
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

    public String getLeitura() {
        return leitura;
    }

    public void setLeitura(String leitura) {
        this.leitura = leitura;
    }

    public String getDirecao_cabecote() {
        return direcao_cabecote;
    }

    public void setDirecao_cabecote(String direcao_cabecote) {
        this.direcao_cabecote = direcao_cabecote;
    }

    public String getEscrita() {
        return escrita;
    }

    public void setEscrita(String escrita) {
        this.escrita = escrita;
    }
    
    

}
