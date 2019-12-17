<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.dto.FornecedorDTO" %>
<%@page import="model.dto.TipoTelefoneDTO" %>
<%@page import="java.util.ArrayList" %>

<jsp:useBean id="forn" class="model.dto.FornecedorDTO" scope="request"/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <title>Fornecedor | Alteração</title>
        <%@include file="pages/styleHeader.jsp" %>
        <%@include file="pages/jsHeader.jsp" %>
        <%@include file="pages/jsValidacao.jsp" %>        
        <link rel="stylesheet" type="text/css" href="css/styleFornecedor.css" />
        <link rel="shortcut icon" type="image/ico" href="images/fc_fornecedor.ico" />
        <script type="text/javascript" src="js/maskFornecedor.js"></script>
    </head>
    <body>
        <div id="nav">
            <jsp:include page="pages/logoff.jsp" />
            <ul id="menu-fish">
                <!--
                <li>
                    <a href="index.jsp">
                        <img src="images/home.png" alt="home" />
                        <span>Home</span>
                    </a>
                </li>
                -->
                <!--
                <li>
                    <a href="login.jsp">
                        <img src="images/login.png" alt="login" />
                        <span>Login</span>
                    </a>
                </li>
                -->
                <li>
                    <a href="cliente.jsp">
                        <img src="images/cliente.png" alt="cliente" />
                        <span>Cliente</span>
                    </a>
                </li>
                <li>
                    <a href="peca.jsp">
                        <img src="images/peca.png" alt="peça" />
                        <span>Peça</span>
                    </a>
                </li>
                <li>
                    <a href="funcionario.jsp">
                        <img src="images/funcionario.png" alt="funcionario" />
                        <span>Funcionário</span>
                    </a>
                </li>
                <li>
                    <a href="fornecedor.jsp">
                        <img src="images/fornecedor.png" alt="fornecedor" />
                        <span>Fornecedor</span>
                    </a>
                </li>
                <!--
                <li>
                    <a href="pesquisarLogin.jsp">
                        <img src="images/login1.png" alt="consultar login" />
                        <span>Consultar Login</span>
                    </a>
                </li>
                -->
                <li>
                    <a href="pesquisarCliente.jsp">
                        <img src="images/relatorioCliente.png" alt="relatorio login" />
                        <span>Consultar Cliente</span>
                    </a>
                </li>
                <li>
                    <a href="pesquisarPeca.jsp">
                        <img src="images/relatorioPeca.png" alt="relatorio peça" />
                        <span>Consultar Peça</span>
                    </a>
                </li>
                <li>
                    <a href="pesquisarFuncionario.jsp">
                        <img src="images/relatorioAmigo.png" alt="relatorio fucionário" />
                        <span>Consultar Funcionário</span>
                    </a>
                </li>
                <li>
                    <a href="pesquisarFornecedor.jsp">
                        <img src="images/fornecedor1.png" alt="relatorio fucionário" />
                        <span>Consultar Fornecedor</span>
                    </a>
                </li>
            </ul>
        </div>
        
        <div class="menu">
            <ul>                
                <li><a href="#"></a>
                    <ul>
                        <li><a href="#"></a></li>
                    </ul>
            </ul>
        </div>
        <%
            FornecedorDTO fDTO = (FornecedorDTO) request.getAttribute("forn");
            pageContext.setAttribute("f", fDTO);
        %>
        <div id="corpo">
            <p>${msg}</p>
            <form id="forms" action="SiautoController"  method="post">
                <input type="hidden" name="oper" value="alterarFornecedor" />
                <input type="hidden" name="entidade" value="fornecedor" />
                <input type="hidden" name="id" value="${f.idFornecedor}" />
                <fieldset>
                    <label for="razaoSocial">Razão Social:</label>
                    <input type="text" name="razaoSocial" id="razaoSocial" value="${f.razaoSocial}"/>
                    <label for="cnpj">CNPJ:</label>
                    <input type="text" name="cnpj" id="cnpj" value="${f.cnpj}" />
                    <label for="email">E-mail:</label>
                    <input type="text" name="email" id="email" value="${f.email}" />
                    <label for="representante">Representante:</label>
                    <input type="text" name="representante" id="representante" value="${f.representante}" />
                    <label for="telefone">Telefone:</label>
                    <input type="text" name="telefone" id="telefone" value="${f.telefone.numero}" />
                    <label for="tipoTel">Tipo:</label>
                    <select name="tipoTel" id="tipoTel">                        
                     <%
                        ArrayList<TipoTelefoneDTO> listaTT = (ArrayList<TipoTelefoneDTO>) request.getAttribute("tt");

                        if(listaTT != null) {
                            for(TipoTelefoneDTO tt : listaTT){
                            pageContext.setAttribute("tt", tt);                            
                            //out.println("<option></option>");
                     %>
                     <option value="${tt.idTipoTel}" ${tt.descricao == forn.telefone.tipoTelefone.descricao? "selected='selected'" : ""}>${tt.descricao}</option>
                     <%
                           }
                        }
                     %>                        
                    </select>
                </fieldset>
                <fieldset>
                    <label for="endereco">Endereço:</label>
                    <input type="text" name="endereco" id="endereco" value="${f.endereco.endereco}"/>
                    <label for="uf">Estado:</label>
                    <input type="text" name="uf" id="uf" value="${f.endereco.uf}"/>
                    <label for="cidade">Cidade:</label>
                    <input type="text" name="cidade" id="cidade" value="${f.endereco.cidade}"/>
                    <label for="bairro">Bairro:</label>
                    <input type="text" name="bairro" id="bairro" value="${f.endereco.bairro}"/>
                    <label for="logradouro"> Logradouro:</label>
                    <input type="text" name="logradouro" id="logradouro" value="${f.endereco.logradouro}"/>
                    <label for="cep">CEP:</label>
                    <input type="text" name="cep" id="cep" value="${f.endereco.cep}"/>
                </fieldset>
                <button type="submit">alterar</button>
            </form>
        </div>        
    </body>
</html>
