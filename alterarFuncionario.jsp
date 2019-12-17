<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.dto.FuncionarioDTO"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <title>Funcionário | Atualização</title>
        <%@include file="pages/styleHeader.jsp" %>
        <%@include file="pages/jsHeader.jsp" %>
        <%@include file="pages/jsValidacao.jsp" %>
        <link rel="stylesheet" type="text/css" href="css/styleForms.css" />
        <link rel="shortcut icon" type="image/ico" href="images/fc_funcionario.ico" />
        <script type="text/javascript" src="js/maskFuncionario.js"></script>
        <link rel="stylesheet" type="text/css" href="css/styleForms.css" />
        <link rel="shortcut icon" type="image/ico" href="images/fc_funcionario.ico" />
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
        <%
                    FuncionarioDTO funcDTO = (FuncionarioDTO) request.getAttribute("func");
                    pageContext.setAttribute("func", funcDTO);
        %>        
        <div id="corpo">
            <form id="forms" class="formFunc" action="SiautoController" method="post">
                <input type="hidden" name="oper" value="alterarFuncionario" />
                <input type="hidden" name="entidade" value="funcionario" />
                <input type="hidden" name="id" value="${func.idFunc}" />
                <fieldset>
                    <legend></legend>
                    <label for="nome">Nome:</label>
                    <input type="text" name="nome" id="nome" value="${func.nome}" />
                    <label for="cpf">CPF:</label>
                    <input type="text" name="cpf" id="cpf" value="${func.cpf}" />
                    <label for="dtNasc">Data Nascimento:</label>
                    <input type="text" name="dtNasc" id="dtNasc" value="${func.dtNasc}" />
                    <label for="salario">Salário:</label>
                    <input type="text" name="salario" id="salario" value="${func.strSalario}" />
                    <label for="admissao">Data Admissão:</label>
                    <input type="text" name="admissao" id="admissao" value="${func.dtAdmissao}" />
                </fieldset>
                <fieldset>
                    <label for="demissao">Data de Demissão:</label>
                    <input type="text" name="demissao" id="demissao" value="${func.dtDemissao}" />
                    <label for="usuario">Usuário:</label>
                    <input type="text" name="usuario" id="usuario" value="${func.usuario}" />
                    <label for="senha">Senha:</label>
                    <input type="text" name="senha" id="senha" value="${func.senha}" />
                </fieldset>
                <button type="submit">alterar</button>
            </form>
        </div>      
    </body>
</html>
