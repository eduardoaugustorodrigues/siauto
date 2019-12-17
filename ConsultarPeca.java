package model.bo.cmd;

import java.sql.SQLException;
import javax.servlet.http.*;
import model.dao.*;
import model.dao.inter.*;
import model.dto.*;
import java.util.ArrayList;

public class ConsultarPeca implements InterfaceCommand {
    private PecaDAO pDAO;
    private PecaDTO pDTO;
    
    public ConsultarPeca() {
        pDTO = new PecaDTO();
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {                
        String pagina = "pesquisarPeca.jsp";        
        pDAO = new PecaImpl();

        setParameter(request);

        try {
            ArrayList<PecaDTO> pecas = pDAO.getPecasNome(pDTO);
            int tam = pecas.size();            
            
            if(tam >= 1) {
                request.setAttribute("lista", pecas);
            }
            else {
                request.setAttribute("msg", "Não há peças cadastradas");
            }
        }
        catch (SQLException se) {
            request.setAttribute("msg", "Erro: " + se.getMessage());
            se.printStackTrace();
        }

        return pagina;
    }

    public void setParameter(HttpServletRequest request) {        
        pDTO.setNome(request.getParameter("psqNome"));
    }
    
}
