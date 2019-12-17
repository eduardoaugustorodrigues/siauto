
package model.dao;

import java.sql.*;
import java.util.ArrayList;
import model.dao.inter.EnderecoDAO;
import model.dao.inter.FornecedorDAO;
import model.dao.inter.TelefoneDAO;
import model.dao.util.BancoDAO;
import model.dao.util.FactoryDAO;
import model.dto.FornecedorDTO;

public class FornecedorImpl implements FornecedorDAO {
    private Connection con;
    private PreparedStatement pstm;
    private ResultSet res;
    private BancoDAO bancoDAO; //FactoryDAO.getInstanceBancoDAO();
    private ArrayList<FornecedorDTO> lista;
    private String sql;
    private boolean toReturn;
    private TelefoneDAO tDAO;
    private EnderecoDAO eDAO;

    public FornecedorImpl() {
        lista = new ArrayList<FornecedorDTO>();
        tDAO = new TelefoneImpl();
        eDAO = new EnderecoImpl();
    }

    public Connection getConnection() {
        this.bancoDAO = FactoryDAO.getInstanceBancoDAO();
        return this.bancoDAO.getConnection();
    }

    public boolean cadastrar(FornecedorDTO f) throws SQLException {
        this.con = this.getConnection();

        this.sql = "INSERT INTO fornecedor(razaoSocial, nomeFantasia, cnpj, nomeRep, email)" +
                "VALUES(?,?,?,?,?)";

        try {

            this.pstm = this.con.prepareStatement(sql);
            this.pstm.setString(1, f.getRazaoSocial());
            this.pstm.setString(2, f.getNomeFantasia());
            this.pstm.setString(3, f.getCnpj());
            this.pstm.setString(4, f.getRepresentante());
            this.pstm.setString(5, f.getEmail());

            if(!this.pstm.execute()) {
                toReturn = true;
            }

        }
        finally {
            this.bancoDAO.closeConnection(this.con, this.pstm, null);
        }

        return toReturn;
    }

    public boolean alterar(FornecedorDTO f) throws SQLException {
        this.con = this.getConnection();

        this.sql = "UPDATE fornecedor SET razaoSocial=?, nomeFantasia=?, cnpj=?, nomeRep=?, email=?" +
                "WHERE idForn= ?";

        try {
            this.pstm = this.con.prepareStatement(sql);
            this.pstm.setString(1, f.getRazaoSocial());
            this.pstm.setString(2, f.getNomeFantasia());
            this.pstm.setString(3, f.getCnpj());
            this.pstm.setString(4, f.getRepresentante());
            this.pstm.setString(5, f.getEmail());
            this.pstm.setInt(6, f.getIdFornecedor());

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

        this.sql = "DELETE FROM fornecedor WHERE idForn= ?";

        try{
            this.pstm = this.con.prepareStatement(this.sql);
            this.pstm.setInt(1, f.getIdFornecedor());

            if(!this.pstm.execute()) {
                toReturn = true;
            }

        }
        finally {
            this.bancoDAO.closeConnection(this.con, this.pstm, null);
        }

        return toReturn;
    }

    public ArrayList<FornecedorDTO> getFornecedorNome(FornecedorDTO f) throws SQLException {
        this.con = this.getConnection();

        this.sql = "SELECT * FROM fornecedor WHERE razaoSocial LIKE ?";

        try {
            this.pstm = this.con.prepareStatement(sql);
            this.pstm.setString(1, "%"+f.getRazaoSocial()+"%");
            this.res = this.pstm.executeQuery();

            lista = popularFornecedor(res);

        }
        finally {
           this.bancoDAO.closeConnection(this.con, this.pstm, this.res);
        }

        return lista;
    }

    public ArrayList<FornecedorDTO> getFornecedores() throws SQLException {
        this.con = this.getConnection();

        this.sql = "SELECT * FROM fornecedor";

        try {
            this.pstm = this.con.prepareStatement(sql);            
            this.res = this.pstm.executeQuery();

            lista = popularFornecedor(res);

        }
        finally {
           this.bancoDAO.closeConnection(this.con, this.pstm, this.res);
        }
        
        return lista;
    }

    public FornecedorDTO getForncedorId(int id) throws SQLException {
        this.con = this.getConnection();

        FornecedorDTO fDTO = null;

        this.sql = "SELECT * FROM fornecedor WHERE idForn= ?";

        try {
            this.pstm = this.con.prepareStatement(sql);
            this.pstm.setInt(1, id);
            this.res = this.pstm.executeQuery();

            lista = popularFornecedor(res);

            if(lista.size() >= 1) {
                fDTO = lista.get(0);
            }

        }
        finally{
            this.bancoDAO.closeConnection(this.con, this.pstm, this.res);
        }

        return fDTO;
    }

    public FornecedorDTO getUltimoForncedor() throws SQLException {
        this.con = this.getConnection();

        FornecedorDTO fDTO = null;

        this.sql = "SELECT * FROM fornecedor";

        try {
            this.pstm = this.con.prepareStatement(sql);
            this.res = this.pstm.executeQuery();

            lista = popularFornecedor(res);
            int tam = lista.size();

            if(tam >= 1) {
                fDTO = lista.get(tam - 1);
            }
            
        }
        finally{
            this.bancoDAO.closeConnection(this.con, this.pstm, this.res);
        }

        return fDTO;
    }

    public FornecedorDTO existeCNPJ(FornecedorDTO f) throws SQLException {
        this.con = this.getConnection();

        FornecedorDTO forn = null;

        this.sql = "SELECT razaoSocial, cnpj FROM fornecedor WHERE cnpj= ?";

        try {
            this.pstm = this.con.prepareStatement(sql);
            this.pstm.setString(1, f.getCnpj());
            this.res = this.pstm.executeQuery();

            while(res.next()) {
                forn = new FornecedorDTO();
                forn.setRazaoSocial(res.getString("razaoSocial"));
                forn.setCnpj(res.getString("cnpj"));
            }

        }
        finally{
            this.bancoDAO.closeConnection(this.con, this.pstm, this.res);
        }

        return forn;
    }

    private ArrayList<FornecedorDTO> popularFornecedor(ResultSet res) throws SQLException {
        ArrayList<FornecedorDTO> listForn = new ArrayList<FornecedorDTO>();
        FornecedorDTO f = null;

        while(res.next()) {
            f = new FornecedorDTO();
            f.setIdFornecedor(res.getInt("idForn"));
            f.setCnpj(res.getString("cnpj"));
            f.setEmail(res.getString("email"));
            f.setRazaoSocial(res.getString("razaoSocial"));
            f.setNomeFantasia(res.getString("nomeFantasia"));
            f.setRepresentante(res.getString("nomeRep"));
            f.setEndereco(eDAO.getEnderecoIdFornecedor(f.getIdFornecedor()));
            f.setTelefone(tDAO.getTelefoneIdFornecedor(f.getIdFornecedor()));
            listForn.add(f);
        }
        
        return listForn;
    }

}
