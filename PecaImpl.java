
package model.dao;

import model.dao.util.BancoDAO;
import model.dao.util.FactoryDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.dao.inter.FornecedorDAO;
import model.dto.PecaDTO;
import model.dao.inter.PecaDAO;
import model.dao.inter.TipoPecaDAO;

public class PecaImpl implements PecaDAO{
    private Connection con;
    private PreparedStatement pstm;
    private ResultSet res;
    private BancoDAO bancoDAO;
    private ArrayList<PecaDTO> lista;
    private String sql;
    private boolean toReturn;
    private FornecedorDAO fDAO;
    private TipoPecaDAO tpDAO;

    public PecaImpl(){
        lista =  new ArrayList<PecaDTO>();
        fDAO = new FornecedorImpl();
        tpDAO = new TipoPecaImpl();
    }

    public Connection getConnection() {
        this.bancoDAO = FactoryDAO.getInstanceBancoDAO();
        return this.bancoDAO.getConnection();
    }

    public boolean cadastrar(PecaDTO peca) throws SQLException{
        this.con = this.getConnection();
        
        this.sql = "INSERT INTO peca(nome_peca, desc_peca, valorEntrada_peca, valorVenda_peca, " +
                "qtd_peca, fk_TipoPeca, fk_fornecedor) VALUES(?,?,?,?,?,?,?)";

        try{
            this.pstm = this.con.prepareStatement(sql);
            this.pstm.setString(1, peca.getNome());
            this.pstm.setString(2, peca.getDescricao());
            this.pstm.setDouble(3, peca.getValorEntrada());
            this.pstm.setDouble(4, peca.getValorVenda());
            this.pstm.setInt(5, peca.getQtd());
            this.pstm.setInt(6, peca.getTipoPeca().getIdTipoPeca());
            this.pstm.setInt(7, peca.getFornecedor().getIdFornecedor());

            if(!this.pstm.execute()){
                toReturn = true;
            }
            
        }
        finally{
            this.bancoDAO.closeConnection(this.con, this.pstm, null);
        }
        return toReturn;
    }

    public boolean alterar(PecaDTO peca) throws SQLException {
        this.con = this.getConnection();
        
        this.sql = "UPDATE peca SET nome_peca=?, desc_peca=?, valorEntrada_peca=?, valorVenda_peca=?," +
                "qtd_peca=?, fk_TipoPeca=?, fk_fornecedor=? WHERE idPeca= ?";

        try {
            this.pstm = this.con.prepareStatement(sql);
            this.pstm.setString(1, peca.getNome());
            this.pstm.setString(2, peca.getDescricao());
            this.pstm.setDouble(3, peca.getValorEntrada());
            this.pstm.setDouble(4, peca.getValorVenda());
            this.pstm.setInt(5, peca.getQtd());            
            this.pstm.setInt(6, peca.getTipoPeca().getIdTipoPeca());
            this.pstm.setInt(7, peca.getFornecedor().getIdFornecedor());
            this.pstm.setInt(8, peca.getIdPeca());
            

            if(!this.pstm.execute()) {
                toReturn = true;
            }
        }
        finally{
            this.bancoDAO.closeConnection(this.con, this.pstm, null);
        }

        return toReturn;
    }

    public boolean excluir(PecaDTO peca) throws SQLException {
        this.con = this.getConnection();
        
        this.sql = "DELETE FROM peca WHERE idPeca = ?";

        try{
            this.pstm = this.con.prepareStatement(sql);
            this.pstm.setInt(1, peca.getIdPeca());

            if(!this.pstm.execute()){
                toReturn = true;
            }
        }
        finally{
            this.bancoDAO.closeConnection(this.con, this.pstm, null);
        }
        return toReturn;
    }

    public ArrayList<PecaDTO> getPecasNome(PecaDTO peca) throws SQLException {
        this.con = this.getConnection();
        
        this.sql = "SELECT * FROM peca WHERE nome_peca LIKE ?";

        try {
            this.pstm = this.con.prepareStatement(sql);
            this.pstm.setString(1, "%motor%");
            //this.pstm.setString(1, "%"+peca.getNome()+"%");
            this.res = this.pstm.executeQuery();
            
            lista = getListaPeca(res);
        }
        finally{
           this.bancoDAO.closeConnection(this.con, this.pstm, this.res);
        }
        return lista;
    }

    public PecaDTO getPecaId(int idPeca) throws SQLException {
        this.con = this.getConnection();

        this.sql = "SELECT * FROM peca WHERE idPeca = ?";
        PecaDTO pDTO = null;

        try {
            this.pstm = this.con.prepareStatement(sql);
            this.pstm.setInt(1, idPeca);            
            this.res = this.pstm.executeQuery();
            
            lista = getListaPeca(res);
            
            if(lista.size() >= 1) {
                pDTO = lista.get(0);
            }
        }
        finally{
            this.bancoDAO.closeConnection(this.con, this.pstm, null);
        }
        return pDTO;

    }

    public ArrayList<PecaDTO> getListaPecas()throws SQLException {
        this.con = this.getConnection();

        this.sql = "SELECT * FROM peca";

        try {
            this.pstm = this.con.prepareStatement(sql);
            this.res = this.pstm.executeQuery();

            lista = popularPecaCompra(res);
        }
        finally{
            this.bancoDAO.closeConnection(this.con, this.pstm, null);
        }
        return lista;
        
    }

    public ArrayList<PecaDTO> getListaPecasId(int id)throws SQLException {
        this.con = this.getConnection();

        this.sql = "SELECT * FROM peca WHERE idPeca= ?";

        try {
            this.pstm = this.con.prepareStatement(sql);
            this.pstm.setInt(1, id);
            this.res = this.pstm.executeQuery();

            lista = popularPecaCompra(res);
        }
        finally{
            this.bancoDAO.closeConnection(this.con, this.pstm, null);
        }
        return lista;
        
    }

    public ArrayList<PecaDTO> popularPecaCompra(ResultSet res) throws SQLException {
        ArrayList<PecaDTO> listPeca = new ArrayList<PecaDTO>();
        PecaDTO pDTO = null;
        
        while(res.next()) {
            pDTO = new PecaDTO();
            pDTO.setIdPeca(res.getInt("idPeca"));
            pDTO.setNome(res.getString("nome_peca"));
            pDTO.setValorVenda(res.getDouble("valorVenda_peca"));
            pDTO.setQtdEstoque(res.getInt("qtd_peca"));
            pDTO.setTipoPeca(tpDAO.getTipoPecaId(res.getInt("fk_TipoPeca")));
            
            listPeca.add(pDTO);
        }
        return listPeca;
    }

    public ArrayList<PecaDTO> getListaPeca(ResultSet res) throws SQLException {        
        ArrayList<PecaDTO> listPeca = new ArrayList<PecaDTO>();
        PecaDTO pDTO = null;
        
        while(res.next()){
            pDTO = new PecaDTO();
            pDTO.setIdPeca(res.getInt("idPeca"));
            pDTO.setNome(res.getString("nome_peca"));
            pDTO.setDescricao(res.getString("desc_peca"));
            pDTO.setValorEntrada(res.getDouble("valorEntrada_peca"));
            pDTO.setValorVenda(res.getDouble("valorVenda_peca"));
            pDTO.setQtdEstoque(res.getInt("qtd_peca"));
            pDTO.setTipoPeca(tpDAO.getTipoPecaId(res.getInt("fk_TipoPeca")));
            pDTO.setFornecedor(fDAO.getForncedorId(res.getInt("fk_fornecedor")));

            listPeca.add(pDTO);
        }
        
        return listPeca;
    }

  }
