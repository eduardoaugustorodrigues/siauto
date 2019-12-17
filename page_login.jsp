<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="model.dto.FuncionarioDTO" %>
<html>
    <head>
        <title>Login | Cadastro</title>
        <%@include file="pages/styleHeader.jsp" %>
        <%@include file="pages/jsHeader.jsp" %>
        <%@include file="pages/jsValidacao.jsp" %>
        <link rel="stylesheet" type="text/css" href="css/styleLogin.css" />
        <link rel="shortcut icon" type="image/ico" href="images/fc_login.ico" />
    </head>
    <body>
        <div id="nav">
            <ul id="menu-fish">
                <li>
                    <a href="index.jsp">
                        <img src="images/home.png" alt="home" />
                        <span>Home</span>
                    </a>
                </li>
                <li>
                    <a href="#">
                        <img src="images/login.png" alt="login" />
                        <span>Login</span>
                    </a>
                </li>
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
                <li>
                    <a href="pesquisarLogin.jsp">
                        <img src="images/login1.png" alt="consultar login" />
                        <span>Consultar Login</span>
                    </a>
                </li>
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
            </ul>
        </div>

        <div id="corpo">
            <%@include file="pages/msgPage.jsp" %>
            <p>
                <%
                    
                    if(request.getSession(false).getAttribute("ssfunc") != null){
                         out.println("SessionID: "+request.getSession().getId());
                    }
                    else{
                         out.println("nulo");
                         }
                %>
            </p>
            <form id="login" class="formLogin" action="SiautoController" method="post">
                <input type="hidden" name="oper" value="logarFuncionario" />
                <input type="hidden" name="entidade" value="login" />
                    <fieldset>
                        <legend></legend>
                        <label for="usuario">Usuário:</label>
                        <input type="text" name="usuario" id="usuario"/>
                        <label for="senha">Senha:</label>
                        <input type="password" name="senha" id="senha"/>
                        <button type="submit">login</button>
                        <label><a href="#">esqueci a senha</a></label>
                    </fieldset>
            </form>
        </div>
    </body>
</html>