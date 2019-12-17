
package model.dao;

import model.dao.util.BancoDAO;
import model.dao.util.FactoryDAO;
import java.sql.*;
import java.util.ArrayList;
import model.dao.inter.TelefoneDAO;
import model.dao.inter.TipoTelefoneDAO;
import model.dto.ClienteDTO;
import model.dto.EnderecoDTO;
import model.dto.FornecedorDTO;
import model.dto.TelefoneDTO;

public class TelefoneImpl implements TelefoneDAO {
    private Connection con;
    private PreparedStatement pstm;
    private ResultSet res;
    private BancoDAO bancoDAO; //FactoryDAO.getInstanceBancoDAO();   
    private String sql;
    private boolean toReturn;
    private TipoTelefoneDAO tipoTelDAO;

    public TelefoneImpl() {
        tipoTelDAO = new TipoTelefoneImpl();
    }

    public Connection getConnection() {
        this.bancoDAO = FactoryDAO.getInstanceBancoDAO();
        return this.bancoDAO.getConnection();
    }
    
    public boolean cadastrar(ClienteDTO c) throws SQLException {
        //this.con = this.bancoDAO.getConnection();this
        this.con = this.getConnection();

        this.sql = "INSERT INTO telefone(numero, fk_cliente, fk_tipoTel) VALUES(?,?,?)";
       
        try {
        
            this.pstm = this.con.prepareStatement(sql);
            this.pstm.setString(1, c.getTelefone().getNumero());
            this.pstm.setInt(2, c.getIdCliente());
            this.pstm.setInt(3, c.getTelefone().getTipoTelefone().getIdTipoTel());

            if(!this.pstm.execute()) {
                toReturn = true;
            }
            
        }
        finally {
            this.bancoDAO.closeConnection(this.con, this.pstm, null);
        }
        
        return toReturn;

    }

    public boolean cadastrar(FornecedorDTO f) throws SQLException {
        //this.con = this.bancoDAO.getConnection();this
        this.con = this.getConnection();

        this.sql = "INSERT INTO telefone(numero, fk_fornecedor, fk_tipoTel) VALUES(?,?,?)";

        try {

            this.pstm = this.con.prepareStatement(sql);
            this.pstm.setString(1, f.getTelefone().getNumero());
            this.pstm.setInt(2, f.getIdFornecedor());
            this.pstm.setInt(3, f.getTelefone().getTipoTelefone().getIdTipoTel());

            if(!this.pstm.execute()) {
                toReturn = true;
            }

        }
        finally {
            this.bancoDAO.closeConnection(this.con, this.pstm, null);
        }

        return toReturn;

    }

    public boolean alterar(ClienteDTO c) throws SQLException {
        //this.con = this.bancoDAO.getConnection();
        this.con = this.getConnection();
        
        this.sql = "UPDATE telefone SET numero= ?, fk_tipoTel= ? WHERE fk_cliente= ?";

        try {
            this.pstm = this.con.prepareStatement(sql);
            this.pstm.setString(1, c.getTelefone().getNumero());
            this.pstm.setInt(2, c.getTelefone().getTipoTelefone().getIdTipoTel());
            this.pstm.setInt(3, c.getIdCliente());

            if(!this.pstm.execute()) {
                toReturn = true;
            }

        }
        finally{
          this.bancoDAO.closeConnection(this.con, this.pstm, null);
        }

        return toReturn;

    }

    public boolean alterar(FornecedorDTO f) throws SQLException {
        //this.con = this.bancoDAO.getConnection();
        this.con = this.getConnection();
        
        this.sql = "UPDATE telefone SET numero=?, fk_tipoTel=? WHERE fk_fornecedor= ?";

        try {
            this.pstm = this.con.prepareStatement(sql);
            this.pstm.setString(1, f.getTelefone().getNumero());
            this.pstm.setInt(2, f.getTelefone().getTipoTelefone().getIdTipoTel());
            this.pstm.setInt(3, f.getIdFornecedor());

            if(!this.pstm.execute()) {
                toReturn = true;
            }

        }
        finally{
          this.bancoDAO.closeConnection(this.con, this.pstm, null);
        }

        return toReturn;

    }

    public boolean excluir(ClienteDTO c) throws SQLException {
        this.con = this.getConnection();
        //this.con = this.bancoDAO.getConnection();

        this.sql = "DELETE FROM telefone WHERE fk_cliente= ?";

        try{            
            this.pstm = this.con.prepareStatement(this.sql);
            this.pstm.setInt(1, c.getIdCliente());

            if(!this.pstm.execute()) {
                toReturn = true;
            }
            
        }
        finally{
           this.bancoDAO.closeConnection(this.con, this.pstm, null);
        }

        return toReturn;

    }
    public boolean excluir(FornecedorDTO f) throws SQLException {
        this.con = this.getConnection();
        //this.con = this.bancoDAO.getConnection();

        this.sql = "DELETE FROM telefone WHERE fk_fornecedor= ?";

        try{
            this.pstm = this.con.prepareStatement(this.sql);
            this.pstm.setInt(1, f.getIdFornecedor());

            if(!this.pstm.execute()) {
                toReturn = true;
            }

        }
        finally{
           this.bancoDAO.closeConnection(this.con, this.pstm, null);
        }

        return toReturn;

    } 

    public TelefoneDTO getTelefoneIdCliente(int id) throws SQLException {
        //this.con = this.bancoDAO.getConnection();
        this.con = this.getConnection();
        
        TelefoneDTO tel = null;

        this.sql = "SELECT * FROM telefone WHERE fk_cliente = ?";

        try {
            this.pstm = this.con.prepareStatement(sql);
            this.pstm.setInt(1, id);
            this.res = this.pstm.executeQuery();           

            if(res.next()) {
                tel = new TelefoneDTO();
                tel.setIdTel(res.getInt("idTel"));
                tel.setNumero(res.getString("numero"));
                tel.setTipoTelefone(tipoTelDAO.getTipoTelefoneId(res.getInt("fk_TipoTel")));
            }
            
        }
        finally{
            this.bancoDAO.closeConnection(this.con, this.pstm, this.res);
        }
        
        return tel;

    }

    public TelefoneDTO getTelefoneIdFornecedor(int id) throws SQLException {
        //this.con = this.bancoDAO.getConnection();
        this.con = this.getConnection();

        TelefoneDTO tel = null;

        this.sql = "SELECT * FROM telefone WHERE fk_fornecedor = ?";

        try {
            this.pstm = this.con.prepareStatement(sql);
            this.pstm.setInt(1, id);
            this.res = this.pstm.executeQuery();

            if(res.next()) {
                tel = new TelefoneDTO();
                tel.setIdTel(res.getInt("idTel"));
                tel.setNumero(res.getString("numero"));
                tel.setTipoTelefone(tipoTelDAO.getTipoTelefoneId(res.getInt("fk_TipoTel")));
            }

        }
        finally{
            this.bancoDAO.closeConnection(this.con, this.pstm, this.res);
        }

        return tel;

    }
        
}
