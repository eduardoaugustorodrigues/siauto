package model.dto;

public class TipoTelefoneDTO {
    private int idTipoTel;
    private String descricao;

    public TipoTelefoneDTO() {
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getIdTipoTel() {
        return idTipoTel;
    }

    public void setIdTipoTel(int idTipoTel) {
        this.idTipoTel = idTipoTel;
    }
    
}
