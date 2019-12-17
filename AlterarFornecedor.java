package model.bo.cmd;

import java.sql.SQLException;
import javax.servlet.http.*;
import model.dao.*;
import model.dao.inter.*;
import model.dto.*;

public class AlterarFornecedor implements InterfaceCommand {
    private FornecedorDAO fDAO;
    private FornecedorDTO fDTO;
    private TelefoneDTO tDTO;
    private TipoTelefoneDTO ttDTO;
    private TelefoneDAO tDAO;
    private EnderecoDTO eDTO;
    private TipoTelefoneDAO ttDAO;
    private EnderecoDAO eDAO;

    public AlterarFornecedor() {
        fDTO = new FornecedorDTO();
        tDTO = new TelefoneDTO();
        ttDTO = new TipoTelefoneDTO();
        eDTO = new EnderecoDTO();
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String pagina = "alterarFornecedor.jsp";
        String cmd = request.getParameter("oper");

        setParameter(request);

        try {
            
            fDAO = new FornecedorImpl();
            tDAO = new TelefoneImpl();
            ttDAO = new TipoTelefoneImpl();
            eDAO = new EnderecoImpl();

            if(cmd.equals("getFornecedorId")){
                FornecedorDTO forn = fDAO.getForncedorId(fDTO.getIdFornecedor());
                request.setAttribute("forn", forn);
                request.setAttribute("tt", ttDAO.getListaTipoTelefone());
            }
            else {
                setAllParameter(request);

                fDAO.alterar(fDTO);
                eDAO.alterar(fDTO);
                tDAO.alterar(fDTO);
                request.setAttribute("msg", "Fornecedor "+fDTO.getRazaoSocial()+" atualizado!");
                pagina = "SiautoController?oper=pesquisarFornecedor&psqNome="+fDTO.getRazaoSocial();
            }

        }
        catch (SQLException se) {
            request.setAttribute("msg", "Erro: " + se.getMessage());
            se.printStackTrace();
        }

        return pagina;
    }

    public void setParameter(HttpServletRequest request) {
        fDTO.setIdFornecedor(Integer.parseInt(request.getParameter("id")));
    }

    public void setAllParameter(HttpServletRequest request) {
        setParameter(request);
        
        fDTO.setRazaoSocial(request.getParameter("razaoSocial"));
        fDTO.setRepresentante(request.getParameter("representante"));
        fDTO.setEmail(request.getParameter("email"));
        fDTO.setCnpj(request.getParameter("cnpj"));

        eDTO.setBairro(request.getParameter("bairro"));
        eDTO.setCep(request.getParameter("cep"));
        eDTO.setCidade(request.getParameter("cidade"));
        eDTO.setEndereco(request.getParameter("endereco"));
        eDTO.setLogradouro(request.getParameter("logradouro"));
        eDTO.setUf(request.getParameter("uf"));

        ttDTO.setIdTipoTel(Integer.valueOf(request.getParameter("tipoTel")));

        System.out.println(ttDTO.getIdTipoTel());

        tDTO.setNumero(request.getParameter("telefone"));
        tDTO.setTipoTelefone(ttDTO);

        fDTO.setTelefone(tDTO);
        fDTO.setEndereco(eDTO);
        
    }

}
