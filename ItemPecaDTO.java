package model.dto;

public class ItemPecaDTO {
    private int idItemPeca;
    private PecaDTO peca;    
    private int qtd;

    public ItemPecaDTO() {
    }

    public ItemPecaDTO(PecaDTO peca, int qtd) {
        this.peca = peca;
        this.qtd = qtd;
    }

    public PecaDTO getPeca() {
        return peca;
    }

    public void setPeca(PecaDTO peca) {
        this.peca = peca;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public int getIdItemPeca() {
        return idItemPeca;
    }

    public void setIdItemPeca(int idItemPeca) {
        this.idItemPeca = idItemPeca;
    }
/*
    @Override
    public String toString() {
        return String.format("%s \nQtd Itens: %d \n",
                getPeca(), getQtd());
    }
  */
}
