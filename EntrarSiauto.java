package model.bo.cmd;

import java.sql.*;
import javax.servlet.http.*;

public class EntrarSiauto implements InterfaceCommand {
    private HttpServletRequest request;

    public EntrarSiauto() {
        
    }
    
    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        this.setRequest(request);
        //String pagina = "cadastro_principal.jsp";
        String pagina = "/principal.jsp";

        request.setAttribute("msg", "Cadastro Principal - Usuario está logado!");
        
        return pagina;
    }

    public void setParameter(HttpServletRequest request) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
