package model.bo.cmd;

import javax.servlet.http.*;

public interface InterfaceCommand {
    
    public String execute(HttpServletRequest request, HttpServletResponse response);
    public void setParameter(HttpServletRequest request);

}
