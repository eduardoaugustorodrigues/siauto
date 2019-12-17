package model.dto;

import java.text.DecimalFormat;

public class PecaDTO {
    private int idPeca;
    private String nome;
    private String descricao;
    private double valorEntrada;
    private double valorVenda;
    private int qtd;
    private TipoPecaDTO tipoPeca;
    private FornecedorDTO fornecedor;

    public PecaDTO() {
     
    }

    public PecaDTO(int idPeca, String nome, double valorVenda, int qtd, TipoPecaDTO tipoPeca) {
        this.idPeca = idPeca;
        this.nome = nome;
        this.valorVenda = valorVenda;
        this.qtd = qtd;
        setTipoPeca(tipoPeca);
    }   

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getIdPeca() {
        return idPeca;
    }

    public void setIdPeca(int idPeca) {
        this.idPeca = idPeca;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtdEstoque(int qtd) {
        this.qtd = qtd;
    }

    public double getValorEntrada() {        
        return valorEntrada;
    }

    public String getStringValorEntrada() {
        DecimalFormat df = new DecimalFormat("##,###.00");
        String str = df.format(this.getValorEntrada());

        return str;
    }

    public String getStringValorVenda() {
        DecimalFormat df = new DecimalFormat("##,###.00");
        String str = df.format(this.getValorVenda());

        return str;
    }

    public void setValorEntrada(double valorEntrada) {
        this.valorEntrada = valorEntrada;
    }

    public double getValorVenda() {
        return valorVenda;
    }

    public String getStrValorVenda() {
        DecimalFormat df = new DecimalFormat("##,###.00");
        String str = df.format(this.getValorVenda());

        return str;
    }

    public void setValorVenda(double valorVenda) {
        this.valorVenda = valorVenda;
    }

    public TipoPecaDTO getTipoPeca() {
        return tipoPeca;
    }

    public void setTipoPeca(TipoPecaDTO tipoPeca) {
        this.tipoPeca = tipoPeca;
    }

    public FornecedorDTO getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(FornecedorDTO fornecedor) {
        this.fornecedor = fornecedor;
    }
/*
    @Override
    public String toString() {
        return String.format("\nTipo de Peca: %s \nNome: %s \nQtd: %d \nValor: %.2f \n",
                getTipoPeca().getDescricao(), getNome(), getQtd(), getValorVenda());
    }
  */
}