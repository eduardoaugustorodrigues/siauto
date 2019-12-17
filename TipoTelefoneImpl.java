package model.dao;

import java.sql.*;
import java.util.ArrayList;
import model.dao.inter.TipoTelefoneDAO;
import model.dao.util.BancoDAO;
import model.dao.util.FactoryDAO;
import model.dto.TipoTelefoneDTO;

public class TipoTelefoneImpl implements TipoTelefoneDAO {

    private Connection con;
    private PreparedStatement pstm;
    private ResultSet res;
    private BancoDAO bancoDAO; //FactoryDAO.getInstanceBancoDAO();    
    private String sql;
    private ArrayList<TipoTelefoneDTO> lista;
    private boolean toReturn;

    public TipoTelefoneImpl() {
        lista = new ArrayList<TipoTelefoneDTO>();
    }

    public Connection getConnection() {
        this.bancoDAO = FactoryDAO.getInstanceBancoDAO();
        return this.bancoDAO.getConnection();
    }
    /*
    public boolean cadastrar(TipoTelefoneDTO tt) throws SQLException {
        //this.con = this.bancoDAO.getConnection();this
        this.con = this.getConnection();

        this.sql = "INSERT INTO tipo_tel(desc_tipotel) VALUES(?)";

        try {

            this.pstm = this.con.prepareStatement(sql);
            this.pstm.setString(1, tt.getDescricao());

            if (!this.pstm.execute()) {
                toReturn = true;
            }

        } finally {
            this.bancoDAO.closeConnection(this.con, this.pstm, null);
        }

        return toReturn;
    }
*/
    
    public TipoTelefoneDTO getTipoTelefoneId(int id) throws SQLException {
        this.con = this.getConnection();

        TipoTelefoneDTO tt = null;

        this.sql = "SELECT * FROM tipo_tel WHERE idTipoTel = ?";

        try {
            this.pstm = this.con.prepareStatement(sql);
            this.pstm.setInt(1, id);
            this.res = this.pstm.executeQuery();

            lista = getListaTipoTelefone(res);
            int tam = lista.size();

            tt = lista.get(tam - 1);            

        }
        finally {
            this.bancoDAO.closeConnection(this.con, this.pstm, this.res);
        }

        return tt;

    }

    public TipoTelefoneDTO getUltimoTipoTelefone() throws SQLException {
        this.con = this.getConnection();

        TipoTelefoneDTO tt = null;

        this.sql = "SELECT * FROM tipo_tel";

        try {
            this.pstm = this.con.prepareStatement(sql);            
            this.res = this.pstm.executeQuery();

            lista = getListaTipoTelefone(res);
            int tam = lista.size();

            if(tam >= 1) {
                tt = lista.get(tam - 1);
            }

        }
        finally {
            this.bancoDAO.closeConnection(this.con, this.pstm, this.res);
        }

        return tt;
    }

    public ArrayList<TipoTelefoneDTO> getListaTipoTelefone(ResultSet res) throws SQLException {
        ArrayList<TipoTelefoneDTO> listTipo = new ArrayList<TipoTelefoneDTO>();
        TipoTelefoneDTO tt = null;

        while(res.next()) {
            tt = new TipoTelefoneDTO();
            tt.setIdTipoTel(res.getInt("idTipoTel"));
            tt.setDescricao(res.getString("desc_tipotel"));
            listTipo.add(tt);
        }

        return listTipo;
    }

    public ArrayList<TipoTelefoneDTO> getListaTipoTelefone() throws SQLException {
        this.con = this.getConnection();      

        this.sql = "SELECT * FROM tipo_tel";

        try {
            this.pstm = this.con.prepareStatement(sql);
            this.res = this.pstm.executeQuery();

            lista = getListaTipoTelefone(res);

        }
        finally {
            this.bancoDAO.closeConnection(this.con, this.pstm, this.res);
        }

        return lista;
    }
    
}

