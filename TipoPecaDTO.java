package model.dto;

public class TipoPecaDTO {
    private int idTipoPeca;
    private String descricao;

    public TipoPecaDTO() {
        
    }

    public TipoPecaDTO(int idTipoPeca) {
        this.idTipoPeca = idTipoPeca;
    }

    public TipoPecaDTO(String descricao) {
        this.descricao = descricao;
    }

    public TipoPecaDTO(int idTipoPeca, String descricao) {
        this.idTipoPeca = idTipoPeca;
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getIdTipoPeca() {
        return idTipoPeca;
    }

    public void setIdTipoPeca(int idTipoPeca) {
        this.idTipoPeca = idTipoPeca;
    }

    @Override
    public String toString() {
        
        return String.format("\nTipo de Peça: %s", getDescricao());
    }
    
}
