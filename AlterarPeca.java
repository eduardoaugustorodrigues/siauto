package model.bo.cmd;

import java.sql.SQLException;
import javax.servlet.http.*;
import model.dao.*;
import model.dao.inter.*;
import model.dto.*;

public class AlterarPeca implements InterfaceCommand {
    private PecaDAO pDAO;
    private PecaDTO pDTO;
    private FornecedorDTO fDTO;
    private TipoPecaDTO tpDTO;
    private FornecedorDAO fDAO;
    private TipoPecaDAO tpDAO;

    public AlterarPeca() {
        pDTO = new PecaDTO();
        tpDTO = new TipoPecaDTO();
        fDTO = new FornecedorDTO();
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String pagina = "alterarPeca.jsp";
        String cmd = request.getParameter("oper");
        
        pDAO = new PecaImpl();

        setParameter(request);

        try {

            if(cmd.equals("getPecaId")){                                                                 
                request.setAttribute("peca", pDAO.getPecaId(pDTO.getIdPeca()));
            }
            else{
                setAllParameter(request);
                
                if(pDAO.alterar(pDTO)) {
                    request.setAttribute("msg", "Peça "+pDTO.getNome()+" alterada!");
                    pagina = "SiautoController?entidade=peca&oper=pesquisarPeca&psqNome="+pDTO.getNome();
                }
                else{
                    request.setAttribute("msg", "Erro ao tentar alterar os dados!");
                }
            }
        }
        catch (SQLException se) {
            request.setAttribute("msg", "Erro: " + se.getMessage());
            se.printStackTrace();
        }

        return pagina;
    }

    public void setParameter(HttpServletRequest request) {
        pDTO.setIdPeca(Integer.valueOf(request.getParameter("id")));        
    }

    public void setAllParameter(HttpServletRequest request) {
        setParameter(request);
        
        pDTO.setNome(request.getParameter("nome"));
        pDTO.setDescricao(request.getParameter("descricao"));
        pDTO.setValorEntrada(Double.valueOf(request.getParameter("valorEntrada")));
        pDTO.setValorVenda(Double.valueOf(request.getParameter("valorVenda")));
        pDTO.setQtdEstoque(Integer.valueOf(request.getParameter("qtdEstoque")));

        tpDTO.setIdTipoPeca(Integer.valueOf(request.getParameter("tipoPeca")));

        fDTO.setIdFornecedor(Integer.valueOf(request.getParameter("fornecedor")));

        pDTO.setTipoPeca(tpDTO);
        pDTO.setFornecedor(fDTO);
    }

}
