
package model.bo.helper;

import java.util.HashMap;
import javax.servlet.http.*;
import model.bo.cmd.*;
import model.bo.helper.inter.*;

public class HelperCliente implements InterfaceHelper {
    private HashMap<String, InterfaceCommand> mapaComandos;
    private HttpServletRequest request;
    private String cmd;
    
    public HelperCliente(HttpServletRequest request) {
        mapaComandos = new HashMap<String, InterfaceCommand>();
        this.setRequest(request);
        this.cmd = request.getParameter("oper");
    }
    
    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public InterfaceCommand getCommandConfigurado() {
        
        if(cmd.equals("cadastrarCliente")) {
            mapaComandos.put(cmd, new CadastrarCliente());
        }
        if(cmd.equals("pesquisarCliente")) {
            mapaComandos.put(cmd, new ConsultarCliente());
        }
        if(cmd.equals("alterarCliente") || cmd.equals("getClienteId")) {
            mapaComandos.put(cmd, new AlterarCliente());
        }
        if(cmd.equals("excluirCliente")) {
            mapaComandos.put(cmd, new ExcluirCliente());
        }
        return mapaComandos.get(cmd);
    }

}
