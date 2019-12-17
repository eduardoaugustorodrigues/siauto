
package model.dao;

import model.dao.util.*;
import java.sql.*;
import java.util.ArrayList;
import model.dao.inter.*;
import model.dto.*;

public class FuncionarioImpl implements FuncionarioDAO{
    private Connection con;
    private PreparedStatement pstm;
    private ResultSet res;
    private BancoDAO bancoDAO; 
    private ArrayList<FuncionarioDTO> lista;
    private String sql;   
    private boolean toReturn;    

    public FuncionarioImpl(){        
        lista =  new ArrayList<FuncionarioDTO>();        
    }

    public Connection getConnection(){
        this.bancoDAO = FactoryDAO.getInstanceBancoDAO();
        return this.bancoDAO.getConnection();
    }

    public boolean cadastrar(FuncionarioDTO func) throws SQLException{
        this.con = this.getConnection();
        
        this.sql = "INSERT INTO funcionario(nome, cpf, salario, dtNasc, dt_admissao, " +
                "dt_demissao, usuario, senha)" +
                "VALUES(?,?,?,?,?,?,?,?)";

        try{
            this.pstm = this.con.prepareStatement(sql);
            this.pstm.setString(1, func.getNome());
            this.pstm.setString(2, func.getCpf());
            this.pstm.setDouble(3, func.getSalario());
            this.pstm.setString(4, func.getDtNasc());
            this.pstm.setString(5, func.getDtAdmissao());
            this.pstm.setString(6, func.getDtDemissao());
            this.pstm.setString(7, func.getUsuario());
            this.pstm.setString(8, func.getSenha());

            if(!this.pstm.execute()){
                toReturn = true;
            }

        }
        finally{
            this.bancoDAO.closeConnection(this.con, this.pstm, null);
        }
        return toReturn;
    }

    public boolean alterar(FuncionarioDTO func) throws SQLException{
        this.con = this.getConnection();

        this.sql = "UPDATE funcionario SET nome=?, cpf=?, salario=?, dtNasc=?, dt_admissao=?," +
                "dt_demissao=?, usuario=?, senha=? WHERE idFunc=?";

        try {
            this.pstm = this.con.prepareStatement(sql);
            this.pstm.setString(1, func.getNome());
            this.pstm.setString(2, func.getCpf());
            this.pstm.setDouble(3, func.getSalario());
            this.pstm.setString(4, func.getDtNasc());
            this.pstm.setString(5, func.getDtAdmissao());
            this.pstm.setString(6, func.getDtDemissao());
            this.pstm.setString(7, func.getUsuario());
            this.pstm.setString(8, func.getSenha());
            this.pstm.setInt(9, func.getIdFunc());

            if(!this.pstm.execute()) {
                toReturn = true;
            }
        }
        finally{
            this.bancoDAO.closeConnection(this.con, this.pstm, null);
        }
        return toReturn;
    }

    public boolean alterarLogin(FuncionarioDTO func) throws SQLException{
        this.con = this.getConnection();

        this.sql = "UPDATE funcionario SET usuario=?, senha=? WHERE idFunc=?";

        try {
            this.pstm = this.con.prepareStatement(sql);
            this.pstm.setString(1, func.getUsuario());
            this.pstm.setString(2, func.getSenha());
            this.pstm.setInt(3, func.getIdFunc());

            if(!this.pstm.execute()) {
                toReturn = true;
            }
        }
        finally{
            this.bancoDAO.closeConnection(this.con, this.pstm, null);
        }
        return toReturn;
    }

    public boolean excluir(FuncionarioDTO func) throws SQLException{
        this.con = this.getConnection();
        
        this.sql = "DELETE FROM funcionario WHERE idFunc= ?";

        try{
            this.pstm = this.con.prepareStatement(sql);
            this.pstm.setInt(1, func.getIdFunc());

            if(!this.pstm.execute()) {
                toReturn = true;
            }
            
        }
        finally{
            this.bancoDAO.closeConnection(this.con, this.pstm, null);
        }
        return toReturn;
    }

    public FuncionarioDTO getFuncionarioId(int idFunc) throws SQLException{
        this.con = this.getConnection();
        
        this.sql = "SELECT * FROM funcionario WHERE idFunc= ?";
        FuncionarioDTO func = null;

        try {
            this.pstm = this.con.prepareStatement(sql);
            this.pstm.setInt(1, idFunc);
            this.res = this.pstm.executeQuery();
                
            lista = popularFuncionario(res);

            if(lista.size() >= 1) {
                func = lista.get(0);
            }

        }
        finally{
            this.bancoDAO.closeConnection(this.con, this.pstm, null);
        }
        return func;

    }

    public ArrayList<FuncionarioDTO> getFuncionariosNome(FuncionarioDTO func)
        throws SQLException{
        
        this.con = this.getConnection();
        
        this.sql = "SELECT * FROM funcionario WHERE nome LIKE ?";

        try {
            this.pstm = this.con.prepareStatement(sql);
            this.pstm.setString(1, "%"+func.getNome()+"%");
            this.res = this.pstm.executeQuery();

            lista = popularFuncionario(res);

        }
        finally{
           this.bancoDAO.closeConnection(this.con, this.pstm, this.res);
        }
        return lista;
    }

    public FuncionarioDTO getUltimoFuncionario() throws SQLException {
        this.con = this.getConnection();

        FuncionarioDTO fDTO = null;

        this.sql = "SELECT * FROM funcionario";

        try {
            this.pstm = this.con.prepareStatement(sql);
            this.res = this.pstm.executeQuery();

            lista = popularFuncionario(res);
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

    public FuncionarioDTO getLoginFuncionario(FuncionarioDTO func) throws SQLException {
        this.con = this.getConnection();

        FuncionarioDTO fDTO = null;

        this.sql = "SELECT * FROM funcionario WHERE usuario= ? OR senha= ?";

        try {
            this.pstm = this.con.prepareStatement(sql);
            this.pstm.setString(1, func.getUsuario());
            this.pstm.setString(2, func.getSenha());
            this.res = this.pstm.executeQuery();

            fDTO = popularLoginFuncionario(res);
            
        }
        finally{
            this.bancoDAO.closeConnection(this.con, this.pstm, this.res);
        }

        return fDTO;
    }

    public FuncionarioDTO getFuncionarioCPF(FuncionarioDTO func) throws SQLException {
        this.con = this.getConnection();

        FuncionarioDTO fDTO = null;

        this.sql = "SELECT * FROM funcionario WHERE cpf= ?";

        try {
            this.pstm = this.con.prepareStatement(sql);
            this.pstm.setString(1, func.getCpf());
            this.res = this.pstm.executeQuery();

            fDTO = popularLoginFuncionario(res);

        }
        finally{
            this.bancoDAO.closeConnection(this.con, this.pstm, this.res);
        }

        return fDTO;
        
    }

    public FuncionarioDTO existeCPF(FuncionarioDTO func) throws SQLException {
        this.con = this.getConnection();

        FuncionarioDTO f = null;

        this.sql = "SELECT nome, cpf FROM funcionario WHERE cpf= ?";

        try {
            this.pstm = this.con.prepareStatement(sql);
            this.pstm.setString(1, func.getCpf());
            this.res = this.pstm.executeQuery();

            while(res.next()) {
                f = new FuncionarioDTO();
                f.setNome(res.getString("nome"));
                f.setCpf(res.getString("cpf"));
            }

        }
        finally{
            this.bancoDAO.closeConnection(this.con, this.pstm, this.res);
        }

        return f;
    }

    public FuncionarioDTO existeUsuario(FuncionarioDTO func) throws SQLException {
        this.con = this.getConnection();

        FuncionarioDTO f = null;

        this.sql = "SELECT nome, usuario, senha FROM funcionario WHERE usuario= ?";

        try {
            this.pstm = this.con.prepareStatement(sql);
            this.pstm.setString(1, func.getUsuario());
            this.res = this.pstm.executeQuery();

            while(res.next()) {
                f = new FuncionarioDTO();
                f.setNome(res.getString("nome"));
                f.setUsuario(res.getString("usuario"));
                f.setSenha(res.getString("senha"));
            }

        }
        finally{
            this.bancoDAO.closeConnection(this.con, this.pstm, this.res);
        }

        return f;
    }

    public ArrayList<FuncionarioDTO> popularFuncionario(ResultSet res) throws SQLException {

        ArrayList<FuncionarioDTO> listFunc =  new ArrayList<FuncionarioDTO>();
        FuncionarioDTO func = null;

        while(res.next()){
            func = new FuncionarioDTO();
            func.setIdFunc(res.getInt("idFunc"));
            func.setNome(res.getString("nome"));
            func.setCpf(res.getString("cpf"));
            func.setDtAdmissao(res.getString("dt_admissao"));
            func.setDtDemissao(res.getString("dt_demissao"));
            func.setDtNasc(res.getString("dtNasc"));
            func.setSalario(res.getDouble("salario"));
            func.setUsuario(res.getString("usuario"));
            func.setSenha(res.getString("senha"));
            
            listFunc.add(func);
        }
        
        return listFunc;
    }

    public FuncionarioDTO popularLoginFuncionario(ResultSet res) throws SQLException {
        FuncionarioDTO func = null;

        if(res.next()){
            func = new FuncionarioDTO();
            func.setIdFunc(res.getInt("idFunc"));
            func.setNome(res.getString("nome"));
            func.setUsuario(res.getString("usuario"));
            func.setSenha(res.getString("senha"));
            //func.setCpf(res.getString("cpf"));
        }

        return func;
    }

}