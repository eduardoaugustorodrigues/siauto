<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.dto.TipoTelefoneDTO"%>
<%@page import="model.dao.TipoTelefoneImpl"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <title>SiAuto</title>
        <%@include file="pages/styleHeader.jsp" %>
        <%@include file="pages/jsHeader.jsp" %>
        <%@include file="pages/jsValidacao.jsp" %>
        <link rel="stylesheet" type="text/css" href="css/styleForms.css" />        
        <link rel="shortcut icon" type="image/ico" href="images/fc_cliente.ico" />
        <script type="text/javascript" src="js/maskCliente.js"></script>
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
                    <a href="#">
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
                        <img src="images/relatorioCliente.png" alt="consultar cliente" />
                        <span>Consultar Cliente</span>
                    </a>
                </li>
                <li>
                    <a href="pesquisarPeca.jsp">
                        <img src="images/relatorioPeca.png" alt="consultar peça" />
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

        <div id="corpo">
            <%@include file="pages/msgPage.jsp" %>
        <%
            if(request.getSession(false).getAttribute("ssfunc") != null) {

            ArrayList<TipoTelefoneDTO> lista = new TipoTelefoneImpl().getListaTipoTelefone();
        %>
            <form id="forms" class="formCliente" action="SiautoController"  method="post">
                <input type="hidden" name="oper" value="cadastrarCliente" />
                <input type="hidden" name="entidade" value="cliente" />
                <fieldset>
                    <label for="nome">Nome:</label>
                    <input type="text" name="nome" id="nome" />
                    <label for="dtNasc">Data de Nascimento:</label>
                    <input type="text" name="dtNasc" id="dtNasc" />
                    <label for="cpf">CPF:</label>
                    <input type="text" name="cpf" id="cpf" />
                    <label for="numTel">Telefone:</label>
                    <input type="text" name="numTel" id="numTel" />
                    <label for="tipoTel">Tipo de Telefone:</label>
                    <select name="tipoTel" id="tipoTel">
                        <option>selecione uma opção</option>
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
                    <!--<input type="text" name="tipoTel" id="tipoTel" />-->
                    <label for="endereco"> Endereço:</label>
                    <input type="text" name="endereco" id="endereco" />
                </fieldset>
                <fieldset>
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
                    <button class="novaposicao" type="submit">Incluir</button>
            </form>
        <%
            }
        %>
        </div>
        
    </body>
</html>
