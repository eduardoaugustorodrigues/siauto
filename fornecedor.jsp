<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.util.ArrayList"%>
<%@page import="model.dto.TipoTelefoneDTO"%>
<%@page import="model.dao.TipoTelefoneImpl"%>

<html>
    <head>
        <title>Fornecedor | Cadastro</title>
        <%@include file="pages/styleHeader.jsp" %>
        <%@include file="pages/jsHeader.jsp" %>
        <%@include file="pages/jsValidacao.jsp" %>
        <!--<link rel="stylesheet" type="text/css" href="css/styleForms.css" /> -->
        <link rel="stylesheet" type="text/css" href="css/styleFornecedor.css" />
        <!-- <link rel="stylesheet" type="text/css" href="css/styleLogin.css" /> -->
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
                    <a href="#">
                        <img src="images/fornecedor.png" alt="forncedor" />
                        <span>Fornecedor</span>
                    </a>
                </li>
                <li>
                    <a href="compra.jsp">
                        <img src="images/compra.png" alt="fornecedor" />
                        <span>Compra</span>
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
                        <span>Pesquisar Cliente</span>
                    </a>
                </li>
                 <li>
                    <a href="pesquisarPeca.jsp">
                        <img src="images/relatorioPeca.png" alt="relatorio peça" />
                        <span>Pesquisar Peça</span>
                    </a>
                </li>
                <li>
                    <a href="pesquisarFuncionario.jsp">
                        <img src="images/relatorioAmigo.png" alt="relatorio Amigo" />
                        <span>Pesquisar Funcionário</span>
                    </a>
                </li>
                <li>
                    <a href="pesquisarFornecedor.jsp">
                        <img src="images/fornecedor1.png" alt="relatorio Fornecedor" />
                        <span>Pesquisar Fornecedor</span>
                    </a>
                </li>
            </ul>
        </div>

        <div class="menu">
            <!--
            <ul>
                <li><a href="#"></a>
                    <ul>
                        <li><a href="#">inserir</a></li>
                    </ul>
                    <li><a href="#">inserir</a></li>
                    <li><a href="#">atualizar</a></li>
                    <li><a href="ControladorFuncionario?oper=pesquisarFuncionario&entidade=funcionario&psqNome=">pesquisar</a></li>
                    <li><a href="#">excluir</a></li>
                    <li><a href="#" style="width: 2px; padding: 0;" >&nbsp;</a></li>
            </ul>
            -->
        </div>
        <div id="corpo">
            <%@include file="pages/msgPage.jsp" %>
                <!--<form id="login" class="formForn" action="SiautoController" method="get">-->
            <%
                ArrayList<TipoTelefoneDTO> lista = null;

                if(request.getSession(false).getAttribute("ssfunc") != null) {
                    
                    lista = new TipoTelefoneImpl().getListaTipoTelefone();
            %>
                <form id="forms" class="formForn" action="SiautoController" method="get">
                    <input type="hidden" name="oper" value="cadastrarFornecedor" />
                    <input type="hidden" name="entidade" value="fornecedor" />
                    <fieldset>
                        <legend></legend>
                        <label for="razaoSocial">Razão Social:</label>
                        <input type="text" name="razaoSocial" id="razaoSocial"/>
                        <label for="cnpj">CNPJ:</label>
                        <input type="text" name="cnpj" id="cnpj"/>
                        <label for="email">E-mail:</label>
                        <input type="text" name="email" id="email"/>
                        <label for="representante">Representante:</label>
                        <input type="text" name="representante" id="representante"/>
                        <label for="telefone">Telefone:</label>
                        <input type="text" name="telefone" id="telefone"/>
                        <label for="tipoTel">Tipo de Telefone:</label>
                        <select name="tipoTel" id="tipoTel">
                            <option>Selecione uma opção</option>
                        <%
                            for(TipoTelefoneDTO tt : lista){
                                pageContext.setAttribute("tt", tt);
                         %>
                            <option value="${tt.idTipoTel}">${tt.descricao}</option>
                         <%
                            //out.println("<option value='"+tt.getIdTipoTel()+"'>"+tt.getDescricao()+"</option>");
                            }
                        %>
                        </select>
                    </fieldset>
                    <fieldset>
                        <label for="endereco"> Endereço:</label>
                        <input type="text" name="endereco" id="endereco" />
                        <label for="uf">UF:</label>
                        <input type="text" name="uf" id="uf" maxlength="2" />
                        <label for="cidade">Cidade:</label>
                        <input type="text" name="cidade" id="cidade" />
                        <label for="bairro">Bairro:</label>
                        <input type="text" name="bairro" id="bairro" />
                        <label for="logradouro"> Logradouro:</label>
                        <input type="text" name="logradouro" id="logradouro" />
                        <label for="cep">CEP:</label>
                        <input type="text" name="cep" id="cep" />                        
                    </fieldset>
                        <button type="submit">incluir</button>
                </form>
                <%
                    }
                %>
        </div>
    </body>
</html>
