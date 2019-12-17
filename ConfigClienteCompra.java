package model.bo.cmd;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.*;
import model.dto.*;

public class ConfigClienteCompra implements InterfaceCommand {
    private CompraDTO cmpDTO;
    private PecaDTO pDTO;
    private ItemPecaDTO ipDTO;
    private boolean toReturn;
    private HttpServletRequest request;

    public ConfigClienteCompra() {
        cmpDTO = new CompraDTO();
        pDTO = new PecaDTO();
        ipDTO = new ItemPecaDTO();
    }

    public void setRequest(HttpServletRequest request){
        this.request = request;
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        setRequest(request);
        String pagina = "listaItensCompra.jsp";       
        
        setParameter(request);

        if(!excluirItemCompra(ipDTO)){
            request.setAttribute("msg", "Erro ao tentar excluir o item: "+ipDTO.getPeca().getNome());
        }

        return pagina;
    }

    public void setParameter(HttpServletRequest request) {
        pDTO.setIdPeca(Integer.valueOf(request.getParameter("idPeca")));
        ipDTO.setPeca(pDTO);
        ipDTO.setIdItemPeca(pDTO.getIdPeca());
        
    }

    public boolean excluirItemCompra(ItemPecaDTO ipDTO) {
        HttpSession session = request.getSession(false);
        CompraDTO cmpAux = new CompraDTO();
        List<ItemPecaDTO> listaPeca = new ArrayList<ItemPecaDTO>();

        if(session.getAttribute("sscmp") != null) {
            CompraDTO cmp = (CompraDTO) session.getAttribute("sscmp");

            System.out.println(cmp.getListaItens().size());

            cmp.excluirItemPeca(ipDTO.getIdItemPeca());
            
            /*cmpAux = cmp;
            System.out.println(cmpAux.getListaItens().size());

            cmpAux.removerItemPeca(ipDTO);
            
            cmp = cmpAux;
            */
            System.out.println(cmp.getListaItens().size());
            
            session.setAttribute("sscmp", cmp);
            
            toReturn = true;
        }
        else{
            request.setAttribute("msg", "Erro ao tentar excluir o item");
        }
        
        return toReturn;
    }    

}
