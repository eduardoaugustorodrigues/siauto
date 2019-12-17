package model.bo.cmd;

import java.sql.SQLException;
import javax.servlet.http.*;
import model.dao.*;
import model.dao.inter.*;
import model.dto.*;
import java.util.ArrayList;

public class ConsultarFornecedor implements InterfaceCommand {
    private FornecedorDAO fDAO;
    private FornecedorDTO fDTO;
    
    public ConsultarFornecedor() {
        fDTO = new FornecedorDTO();
        
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {        
        String pagina = "pesquisarFornecedor.jsp";
        
        fDAO = new FornecedorImpl();

        setParameter(request);

        try {
            ArrayList<FornecedorDTO> listFornecedor = fDAO.getFornecedorNome(fDTO);           
            
            if(listFornecedor.size() >= 1) {
                request.setAttribute("lista", listFornecedor);
            }
            else {
                request.setAttribute("msg", "Não há fornecedores cadastrados");
            }
        }
        catch (SQLException se) {
            request.setAttribute("msg", "Erro: " + se.getMessage());
            se.printStackTrace();
        }

        return pagina;
    }

    public void setParameter(HttpServletRequest request) {        
        fDTO.setRazaoSocial(request.getParameter("psqNome"));
    }
    
}
