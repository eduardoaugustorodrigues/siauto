package model.bo.cmd;

import java.sql.SQLException;
import javax.servlet.http.*;
import model.dao.*;
import model.dao.inter.*;
import model.dto.*;

public class AlterarCliente implements InterfaceCommand {
    private ClienteDAO cDAO;
    private ClienteDTO cDTO;
    private TelefoneDTO tDTO;
    private TipoTelefoneDTO ttDTO;
    private TelefoneDAO tDAO;
    private EnderecoDTO eDTO;
    private TipoTelefoneDAO ttDAO;
    private EnderecoDAO eDAO;

    public AlterarCliente() {
        cDTO = new ClienteDTO();
        tDTO = new TelefoneDTO();
        ttDTO = new TipoTelefoneDTO();
        eDTO = new EnderecoDTO();
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String pagina = "alterarCliente.jsp";
        String cmd = request.getParameter("oper");

        setParameter(request);

        try {
            
            cDAO = new ClienteImpl();
            tDAO = new TelefoneImpl();
            ttDAO = new TipoTelefoneImpl();
            eDAO = new EnderecoImpl();

            if(cmd.equals("getClienteId")){
                ClienteDTO cliente = cDAO.getClienteId(cDTO.getIdCliente());
                request.setAttribute("cliente", cliente);
                request.setAttribute("tt", ttDAO.getListaTipoTelefone());
            }
            else {
                setAllParameter(request);

                cDAO.alterar(cDTO);
                eDAO.alterar(cDTO);
                tDAO.alterar(cDTO);
                request.setAttribute("msg", "Cliente "+cDTO.getNome()+" atualizado!");
                pagina = "SiautoController?oper=pesquisarCliente&entidade=cliente&psqNome="+cDTO.getNome();
            }

        }
        catch (SQLException se) {
            request.setAttribute("msg", "Erro: " + se.getMessage());
            se.printStackTrace();
        }

        return pagina;
    }

    public void setParameter(HttpServletRequest request) {
        cDTO.setIdCliente(Integer.parseInt(request.getParameter("id")));    
    }

    public void setAllParameter(HttpServletRequest request) {
        setParameter(request);
        
        cDTO.setNome(request.getParameter("nome"));
        cDTO.setDtNasc(request.getParameter("dtNasc"));
        cDTO.setCpf(request.getParameter("cpf"));

        eDTO.setBairro(request.getParameter("bairro"));
        eDTO.setCep(request.getParameter("cep"));
        eDTO.setCidade(request.getParameter("cidade"));
        eDTO.setEndereco(request.getParameter("endereco"));
        eDTO.setLogradouro(request.getParameter("logradouro"));
        eDTO.setUf(request.getParameter("uf"));

        ttDTO.setIdTipoTel(Integer.valueOf(request.getParameter("tipoTel")));

        tDTO.setNumero(request.getParameter("numTel"));
        tDTO.setTipoTelefone(ttDTO);

        cDTO.setTelefone(tDTO);
        cDTO.setEndereco(eDTO);
        
    }

}
