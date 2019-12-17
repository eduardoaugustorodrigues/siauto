package model.dto;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import model.dto.inter.InterfaceCompra;

public class CompraDTO implements InterfaceCompra {
    private int idCompra;
    private double valorTotal;
    private List<ItemPecaDTO> listaItens;
    private FuncionarioDTO funcionario;
    private ClienteDTO cliente;
    private Date dataCompra;

    public CompraDTO() {
        listaItens = new ArrayList<ItemPecaDTO>();
    }

    public CompraDTO(int idCompra, ArrayList<ItemPecaDTO> listaItens, FuncionarioDTO funcionario,
            ClienteDTO cliente) {
        this.idCompra = idCompra;
        this.listaItens = listaItens;
        this.funcionario = funcionario;
        this.cliente = cliente;
    }

    public ClienteDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }

    public FuncionarioDTO getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(FuncionarioDTO funcionario) {
        this.funcionario = funcionario;
    }

    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    public double getValorTotal() {

        //setValorTotal(calcularValorTotal());
        
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Date getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(Date dataCompra) {
        this.dataCompra = dataCompra;
    }

    public double calcularValorTotal() {
        List<ItemPecaDTO> itens = getListaItensPeca();
        double somaItens = 0;

        for(ItemPecaDTO tp : itens) {
            somaItens += tp.getPeca().getValorVenda() * tp.getQtd();
        }
        
        this.setValorTotal(somaItens);
        
        return this.getValorTotal();
    }

    public void adicionarItemPeca(ItemPecaDTO ip) {
        listaItens.add(ip);
    }

    public void removerItemPeca(ItemPecaDTO ipDTO) {
        listaItens.remove(ipDTO);
    }

    public ItemPecaDTO getItemPecaId(int id) {
        return listaItens.get(id);
    }

    public List<ItemPecaDTO> getListaItensPeca() {
        StringBuilder sb = new StringBuilder();
        
        for(ItemPecaDTO ip : listaItens) {
            sb.append(ip.toString());
        }
        
        exibirItensPeca(sb.toString());

        return listaItens;
    }

    public List<ItemPecaDTO> getListaItens() {
        return listaItens;
    }

    public void setListaItens(List<ItemPecaDTO> listaItens) {
        this.listaItens = listaItens;
    }

    public String exibirItensPeca(String itemPeca) {
        
        return itemPeca;
    }

    public void excluirItemPeca(int id) {
        this.listaItens.remove(id);
    }
/*
    @Override
    public String toString() {
        String str = String.format("Compra: %d \nFuncionario: %s \nCliente: %s \nLista de Itens: %s \nValor Total: %.2f \n",
                getIdCompra(), getFuncionario(), getCliente(), getListaItensPeca(), calcularValorTotal());

        System.out.println(str);
        
        return str;
    }
  */
}
