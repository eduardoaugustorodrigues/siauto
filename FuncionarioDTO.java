package model.dto;

import java.text.DecimalFormat;

public class FuncionarioDTO {
    private int idFunc;
    private String nome;
    private String cpf;
    private String dtNasc;
    private double salario;
    private String dtAdmissao;
    private String dtDemissao;
    private String usuario;
    private String senha;

    public FuncionarioDTO() {
    }

    public FuncionarioDTO(int idFunc, String nome) {
        this.idFunc = idFunc;
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getDtAdmissao() {
        return dtAdmissao;
    }

    public void setDtAdmissao(String dtAdmissao) {
        this.dtAdmissao = dtAdmissao;
    }

    public String getDtDemissao() {
        return dtDemissao;
    }

    public void setDtDemissao(String dtDemissao) {
        this.dtDemissao = dtDemissao;
    }

    public String getDtNasc() {
        return dtNasc;
    }

    public void setDtNasc(String dtNasc) {
        this.dtNasc = dtNasc;
    }

    public int getIdFunc() {
        return idFunc;
    }

    public void setIdFunc(int idFunc) {
        this.idFunc = idFunc;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getStrSalario() {
        DecimalFormat df = new DecimalFormat("##,###.00");
        String str = df.format(this.getSalario());
        
        return str;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        String str = String.format("ID: %d \nNome: %s \n", getIdFunc(), getNome());

        return str;
    }

    public static void main(String args[]) {
        FuncionarioDTO f = new FuncionarioDTO();
        f.setSalario(1239.50);
        f.getStrSalario();

    }
    
}
