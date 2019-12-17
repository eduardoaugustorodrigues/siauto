package model.bo.cmd;

import java.sql.SQLException;
import javax.servlet.http.*;
import model.dao.*;
import model.dao.inter.*;
import model.dto.*;

public class CadastrarPeca implements InterfaceCommand {
    private PecaDAO pDAO;
    private PecaDTO pDTO;
    private FornecedorDTO fDTO;
    private FornecedorDAO fDAO;
    private TipoPecaDAO tpDAO;
    private TipoPecaDTO tpDTO;

    public CadastrarPeca() {
        pDTO = new PecaDTO();
        tpDTO = new TipoPecaDTO();
        fDTO = new FornecedorDTO();
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String pagina = "peca.jsp";

        pDAO = new PecaImpl();
        //tpDAO = new TipoPecaImpl();
        
        setParameter(request);
        
        try {                      
            if (pDAO.cadastrar(pDTO)) {
                request.setAttribute("msg", "Peça: "+pDTO.getNome()+" cadastrada!");
            }
        }
        catch (SQLException se) {
            request.setAttribute("msg", "Erro: " + se.getMessage());
            se.printStackTrace();
        }
        
        return pagina;
    }

    public void setParameter(HttpServletRequest request) {
        pDTO.setNome(request.getParameter("nome"));
        pDTO.setDescricao(request.getParameter("descricao"));
        pDTO.setValorEntrada(Double.parseDouble(request.getParameter("valorEntrada")));
        pDTO.setValorVenda(Double.parseDouble(request.getParameter("valorVenda")));
        pDTO.setQtdEstoque(Integer.parseInt(request.getParameter("qtdEstoque")));
        
        tpDTO.setIdTipoPeca(Integer.valueOf(request.getParameter("tipoPeca")));

        fDTO.setIdFornecedor(Integer.valueOf(request.getParameter("fornecedor")));

        pDTO.setTipoPeca(tpDTO);
        pDTO.setFornecedor(fDTO);

        System.out.println(pDTO);

    }

}
