package model.bo.cmd;

import java.sql.SQLException;
import javax.servlet.http.*;
import model.dao.*;
import model.dao.inter.*;
import model.dto.*;

public class AlterarFuncionario implements InterfaceCommand {
    private FuncionarioDAO fDAO = new FuncionarioImpl();
    private FuncionarioDTO fDTO;

    public AlterarFuncionario() {
        fDTO = new FuncionarioDTO();
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String pagina = "alterarFuncionario.jsp";
        String cmd = request.getParameter("oper");

        setParameter(request);

        try {                        

            if(cmd.equals("getFuncId")){
                FuncionarioDTO func = fDAO.getFuncionarioId(fDTO.getIdFunc());
                request.setAttribute("func", func);
            }
            else {
                setParametersAll(request);
                fDAO.alterar(fDTO);
                request.setAttribute("msg", "Funcionário "+fDTO.getNome()+" atualizado!");
                pagina = "SiautoController?entidade=funcionario&oper=pesquisarFuncionario&psqNome="+fDTO.getNome();
            }

        }
        catch (SQLException se) {
            request.setAttribute("msg", "Erro: " + se.getMessage());
            se.printStackTrace();
        }

        return pagina;
    }

    public void setParameter(HttpServletRequest request) {
        fDTO.setIdFunc(Integer.parseInt(request.getParameter("id")));
    }

    public void setParametersAll(HttpServletRequest request) {
        setParameter(request);
        fDTO.setNome(request.getParameter("nome"));
        fDTO.setDtNasc(request.getParameter("dtNasc"));
        fDTO.setCpf(request.getParameter("cpf"));
        fDTO.setSalario(Double.valueOf(request.getParameter("salario")));
        fDTO.setDtAdmissao(request.getParameter("admissao"));

        fDTO.setUsuario(request.getParameter("usuario"));
        fDTO.setSenha(request.getParameter("senha"));
    }
    

}
