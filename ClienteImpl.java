
package model.dao;

import model.dao.util.BancoDAO;
import model.dao.util.FactoryDAO;
import java.sql.*;
import java.util.ArrayList;
import model.dao.inter.ClienteDAO;
import model.dao.inter.EnderecoDAO;
import model.dao.inter.TelefoneDAO;
import model.dto.ClienteDTO;

public class ClienteImpl implements ClienteDAO {
    private Connection con;
    private PreparedStatement pstm;
    private ResultSet res;
    private BancoDAO bancoDAO; //FactoryDAO.getInstanceBancoDAO();
    private ArrayList<ClienteDTO> lista;
    private String sql;
    private boolean toReturn;
    private TelefoneDAO tDAO;
    private EnderecoDAO eDAO;

    public ClienteImpl() {
        lista =  new ArrayList<ClienteDTO>();
        tDAO = new TelefoneImpl();
        eDAO = new EnderecoImpl();
    }

    public Connection getConnection() {
        this.bancoDAO = FactoryDAO.getInstanceBancoDAO();
        return this.bancoDAO.getConnection();
    }
    
    public boolean cadastrar(ClienteDTO c) throws SQLException {
        //this.con = this.bancoDAO.getConnection();
        this.con = this.getConnection();

        this.sql = "INSERT INTO cliente(nome, dtNasc, cpf) VALUES(?,?,?)";
       
        try {
        
            this.pstm = this.con.prepareStatement(sql);
            this.pstm.setString(1, c.getNome());
            this.pstm.setString(2, c.getDtNasc());
            this.pstm.setString(3, c.getCpf());

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
        
        this.sql = "UPDATE cliente SET nome=?, dtNasc=?, cpf=? WHERE idCliente=?";

        try {
            this.pstm = this.con.prepareStatement(sql);
            this.pstm.setString(1, c.getNome());
            this.pstm.setString(2, c.getDtNasc());
            this.pstm.setString(3, c.getCpf());
            this.pstm.setInt(4, c.getIdCliente());

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

        this.sql = "DELETE FROM cliente WHERE idCliente= ?";

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

    public ArrayList<ClienteDTO> getClientesNome(ClienteDTO c) throws SQLException {
        //this.con = this.bancoDAO.getConnection();
        this.con = this.getConnection();
        
        this.sql = "SELECT * FROM cliente WHERE nome LIKE ?";

        try {
            this.pstm = this.con.prepareStatement(sql);
            this.pstm.setString(1, "%"+c.getNome()+"%");            
            this.res = this.pstm.executeQuery();

            lista = getListaClientes(res);
            
        }
        finally {
           this.bancoDAO.closeConnection(this.con, this.pstm, this.res);
        }
        
        return lista;
        
    }

    public ClienteDTO getClienteId(int idCliente) throws SQLException {
        //this.con = this.bancoDAO.getConnection();
        this.con = this.getConnection();
        
        ClienteDTO cliente = null;

        this.sql = "SELECT * FROM cliente WHERE idCliente = ?";

        try {
            this.pstm = this.con.prepareStatement(sql);
            this.pstm.setInt(1, idCliente);
            this.res = this.pstm.executeQuery();

            lista = getListaClientes(res);

            if(lista.size() >= 1) {
                cliente = lista.get(0);
            }
            
        }
        finally{
            this.bancoDAO.closeConnection(this.con, this.pstm, this.res);
        }
        
        return cliente;

    }

    public ClienteDTO existeCPF(ClienteDTO c) throws SQLException {
        this.con = this.getConnection();

        ClienteDTO cliente = null;

        this.sql = "SELECT nome, cpf FROM cliente WHERE cpf= ?";

        try {
            this.pstm = this.con.prepareStatement(sql);
            this.pstm.setString(1, c.getCpf());
            this.res = this.pstm.executeQuery();

            while(res.next()) {
                cliente = new ClienteDTO();
                cliente.setNome(res.getString("nome"));
                cliente.setCpf(res.getString("cpf"));
            }

        }
        finally{
            this.bancoDAO.closeConnection(this.con, this.pstm, this.res);
        }

        return cliente;                        
    }

    private ArrayList<ClienteDTO> getListaClientes(ResultSet res) throws SQLException {
        ArrayList<ClienteDTO> listCliente = new ArrayList<ClienteDTO>();
        ClienteDTO c = null;
        
        while(res.next()){
            c = new ClienteDTO();
            c.setIdCliente(res.getInt("idCliente"));
            c.setNome(res.getString("nome"));
            c.setDtNasc(res.getString("dtNasc"));
            c.setCpf(res.getString("cpf"));
            c.setTelefone(tDAO.getTelefoneIdCliente(c.getIdCliente()));
            c.setEndereco(eDAO.getEnderecoIdCliente(c.getIdCliente()));
            
            listCliente.add(c);
        }
        
        return listCliente;
        
    }

    public ClienteDTO getUltimoCliente() throws SQLException {
        this.con = this.getConnection();

        ClienteDTO cliente = null;

        this.sql = "SELECT * FROM cliente";

        try {
            this.pstm = this.con.prepareStatement(sql);            
            this.res = this.pstm.executeQuery();

            lista = getListaClientes(res);
            int tam = lista.size();

            if(tam >= 1) {
                cliente = lista.get(tam - 1);
            }

        }
        finally{
            this.bancoDAO.closeConnection(this.con, this.pstm, this.res);
        }

        return cliente;
    }

}
