
package model.dao;

import java.sql.*;
import java.util.ArrayList;
import model.dao.inter.EnderecoDAO;
import model.dao.util.BancoDAO;
import model.dao.util.FactoryDAO;
import model.dto.ClienteDTO;
import model.dto.EnderecoDTO;
import model.dto.FornecedorDTO;

public class EnderecoImpl implements EnderecoDAO {
 private Connection con;
    private PreparedStatement pstm;
    private ResultSet res;
    private BancoDAO bancoDAO; //FactoryDAO.getInstanceBancoDAO();
    private String sql;
    private boolean toReturn;    

    public EnderecoImpl() {
        
    }

    public Connection getConnection() {
        this.bancoDAO = FactoryDAO.getInstanceBancoDAO();
        return this.bancoDAO.getConnection();
    }

    public boolean cadastrar(ClienteDTO c) throws SQLException {
        //this.con = this.bancoDAO.getConnection();this
        this.con = this.getConnection();

        this.sql = "INSERT INTO endereco(endereco, bairro, cep, logradouro, cidade, uf, fk_cliente) " +
                "VALUES(?,?,?,?,?,?,?)";

        try {

            this.pstm = this.con.prepareStatement(sql);
            this.pstm.setString(1, c.getEndereco().getEndereco());
            this.pstm.setString(2, c.getEndereco().getBairro());
            this.pstm.setString(3, c.getEndereco().getCep());
            this.pstm.setString(4, c.getEndereco().getLogradouro());
            this.pstm.setString(5, c.getEndereco().getCidade());
            this.pstm.setString(6, c.getEndereco().getUf());
            this.pstm.setInt(7, c.getIdCliente());

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

        this.sql = "INSERT INTO endereco(endereco, bairro, cep, logradouro, cidade, uf, fk_fornecedor) " +
                "VALUES(?,?,?,?,?,?,?)";

        try {

            this.pstm = this.con.prepareStatement(sql);
            this.pstm.setString(1, f.getEndereco().getEndereco());
            this.pstm.setString(2, f.getEndereco().getBairro());
            this.pstm.setString(3, f.getEndereco().getCep());
            this.pstm.setString(4, f.getEndereco().getLogradouro());
            this.pstm.setString(5, f.getEndereco().getCidade());
            this.pstm.setString(6, f.getEndereco().getUf());
            this.pstm.setInt(7, f.getIdFornecedor());

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

        this.sql = "UPDATE endereco SET endereco=?, bairro=?, cep=?, logradouro=?," +
                "cidade=?, uf=? WHERE fk_cliente=?";

        try {
            this.pstm = this.con.prepareStatement(sql);
            this.pstm.setString(1, c.getEndereco().getEndereco());
            this.pstm.setString(2, c.getEndereco().getBairro());
            this.pstm.setString(3, c.getEndereco().getCep());
            this.pstm.setString(4, c.getEndereco().getLogradouro());
            this.pstm.setString(5, c.getEndereco().getCidade());
            this.pstm.setString(6, c.getEndereco().getUf());
            this.pstm.setInt(7, c.getIdCliente());

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

        this.sql = "UPDATE endereco SET endereco=?, bairro=?, cep=?, logradouro=?," +
                "cidade=?, uf=? WHERE fk_fornecedor=?";

        try {
            this.pstm = this.con.prepareStatement(sql);
            this.pstm.setString(1, f.getEndereco().getEndereco());
            this.pstm.setString(2, f.getEndereco().getBairro());
            this.pstm.setString(3, f.getEndereco().getCep());
            this.pstm.setString(4, f.getEndereco().getLogradouro());
            this.pstm.setString(5, f.getEndereco().getCidade());
            this.pstm.setString(6, f.getEndereco().getUf());
            this.pstm.setInt(7, f.getIdFornecedor());

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

        this.sql = "DELETE FROM endereco WHERE fk_cliente= ?";

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

        this.sql = "DELETE FROM endereco WHERE fk_fornecedor= ?";

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

    public EnderecoDTO getEnderecoIdCliente(int id) throws SQLException {
        //this.con = this.bancoDAO.getConnection();
        this.con = this.getConnection();

        EnderecoDTO end = null;

        this.sql = "SELECT * FROM endereco WHERE fk_cliente = ?";

        try {
            this.pstm = this.con.prepareStatement(sql);
            this.pstm.setInt(1, id);
            this.res = this.pstm.executeQuery();

            end = popularEndereco(res);

        }
        finally{
            this.bancoDAO.closeConnection(this.con, this.pstm, this.res);
        }

        return end;

    }

    public EnderecoDTO getEnderecoIdFornecedor(int id) throws SQLException {
        //this.con = this.bancoDAO.getConnection();
        this.con = this.getConnection();

        EnderecoDTO end = null;

        this.sql = "SELECT * FROM endereco WHERE fk_fornecedor= ?";

        try {
            this.pstm = this.con.prepareStatement(sql);
            this.pstm.setInt(1, id);
            this.res = this.pstm.executeQuery();

            end = popularEndereco(res);
        }
        finally{
            this.bancoDAO.closeConnection(this.con, this.pstm, this.res);
        }

        return end;

    }

    public EnderecoDTO popularEndereco(ResultSet res) throws SQLException {
        EnderecoDTO end = null;
        
        if(res.next()) {
            end = new EnderecoDTO();
            end.setIdEnd(res.getInt("idEnd"));
            end.setCidade(res.getString("cidade"));
            end.setBairro(res.getString("bairro"));
            end.setUf(res.getString("uf"));
            end.setCep(res.getString("cep"));
            end.setLogradouro(res.getString("logradouro"));
            end.setEndereco(res.getString("endereco"));
        }
        return end;
    }
       
}
