<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <title>Funcionário | Cadastro</title>
        <%@include file="pages/styleHeader.jsp" %>
        <%@include file="pages/jsHeader.jsp" %>
        <%@include file="pages/jsValidacao.jsp" %>
        <link rel="stylesheet" type="text/css" href="css/styleForms.css" />
        <link rel="shortcut icon" type="image/ico" href="images/fc_funcionario.ico" />
        <script type="text/javascript" src="js/maskFuncionario.js"></script>
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
                    <a href="#">
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
            <%
                if(request.getSession(false).getAttribute("ssfunc") != null) {
                    
            %>

                <form id="forms" class="formFunc" action="SiautoController" method="post">
                    <input type="hidden" name="oper" value="cadastrarFuncionario" />
                    <input type="hidden" name="entidade" value="funcionario" />
                    <fieldset>
                        <legend></legend>
                        <label for="nome">Nome:</label>
                        <input type="text" name="nome" id="nome"/>
                        <label for="cpf">CPF:</label>
                        <input type="text" name="cpf" id="cpf"/>
                        <label for="dtNasc">Data Nascimento:</label>
                        <input type="text" class="datepicker" name="dtNasc" id="dtNasc"/>
                        <label for="salario">Salário:</label>
                        <input type="text" name="salario" id="salario"/>
                        <label for="admissao">Data Admissão:</label>
                        <input type="text" name="admissao" id="admissao"/>                        
                    </fieldset>
                    <fieldset>
                        <label for="usuario">Usuário:</label>
                        <input type="text" name="usuario" id="usuario"/>
                        <label for="senha">Senha:</label>
                        <input type="password" name="senha" id="senha"/>
                    </fieldset>
                    <button type="submit">incluir</button>
                </form>
                <%
                    }
                %>
        </div>
    </body>
</html>
