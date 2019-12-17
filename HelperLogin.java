
package model.bo.helper;

import java.util.HashMap;
import javax.servlet.http.*;
import model.bo.cmd.*;
import model.bo.helper.inter.*;

public class HelperLogin implements InterfaceHelper {
    private HashMap<String, InterfaceCommand> mapaComandos;
    private HttpServletRequest request;
    private String cmd;
    
    public HelperLogin(HttpServletRequest request) {
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
        
        if(cmd.equals("logarFuncionario")) {
            mapaComandos.put(cmd, new LogarFuncionario());
        }
        if(cmd.equals("entrarSiauto")) {
            mapaComandos.put(cmd, new EntrarSiauto());
        }
        if(cmd.equals("logoff")) {
            mapaComandos.put(cmd, new Logoff());
        }
        /*
        if(cmd.equals("lembrarSenha")) {
            //mapaComandos.put(cmd, new LembrarFuncionario());
        }
         */
        
        return mapaComandos.get(cmd);
    }           
    
}
