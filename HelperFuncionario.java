
package model.bo.helper;

import java.util.HashMap;
import javax.servlet.http.*;
import model.bo.cmd.*;
import model.bo.helper.inter.*;

public class HelperFuncionario implements InterfaceHelper {
    private HashMap<String, InterfaceCommand> mapaComandos;
    private HttpServletRequest request;
    private String cmd;
    
    public HelperFuncionario(HttpServletRequest request) {
        mapaComandos = new HashMap<String, InterfaceCommand>();
        this.setRequest(request);
        this.cmd = this.request.getParameter("oper");
    }
    
    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public InterfaceCommand getCommandConfigurado() {        
        
        if(cmd.equals("cadastrarFuncionario")) {
            mapaComandos.put(cmd, new CadastrarFuncionario());
        }
        if(cmd.equals("pesquisarFuncionario")) {
            mapaComandos.put(cmd, new ConsultarFuncionario());
        }
        if(cmd.equals("alterarFuncionario") || cmd.equals("getFuncId")) {
            mapaComandos.put(cmd, new AlterarFuncionario());
        }
        if(cmd.equals("excluirFuncionario")) {
            mapaComandos.put(cmd, new ExcluirFuncionario());
        }
        return mapaComandos.get(cmd);
    }

}
