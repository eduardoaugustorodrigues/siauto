package model.bo.cmd;

import java.sql.SQLException;
import javax.servlet.http.*;
import model.dao.*;
import model.dao.inter.*;
import model.dto.*;

public class ExcluirPeca implements InterfaceCommand {
    private PecaDAO pDAO;
    private PecaDTO pDTO;

    public ExcluirPeca() {
        pDTO = new PecaDTO();
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String msg = null;
        String pagina = "SiautoController?entidade=peca&oper=pesquisarPeca&psqNome=";

         pDAO = new PecaImpl();

        setParameter(request);   
        
        try {
            PecaDTO p = pDAO.getPecaId(pDTO.getIdPeca());
            
            if(pDAO.excluir(pDTO)){
                request.setAttribute("msg", "Peça "+p.getNome()+" excluída!");
            }
        }
        catch (SQLException se) {
            request.setAttribute("msg", "Erro com a base de dados: "+se.getMessage());            
            se.printStackTrace();
        }

        return pagina;
    }

    public void setParameter(HttpServletRequest request) {
        pDTO.setIdPeca(Integer.valueOf(request.getParameter("id")));
    }
      
}
