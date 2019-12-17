
package model.bo.cmd;

import java.sql.*;
import javax.servlet.http.*;
import model.dao.*;
import model.dao.inter.*;
import model.dto.*;

public class AdicionarItemCompra implements InterfaceCommand {
    private CompraDTO cmpDTO;
    private ItemPecaDTO ipDTO;
    private PecaDTO pDTO;    
    private CompraDAO cmpDAO;
    private PecaDAO pDAO;
    private TipoPecaDTO tpDTO;
    private HttpServletRequest request;
    private boolean toReturn;
    private HttpSession session;

    public AdicionarItemCompra() {
        cmpDTO = new CompraDTO();
        pDTO = new PecaDTO();
        ipDTO = new ItemPecaDTO();
        tpDTO = new TipoPecaDTO();
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        this.setRequest(request);
        String pagina = "/compra.jsp";

        pDAO = new PecaImpl();

        setParameter(request);
        
        try {
            PecaDTO peca = pDAO.getPecaId(pDTO.getIdPeca());            
            ipDTO.setPeca(peca);
            cmpDTO.adicionarItemPeca(ipDTO);

            if(cmpDTO.getListaItens().size() >= 1) {
                if(configCompraSession(cmpDTO)){
                    pagina = "SiautoController?oper=listarItensCompra&entidade=compra";
                    request.setAttribute("msg", request.getSession(false).getAttribute("sscmp"));
                    imprimirListaPeca();
                    
                    return pagina;                    
                }
                if(atualizarCompraSession(cmpDTO)) {
                    pagina = "SiautoController?oper=listarItensCompra&entidade=compra";
                    request.setAttribute("msg", request.getSession(false).getAttribute("sscmp"));                    ;

                    pagina = "SiautoController?oper=listarItensCompra&entidade=compra";
                    request.setAttribute("msg", request.getSession(false).getAttribute("sscmp"));
                    imprimirListaPeca();

                    return pagina;                    
                }
            }
                                    
        }
        catch(SQLException se) {
            request.setAttribute("msg", "Erro de consulta com o banco");
            se.printStackTrace();
        }
        
        return pagina;
    }
    
    public void setParameter(HttpServletRequest request) {        
        
        pDTO.setIdPeca(Integer.valueOf(request.getParameter("idPeca")));

        System.out.println(pDTO.getIdPeca());
    }

    public boolean configCompraSession(CompraDTO cmpDTO) {
        session = request.getSession(false);

        if(session != null) {
            if(session.getAttribute("ssfunc") != null && session.getAttribute("sscmp") == null) {
                session.setAttribute("sscmp", cmpDTO);
                toReturn = true;
                System.err.println("Session Compra: "+session.getId());
            }
        }
        
        return toReturn;
    }

    public void imprimirListaPeca() {
        session = request.getSession(false);
        CompraDTO cmp = null;
        
        if(session != null) {
            cmp = (CompraDTO) session.getAttribute("sscmp");
            if(cmp != null) {
                for(ItemPecaDTO ip: cmp.getListaItens()) {
                    System.out.println("peca: "+ip.getPeca().getNome());
                }
            }
        }
        
    }

    public boolean atualizarCompraSession(CompraDTO cmpDTO) {
        CompraDTO cmp = (CompraDTO) session.getAttribute("sscmp");
        CompraDTO cmpAux = new CompraDTO();
        
        if(cmp != null){
            for(ItemPecaDTO ip : cmpDTO.getListaItens()){
                cmpAux.adicionarItemPeca(ip);
            }
            for(ItemPecaDTO ip : cmpAux.getListaItens()) {
                cmp.adicionarItemPeca(ip);
            }
            session.setAttribute("sscmp", cmp);
            toReturn = true;
        }

        return toReturn;
    }

}
