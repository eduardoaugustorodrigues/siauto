package model.bo.cmd;

import java.sql.SQLException;
import javax.servlet.http.*;
import model.dao.*;
import model.dao.inter.*;
import model.dto.*;

public class CadastrarCliente implements InterfaceCommand {
    private ClienteDAO cDAO;
    private ClienteDTO cDTO;
    private TelefoneDTO tDTO;
    private TipoTelefoneDTO ttDTO;
    private TelefoneDAO tDAO;
    private EnderecoDTO eDTO;
    private TipoTelefoneDAO ttDAO;
    private EnderecoDAO eDAO;

    public CadastrarCliente() {
        cDTO = new ClienteDTO();
        tDTO = new TelefoneDTO();
        ttDTO = new TipoTelefoneDTO();
        eDTO = new EnderecoDTO();
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String pagina = "cliente.jsp";
        
        setParameter(request);

        cDAO = new ClienteImpl();
        tDAO = new TelefoneImpl();
        eDAO = new EnderecoImpl();
        ttDAO = new TipoTelefoneImpl();

        try {
            ClienteDTO cliente = cDAO.existeCPF(cDTO);
            if(cliente != null) {
                request.setAttribute("msg", "Cliente: <span style='color: #000'>"+cliente.getNome()+" </span>" +
                        "com o CPF: <span style='color: #000'>"+cliente.getCpf()+"</span> já está cadastrado!");
                return pagina;
            }            
            if (cDAO.cadastrar(cDTO)) {
                // peca o tipo de telefone
                ttDTO = ttDAO.getTipoTelefoneId(ttDTO.getIdTipoTel());
                // seta o tipo de telefone no telefone
                cDTO.getTelefone().setTipoTelefone(ttDTO);
                //
                cDTO.setIdCliente(cDAO.getUltimoCliente().getIdCliente());
                eDAO.cadastrar(cDTO);
                tDAO.cadastrar(cDTO);                
            }
            request.setAttribute("msg", "Cliente: "+cDTO.getNome()+" cadastrado!");                
        }
        catch (SQLException se) {
            request.setAttribute("msg", "Erro: " + se.getMessage());
            se.printStackTrace();
        }
        
        return pagina;
    }

    public void setParameter(HttpServletRequest request) {
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

        System.out.println(cDTO);
    }

}
