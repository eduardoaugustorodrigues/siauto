
package model.dao;

import model.dao.util.BancoDAO;
import model.dao.util.FactoryDAO;
import java.sql.*;
import java.util.ArrayList;
import model.dao.inter.TipoPecaDAO;
import model.dto.TipoPecaDTO;

public class TipoPecaImpl implements TipoPecaDAO {
    private Connection con;
    private PreparedStatement pstm;
    private ResultSet res;
    private BancoDAO bancoDAO;
    private String sql;
    private ArrayList<TipoPecaDTO> lista;

    public TipoPecaImpl() {
        lista =  new ArrayList<TipoPecaDTO>();
    }

    public Connection getConnection() {
        this.bancoDAO = FactoryDAO.getInstanceBancoDAO();
        return this.bancoDAO.getConnection();
    }

    public TipoPecaDTO getTipoPecaId(int id) throws SQLException{
        this.con = this.getConnection();
        
        TipoPecaDTO tp = null;

        this.sql = "SELECT * FROM tipo_peca WHERE idTipoPeca= ?";

        try {
            this.pstm = this.con.prepareStatement(sql);
            this.pstm.setInt(1, id);
            this.res = this.pstm.executeQuery();

            lista = popularTipoPeca(res);
            int tam = lista.size();

            if(tam >= 1) {
                tp = lista.get(tam - 1);
            }
            
        }
        finally{
            this.bancoDAO.closeConnection(con, pstm, res);
        }
        return tp;
    }

    public ArrayList<TipoPecaDTO> getAllTiposPeca() throws SQLException {
        this.con = this.getConnection();       

        this.sql = "SELECT * FROM tipo_peca";

        try {
            this.pstm = this.con.prepareStatement(sql);            
            this.res = this.pstm.executeQuery();

            lista = popularTipoPeca(res);
            
        }
        finally {
            this.bancoDAO.closeConnection(con, pstm, res);
        }
        
        return lista;
    }

    public ArrayList<TipoPecaDTO> popularTipoPeca(ResultSet res) throws SQLException {
        ArrayList<TipoPecaDTO> listTP = new ArrayList<TipoPecaDTO>();
        TipoPecaDTO tp = null;

        while(res.next()) {
            tp = new TipoPecaDTO();
            tp.setIdTipoPeca(res.getInt("idTipoPeca"));
            tp.setDescricao(res.getString("desc_TipoPeca"));
            listTP.add(tp);
        }

        return listTP;
    }

    public TipoPecaDTO getTipoPecaDescricao(TipoPecaDTO tipoPeca) throws SQLException {
        this.con = this.getConnection();

        TipoPecaDTO tp = null;

        this.sql = "SELECT * FROM tipo_peca WHERE desc_TipoPeca= ?";

        try {
            this.pstm = this.con.prepareStatement(sql);
            this.pstm.setString(1, tipoPeca.getDescricao());
            this.res = this.pstm.executeQuery();

            lista = popularTipoPeca(res);
            int tam = lista.size();

            if(tam >= 1) {
                tp = lista.get(tam - 1);
            }

        }
        finally{
            this.bancoDAO.closeConnection(con, pstm, res);
        }

        return tp;
    }
    
}
