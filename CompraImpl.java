
package model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import model.dao.inter.*;
import model.dao.util.*;
import model.dto.*;
import model.dto.CompraDTO;

public class CompraImpl implements CompraDAO {
    private Connection con;
    private PreparedStatement pstm;
    private ResultSet res;
    private BancoDAO bancoDAO; //FactoryDAO.getInstanceBancoDAO();
    private ArrayList<CompraDTO> lista;
    private String sql;
    private boolean toReturn;
    private ClienteImpl cDAO;
    private FuncionarioImpl fDAO;
    private ItemPecaImpl ipDAO;

    public CompraImpl() {
        lista = new ArrayList<CompraDTO>();
        cDAO = new ClienteImpl();
        fDAO = new FuncionarioImpl();
        ipDAO = new ItemPecaImpl();
    }

    public Connection getConnection() throws SQLException {        
        this.bancoDAO = FactoryDAO.getInstanceBancoDAO();
        return this.bancoDAO.getConnection();    
    }

    public boolean cadastrar(CompraDTO c) throws SQLException {
        this.con = this.getConnection();

        this.sql = "INSERT INTO compra(fk_cliente, fk_funcionario, dt_compra, total_compra)" +
                "VALUES(?,?,?,?)";

        try {
            this.pstm = this.con.prepareStatement(sql);
            this.pstm.setInt(1, c.getCliente().getIdCliente());
            this.pstm.setInt(2, c.getFuncionario().getIdFunc());
            this.pstm.setString(3, null);
            this.pstm.setDouble(4, c.getValorTotal());

            if(!this.pstm.execute()) {
                toReturn = true;
            }

        }
        finally {
            this.bancoDAO.closeConnection(this.con, this.pstm, null);
        }

        return toReturn;
    }

    public CompraDTO getUltimaCompra(int id) throws SQLException {
        CompraDTO cmp = null;

        this.sql = "SELECT * FROM compra";

        try {
            this.pstm = this.con.prepareStatement(sql);
            this.res = this.pstm.executeQuery();

            lista = popularCompra(res);
            int tam = lista.size();

            if(tam >= 1) {
                cmp = lista.get(tam - 1);
            }

        }
        finally{
            this.bancoDAO.closeConnection(this.con, this.pstm, this.res);
        }

        return cmp;
    }

    public PecaDTO getCompraId(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private ArrayList<CompraDTO> popularCompra(ResultSet res) throws SQLException {
        ArrayList<CompraDTO> listaCmp = new ArrayList<CompraDTO>();
        CompraDTO cmp = null;

        while(res.next()) {
            cmp = new CompraDTO();
            cmp.setIdCompra(res.getInt("idComp"));
            cmp.setCliente(cDAO.getClienteId(res.getInt("fk_cliente")));
            cmp.setFuncionario(fDAO.getFuncionarioId(res.getInt("fk_funcionario")));
            //Implementar busca de Item de Peça
            cmp.adicionarItemPeca(ipDAO.getItemPecaId(res.getInt("fk_peca")));
            cmp.setValorTotal(res.getDouble("total_compra"));
            cmp.setDataCompra(null);
            
            listaCmp.add(cmp);
        }
        return listaCmp;
    }

}
