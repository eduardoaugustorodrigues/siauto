package model.bo.cmd;

import java.sql.SQLException;
import javax.servlet.http.*;
import model.dao.*;
import model.dao.inter.*;
import model.dto.*;
import java.util.ArrayList;

public class ConsultarCliente implements InterfaceCommand {
    private ClienteDAO cDAO;
    private ClienteDTO cDTO;
    
    public ConsultarCliente() {
        cDTO = new ClienteDTO();
        
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {        
        String pagina = "pesquisarCliente.jsp";
        cDAO = new ClienteImpl();

        setParameter(request);

        try {
            ArrayList<ClienteDTO> clientes = cDAO.getClientesNome(cDTO);

            System.out.println(clientes.size());
            
            if(clientes.size() >= 1) {
                request.setAttribute("lista", clientes);                                
            }
            else {
                request.setAttribute("msg", "Não há clientes cadastrados");
            }
        }
        catch (SQLException se) {
            request.setAttribute("msg", "Erro: " + se.getMessage());
            se.printStackTrace();
        }

        return pagina;
    }

    public void setParameter(HttpServletRequest request) {        
        cDTO.setNome(request.getParameter("psqNome"));
    }
    
}
