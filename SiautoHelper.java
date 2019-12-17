
package model.bo.helper;

import java.util.HashMap;
import javax.servlet.http.*;
import model.bo.cmd.*;
import model.bo.helper.inter.*;
import model.dto.FuncionarioDTO;

public class SiautoHelper {
    private HashMap<String, InterfaceHelper> mapaHelpers;
    private HttpServletRequest request;    
    private String entidade;

    public SiautoHelper () {
        mapaHelpers = new HashMap<String, InterfaceHelper>();
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
        entidade = request.getParameter("entidade");
    }
/*
    public InterfaceCommand getCommand() {
        
        return getHelperConfigurado().getCommandConfigurado();
    }
*/

    public InterfaceCommand getCommand() {       
        InterfaceCommand interCommand = null;        

        // if(!usuarioLogado() && entidade == null) {
        if(!usuarioLogado()) {
            HelperLogin helperLogin = (HelperLogin) getHelperConfigurado("login");
            helperLogin.setOper("logarFuncionario");
            interCommand = helperLogin.getCommandConfigurado();
        }
        else{
            interCommand = getHelperConfigurado().getCommandConfigurado();
        }
        
        return interCommand;
                
    }
    // estava retornando InterfaceHelper -> mapaHelper.get(entidade);
    public InterfaceHelper getHelperConfigurado() {

        if(entidade.equals("cliente")) {
            mapaHelpers.put(entidade, new HelperCliente(request));
        }
        if(entidade.equals("funcionario")) {
            mapaHelpers.put(entidade, new HelperFuncionario(request));
        }
        if(entidade.equals("peca")) {
            mapaHelpers.put(entidade, new HelperPeca(request));
        }
        if(entidade.equals("login")) {
            mapaHelpers.put(entidade, new HelperLogin(request));
        }
        if(entidade.equals("fornecedor")) {
            mapaHelpers.put(entidade, new HelperFornecedor(request));
        }
        if(entidade.equals("compra")) {
            mapaHelpers.put(entidade, new HelperCompra(request));
        }
        
        //return mapaHelpers.get(entidade);       

        return  mapaHelpers.get(entidade);

    }

    public InterfaceHelper getHelperConfigurado(String entidade) {

        if(entidade.equals("cliente")) {
            mapaHelpers.put(entidade, new HelperCliente(request));
        }
        if(entidade.equals("funcionario")) {
            mapaHelpers.put(entidade, new HelperFuncionario(request));
        }
        if(entidade.equals("peca")) {
            mapaHelpers.put(entidade, new HelperPeca(request));
        }
        if(entidade.equals("login")) {
            mapaHelpers.put(entidade, new HelperLogin(request));
        }
        if(entidade.equals("fornecedor")) {
            mapaHelpers.put(entidade, new HelperFornecedor(request));
        }
        if(entidade.equals("compra")) {
            mapaHelpers.put(entidade, new HelperCompra(request));
        }

        //return mapaHelpers.get(entidade);

        return  mapaHelpers.get(entidade);

    }

    public boolean usuarioLogado() {
        boolean toReturn = false;        

        HttpSession session = request.getSession(false);

        if(session.getAttribute("ssfunc") != null) {            
            toReturn = true;
        }
        
        return toReturn;
    }

}
