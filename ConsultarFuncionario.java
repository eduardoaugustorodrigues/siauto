package model.bo.cmd;

import java.sql.SQLException;
import javax.servlet.http.*;
import model.dao.*;
import model.dao.inter.*;
import model.dto.*;
import java.util.ArrayList;

public class ConsultarFuncionario implements InterfaceCommand {
    private FuncionarioDAO fDAO;
    private FuncionarioDTO fDTO;
    
    public ConsultarFuncionario() {
        fDTO = new FuncionarioDTO();
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {        
        String pagina = "pesquisarFuncionario.jsp";

        setParameter(request);

        fDAO = new FuncionarioImpl();

        try {
            ArrayList<FuncionarioDTO> listFunc = fDAO.getFuncionariosNome(fDTO);
            
            if(listFunc.size() >= 1) {
                request.setAttribute("lista", listFunc);
            }
            else {
                request.setAttribute("msg", "Não há funcionários cadastrados");
            }
        }
        catch (SQLException se) {
            request.setAttribute("msg", "Erro: " + se.getMessage());
            se.printStackTrace();
        }

        return pagina;
    }

    public void setParameter(HttpServletRequest request) {        
        fDTO.setNome(request.getParameter("psqNome"));
    }
    
}
