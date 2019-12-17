package model.dto;

public class ClienteDTO {
    private int idCliente;
    private String nome;
    private String dtNasc;
    private String cpf;
    private TelefoneDTO telefone;
    private EnderecoDTO endereco;

    public ClienteDTO() {
        
    }

    public ClienteDTO(int idCliente) {
        this.setIdCliente(idCliente);
    }

    public ClienteDTO(String nome) {
        this.setNome(nome);
    }

    public ClienteDTO(int idCliente, String nome) {
        this(idCliente);
        setNome(nome);
    }

    public ClienteDTO(String nome, String dtNasc, String cpf) {
        setNome(nome);
        setDtNasc(dtNasc);
        setCpf(cpf);
    }

    public ClienteDTO(int idCliente, String nome, String dtNasc, String cpf) {
        setIdCliente(idCliente);
        setNome(nome);
        setDtNasc(dtNasc);
        setCpf(cpf);
    }       

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getDtNasc() {
        return dtNasc;
    }

    public void setDtNasc(String dtNasc) {
        this.dtNasc = dtNasc;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TelefoneDTO getTelefone() {
        return telefone;
    }

    public void setTelefone(TelefoneDTO telefone) {
        this.telefone = telefone;
    }

    public EnderecoDTO getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoDTO endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        
        return String.format("ID: %d \nNome: %s \n", getIdCliente(), getNome());
    }
    
}
