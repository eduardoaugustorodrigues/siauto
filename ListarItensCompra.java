package model.bo.cmd;

import javax.servlet.http.*;
import model.dao.*;
import model.dao.inter.CompraDAO;
import model.dto.*;

public class ListarItensCompra implements InterfaceCommand {
    private CompraDTO cmpDTO;
    private CompraDAO cmpDAO;

    public ListarItensCompra() {
        cmpDTO = new CompraDTO();
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String pagina = "listaItensCompra.jsp";

        cmpDAO = new CompraImpl();
        
        setParameter(request);
                
        return pagina;
    }

    public void setParameter(HttpServletRequest request) {
        
    }

    public CompraDTO getListaItensCompra() {

        return cmpDTO;
    }

}
