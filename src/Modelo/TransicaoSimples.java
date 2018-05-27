package Modelo;

/**
 *
 * @author Vitor
 */
public class TransicaoSimples extends Transicao{
    
    private String leitura;
    private String direcao_cabecote;
    private String escrita;
    
    public TransicaoSimples(String leitura, String direcao_cabecote, String escrita, Integer origem, Integer destino) {
        super(origem, destino);
        this.leitura = leitura;
        this.direcao_cabecote = direcao_cabecote;
        this.escrita = escrita;
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
