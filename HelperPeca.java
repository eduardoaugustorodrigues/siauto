
package model.bo.helper;

import java.util.HashMap;
import javax.servlet.http.*;
import model.bo.cmd.*;
import model.bo.helper.inter.*;

public class HelperPeca implements InterfaceHelper {
    private HashMap<String, InterfaceCommand> mapaComandos;
    private HttpServletRequest request;
    private String cmd;
    
    public HelperPeca(HttpServletRequest request) {
        mapaComandos = new HashMap<String, InterfaceCommand>();
        this.setRequest(request);
        this.cmd = request.getParameter("oper");
    }
    
    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public InterfaceCommand getCommandConfigurado() {        
        
        if(cmd.equals("cadastrarPeca")) {
            mapaComandos.put(cmd, new CadastrarPeca());
        }
        if(cmd.equals("pesquisarPeca")) {
            mapaComandos.put(cmd, new ConsultarPeca());
        }
        if(cmd.equals("alterarPeca") || cmd.equals("getPecaId")) {
            mapaComandos.put(cmd, new AlterarPeca());
        }
        if(cmd.equals("excluirPeca")) {
            mapaComandos.put(cmd, new ExcluirPeca());
        }
        return mapaComandos.get(cmd);
    }

}
