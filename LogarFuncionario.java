package model.bo.cmd;

import java.sql.*;
import javax.servlet.http.*;
import model.bo.helper.inter.*;
import model.dto.*;
import model.dao.*;
import model.dao.inter.*;

public class LogarFuncionario implements InterfaceCommand, InterfaceSession {
    private FuncionarioDTO fDTO;
    private FuncionarioDAO fDAO;
    private HttpServletRequest request;
    private boolean toReturn;

    public LogarFuncionario() {        
        this.fDTO = new FuncionarioDTO();        
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        this.setRequest(request);
        String pagina = "/login.jsp";
        FuncionarioDTO func = null;
        
        this.fDAO = new FuncionarioImpl();       
        
        setParameter(request);

        try {

            if(enviouCPF()) {
                pagina = "lembrarSenha.jsp";
                return pagina;
            }

            func = getInstanceLoginFuncionario();
            if(func != null) {
                if(isLoginValido(func)){
                    configSessionFuncionario(func);                                        
                    pagina = "SiautoController?oper=entrarSiauto&entidade=login";
                    //pagina = "cadastro_principal.jsp";
                }
            }
        }
        catch(SQLException se) {
            request.setAttribute("msg", "Erro com a base de dados: "+se.getMessage());
            se.printStackTrace();
        }
        
        return pagina;
    }

    public void setParameter(HttpServletRequest request) {
        fDTO.setUsuario(request.getParameter("usuario"));
        fDTO.setSenha(request.getParameter("senha"));
        fDTO.setCpf(request.getParameter("cpf"));
    }   

    public void configSessionFuncionario(FuncionarioDTO func) {        
        //HttpSession session = request.getSession(false); // Obtem nulo ou uma sessao existente
        HttpSession session = request.getSession(false);       
        
        if(session == null) {
            session  = request.getSession();
            //session.setMaxInactiveInterval(20 * 60);

            System.err.println("Sessao Criada: "+session.getId());
            System.err.println("Expira: "+session.getMaxInactiveInterval()+" seg");

        }
        if(session.getAttribute("ssfunc") == null) {
            session.setAttribute("ssfunc", func);
            
            //FuncionarioDTO f = (FuncionarioDTO) session.getAttribute("ssfunc");
            System.err.println("Funcionario na sessao: \n"+session.getAttribute("ssfunc"));
        }
        else {
            FuncionarioDTO f = (FuncionarioDTO) session.getAttribute("ssfunc");
            System.err.println("Funcionário: "+f.getNome());
        }
        request.setAttribute("msg", "ID: "+session.getId());
        
    }

    public FuncionarioDTO getInstanceLoginFuncionario() throws SQLException {
        FuncionarioDTO func = null;        

        if(fDTO.getUsuario() != null && fDTO.getSenha() != null) {
            func = fDAO.getLoginFuncionario(fDTO);
        }
        
        return func;
    }

    public boolean isLoginValido(FuncionarioDTO func) {
        boolean toReturn = true;
 
        if(!func.getSenha().equals(fDTO.getSenha())) {
            request.setAttribute("msg","Senha: "+fDTO.getSenha()+" inválida!");
            return false;
        }
        if(!func.getUsuario().equals(fDTO.getUsuario())){
            request.setAttribute("msg","Usuário: "+fDTO.getUsuario()+" inválido!");
            return false;
        }        

        return toReturn;
    }

    public boolean enviouCPF() throws SQLException {        
        FuncionarioDTO func = null;

        if(fDTO.getCpf() != null) {
            func = fDAO.getFuncionarioCPF(fDTO);
            if(func != null) {
                request.setAttribute("func", func);                
            }
            else{
                request.setAttribute("msg", "Não há usuário registrado no sistema com este CPF!");
            }
            toReturn = true;
        }
        
        return toReturn;
    }

}
