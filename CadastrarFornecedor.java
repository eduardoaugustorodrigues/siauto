package model.bo.cmd;

import java.sql.SQLException;
import javax.servlet.http.*;
import model.dao.*;
import model.dao.inter.*;
import model.dto.*;

public class CadastrarFornecedor implements InterfaceCommand {
    private FornecedorDAO fDAO;
    private FornecedorDTO fDTO;
    private TelefoneDTO tDTO;
    private TipoTelefoneDTO ttDTO;
    private TelefoneDAO tDAO;
    private EnderecoDTO eDTO;
    private TipoTelefoneDAO ttDAO;
    private EnderecoDAO eDAO;

    public CadastrarFornecedor() {
        fDTO = new FornecedorDTO();
        tDTO = new TelefoneDTO();
        ttDTO = new TipoTelefoneDTO();
        eDTO = new EnderecoDTO();
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String pagina = "fornecedor.jsp";

        fDAO = new FornecedorImpl();
        tDAO = new TelefoneImpl();
        eDAO = new EnderecoImpl();
        ttDAO = new TipoTelefoneImpl();
        
        setParameter(request);

        FornecedorDTO f = null;

        try {
            f = fDAO.existeCNPJ(fDTO);

            if(f != null) {
                request.setAttribute("msg", "Fornecedor: <span style='color: #000'>"+f.getRazaoSocial()+" </span>" +
                        "com o CNPJ: <span style='color: #000'>"+f.getCnpj()+"</span> já está cadastrado!");
                return pagina;
            }

            if (fDAO.cadastrar(fDTO)) {
                // peca o tipo de telefone
                ttDTO = ttDAO.getTipoTelefoneId(ttDTO.getIdTipoTel());
                // seta o tipo de telefone no telefone
                fDTO.getTelefone().setTipoTelefone(ttDTO);
                //
                fDTO.setIdFornecedor(fDAO.getUltimoForncedor().getIdFornecedor());
                eDAO.cadastrar(fDTO);
                tDAO.cadastrar(fDTO);
            }
            request.setAttribute("msg", "Fornecedor: "+fDTO.getRazaoSocial()+" cadastrado!");
        }
        catch (SQLException se) {
            request.setAttribute("msg", "Erro: " + se.getMessage());
            se.printStackTrace();
        }
        
        return pagina;
    }

    public void setParameter(HttpServletRequest request) {
        fDTO.setNomeFantasia(request.getParameter("nomeFantasia"));
        fDTO.setRazaoSocial(request.getParameter("razaoSocial"));
        fDTO.setRepresentante(request.getParameter("representante"));
        fDTO.setCnpj(request.getParameter("cnpj"));
        fDTO.setEmail(request.getParameter("email"));

        eDTO.setEndereco(request.getParameter("endereco"));
        eDTO.setLogradouro(request.getParameter("logradouro"));
        eDTO.setUf(request.getParameter("uf"));
        eDTO.setCep(request.getParameter("cep"));
        eDTO.setCidade(request.getParameter("cidade"));
        eDTO.setBairro(request.getParameter("bairro"));
        
        ttDTO.setIdTipoTel(Integer.valueOf(request.getParameter("tipoTel")));

        tDTO.setNumero(request.getParameter("telefone"));
        tDTO.setTipoTelefone(ttDTO);
        
        fDTO.setTelefone(tDTO);
        fDTO.setEndereco(eDTO);

        System.out.println(fDTO);
    }

}
