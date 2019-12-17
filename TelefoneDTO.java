package model.dto;

public class TelefoneDTO {
    private int idTel;
    private String numero;
    private TipoTelefoneDTO tipoTelefone;

    public TelefoneDTO() {
    }

    public int getIdTel() {
        return idTel;
    }

    public void setIdTel(int idTel) {
        this.idTel = idTel;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public TipoTelefoneDTO getTipoTelefone() {
        return tipoTelefone;
    }

    public void setTipoTelefone(TipoTelefoneDTO tipoTelefone) {
        this.tipoTelefone = tipoTelefone;
    }

}
