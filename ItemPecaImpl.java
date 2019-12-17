
package model.dao;

import java.sql.*;
import java.util.ArrayList;
import model.dao.inter.*;
import model.dao.util.*;
import model.dto.*;
import model.dto.ItemPecaDTO;

public class ItemPecaImpl implements ItemPecaDAO {
    private Connection con;
    private PreparedStatement pstm;
    private ResultSet res;
    private BancoDAO bancoDAO; //FactoryDAO.getInstanceBancoDAO();
    private ArrayList<PecaDTO> lista;
    private String sql;
    private boolean toReturn;
    private PecaDAO pDAO;
    
    public ItemPecaImpl() {
        lista = new ArrayList<PecaDTO>();
        pDAO = new PecaImpl();
    }

    public Connection getConnection() throws SQLException {
        this.bancoDAO = FactoryDAO.getInstanceBancoDAO();
        return this.bancoDAO.getConnection();
    }

    public ItemPecaDTO getItemPecaId(int idItem) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ArrayList<ItemPecaDTO> popularItemPeca(ResultSet res) throws SQLException {
        ArrayList<ItemPecaDTO> listaItem = new ArrayList<ItemPecaDTO>();
        ItemPecaDTO ip = null;

        while (res.next()) {
            ip = new ItemPecaDTO();
            ip.setPeca(pDAO.getPecaId(res.getInt("fk_peca")));
            ip.setQtd(res.getInt("qtd_item"));
            
            listaItem.add(ip);
        }

        return listaItem;
    }

}
