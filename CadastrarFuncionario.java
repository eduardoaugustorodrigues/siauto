package model.bo.cmd;

import java.sql.*;
import javax.servlet.http.*;
import model.dao.*;
import model.dao.inter.*;
import model.dto.*;

public class CadastrarFuncionario implements InterfaceCommand {
    private FuncionarioDAO fDAO;
    private FuncionarioDTO fDTO;
    private HttpServletRequest request;

    public CadastrarFuncionario() {
        fDTO = new FuncionarioDTO();
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        setRequest(request);
        String pagina = "funcionario.jsp";
        
        setParameter(request);
        
        fDAO = new FuncionarioImpl();
        
        try {            

            if(existeCPFCadastrado()) {
                return pagina;
            }

            if(existeUsuarioCadastrado()) {
                return pagina;
            }

            if (fDAO.cadastrar(fDTO)) {
                request.setAttribute("msg", "Funcionário(a): <span style='color: #000'>"
                        +fDTO.getNome()+"</span> cadastrado(a)!");
            }
        }
        catch (SQLException se) {
            request.setAttribute("msg", "Erro: " + se.getMessage());
            se.printStackTrace();
        }
        
        return pagina;
    }

    public void setParameter(HttpServletRequest request) {
        fDTO.setNome(request.getParameter("nome"));
        fDTO.setDtNasc(request.getParameter("dtNasc"));
        fDTO.setCpf(request.getParameter("cpf"));
        fDTO.setSalario(Double.valueOf(request.getParameter("salario")));
        fDTO.setDtAdmissao(request.getParameter("admissao"));

        fDTO.setUsuario(request.getParameter("usuario"));
        fDTO.setSenha(request.getParameter("senha"));
    }

    public boolean existeUsuarioCadastrado() throws SQLException {
        boolean toReturn = false;
        FuncionarioDTO func = fDAO.existeUsuario(fDTO);

        if(func != null) {
            if(func.getUsuario().equals(fDTO.getUsuario())){
                request.setAttribute("msg", "Nome de Usuário: <span style='color: #000'>"+func.getUsuario()+"</span> já cadastrado! " +
                    "Informe outro Nome de Usuário.");
            }
            toReturn = true;
        }
        
        return toReturn;
    }

    public boolean existeCPFCadastrado() throws SQLException {
        boolean toReturn = false;
        FuncionarioDTO func = fDAO.existeCPF(fDTO);

        if(func != null) {
            request.setAttribute("msg", "Funcionário(a): <span style='color: #000'>"+func.getNome()+" </span>" +
                "com o CPF: <span style='color: #000'>"+func.getCpf()+"</span> já está cadastrado(a)!");
            toReturn = true;
        }
        
        return toReturn;
    }

}
