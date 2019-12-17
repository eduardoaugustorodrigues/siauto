
package model.bo.helper;

import java.util.HashMap;
import javax.servlet.http.*;
import model.bo.cmd.*;
import model.bo.helper.inter.*;

public class HelperFornecedor implements InterfaceHelper {
    private HashMap<String, InterfaceCommand> mapaComandos;
    private HttpServletRequest request;
    private String cmd;
    
    public HelperFornecedor(HttpServletRequest request) {
        mapaComandos = new HashMap<String, InterfaceCommand>();
        this.setRequest(request);
        this.cmd = request.getParameter("oper");
    }
    
    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public InterfaceCommand getCommandConfigurado() {
        
        if(cmd.equals("cadastrarFornecedor")) {
            mapaComandos.put(cmd, new CadastrarFornecedor());
        }
        if(cmd.equals("pesquisarFornecedor")) {
            mapaComandos.put(cmd, new ConsultarFornecedor());
        }
        if(cmd.equals("alterarFornecedor") || cmd.equals("getFornecedorId")) {
            mapaComandos.put(cmd, new AlterarFornecedor());
        }
        if(cmd.equals("excluirFornecedor")) {
            mapaComandos.put(cmd, new ExcluirFornecedor());
        }
        return mapaComandos.get(cmd);
    }

}
