package model.bo.cmd;

import java.sql.SQLException;
import javax.servlet.http.*;
import model.dao.*;
import model.dao.inter.*;
import model.dto.*;

public class ExcluirCliente implements InterfaceCommand {
    private ClienteDAO cDAO;
    private ClienteDTO cDTO;
    private TelefoneDTO tDTO;
    private TipoTelefoneDTO ttDTO;
    private TelefoneDAO tDAO;
    private EnderecoDTO eDTO;
    private TipoTelefoneDAO ttDAO;
    private EnderecoDAO eDAO;

    public ExcluirCliente() {
        cDTO = new ClienteDTO();
        tDTO = new TelefoneDTO();
        ttDTO = new TipoTelefoneDTO();
        eDTO = new EnderecoDTO();
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String msg = null;
        String pagina = "SiautoController?oper=pesquisarCliente&psqNome=";
        
        setParameter(request);   
        
        try {

            cDAO = new ClienteImpl();
            tDAO = new TelefoneImpl();
            ttDAO = new TipoTelefoneImpl();
            eDAO = new EnderecoImpl();
            
            ClienteDTO c = cDAO.getClienteId(cDTO.getIdCliente());
            
            if(cDAO.excluir(cDTO)){
                eDAO.excluir(c);
                tDAO.excluir(c);
                request.setAttribute("msg", "Cliente "+c.getNome()+" excluído!");
            }                
        }
        catch (SQLException se) {
            request.setAttribute("msg", "Erro com a base de dados: "+se.getMessage());            
            se.printStackTrace();
        }

        return pagina;
    }

    public void setParameter(HttpServletRequest request) {
        cDTO.setIdCliente(Integer.valueOf(request.getParameter("id")));
    }
      
}
