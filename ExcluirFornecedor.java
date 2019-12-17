package model.bo.cmd;

import java.sql.SQLException;
import javax.servlet.http.*;
import model.dao.*;
import model.dao.inter.*;
import model.dto.*;

public class ExcluirFornecedor implements InterfaceCommand {
    private FornecedorDAO fDAO;
    private FornecedorDTO fDTO;
    private TelefoneDTO tDTO;
    private TipoTelefoneDTO ttDTO;
    private TelefoneDAO tDAO;
    private EnderecoDTO eDTO;
    private TipoTelefoneDAO ttDAO;
    private EnderecoDAO eDAO;

    public ExcluirFornecedor() {
        fDTO = new FornecedorDTO();
        tDTO = new TelefoneDTO();
        ttDTO = new TipoTelefoneDTO();
        eDTO = new EnderecoDTO();
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String msg = null;
        String pagina = "SiautoController?oper=pesquisarFornecedor&psqNome=";
        
        setParameter(request);   
        
        try {

            fDAO = new FornecedorImpl();
            tDAO = new TelefoneImpl();
            ttDAO = new TipoTelefoneImpl();
            eDAO = new EnderecoImpl();
            
            FornecedorDTO f = fDAO.getForncedorId(fDTO.getIdFornecedor());
            
            if(fDAO.excluir(fDTO)){
                eDAO.excluir(f);
                tDAO.excluir(f);
                request.setAttribute("msg", "Fornecedor "+f.getRazaoSocial()+" excluído!");
            }                
        }
        catch (SQLException se) {
            request.setAttribute("msg", "Erro com a base de dados: "+se.getMessage());            
            se.printStackTrace();
        }

        return pagina;
    }

    public void setParameter(HttpServletRequest request) {
        fDTO.setIdFornecedor(Integer.valueOf(request.getParameter("id")));
    }
      
}
