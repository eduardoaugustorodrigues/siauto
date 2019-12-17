package model.bo.cmd;

import java.sql.SQLException;
import javax.servlet.http.*;
import model.dao.*;
import model.dao.inter.*;
import model.dto.*;

public class ExcluirFuncionario implements InterfaceCommand {
    private FuncionarioDAO fDAO;
    private FuncionarioDTO fDTO;

    public ExcluirFuncionario() {
        fDTO = new FuncionarioDTO();
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {        
        String pagina = "SiautoController?oper=pesquisarFuncionario&psqNome=";
        
        setParameter(request);

        fDAO = new FuncionarioImpl();
        
        try {
            FuncionarioDTO f = fDAO.getFuncionarioId(fDTO.getIdFunc());
            
            if(fDAO.excluir(fDTO)){
                request.setAttribute("msg", "Funcionário "+f.getNome()+" excluído!");
            }                
        }
        catch (SQLException se) {
            request.setAttribute("msg", "Erro com a base de dados: "+se.getMessage());            
            se.printStackTrace();
        }

        return pagina;
    }

    public void setParameter(HttpServletRequest request) {
        fDTO.setIdFunc(Integer.valueOf(request.getParameter("id")));
    }
      
}
