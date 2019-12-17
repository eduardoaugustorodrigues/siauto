
package model.bo.helper;

import java.util.HashMap;
import javax.servlet.http.*;
import model.bo.cmd.*;
import model.bo.helper.inter.*;

public class HelperCompra implements InterfaceHelper {
    private HashMap<String, InterfaceCommand> mapaComandos;
    private HttpServletRequest request;
    private String cmd;
    
    public HelperCompra(HttpServletRequest request) {
        mapaComandos = new HashMap<String, InterfaceCommand>();
        this.setRequest(request);
        this.cmd = request.getParameter("oper");
    }
    
    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public void setOper(String oper) {
        this.cmd = oper;
    }

    public InterfaceCommand getCommandConfigurado() {
        
        if(cmd.equals("adicionarItem")) {
            mapaComandos.put(cmd, new AdicionarItemCompra());
        }
        if(cmd.equals("listarItensCompra")) {
            mapaComandos.put(cmd, new ListarItensCompra());
        }
        
        if(cmd.equals("removerItem")) {
            mapaComandos.put(cmd, new RemoverItemCompra());
        }

        if(cmd.equals("configCliente")){
            mapaComandos.put(cmd, new ConfigClienteCompra());
        }
        
        return mapaComandos.get(cmd);
    }
    
}
