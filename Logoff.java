
package model.bo.cmd;

import javax.servlet.http.*;

public class Logoff implements InterfaceCommand {
    private HttpServletRequest request;
    
    public Logoff() {
        
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String pagina = "/index.jsp";

        HttpSession session = request.getSession(false);

        if(session != null && session.getAttribute("ssfunc") != null) {
            session.invalidate();
        }
        
        return pagina;
    }

    public void setParameter(HttpServletRequest request) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
