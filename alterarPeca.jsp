<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.dto.*"%>
<%@page import="model.dao.*"%>
<%@page import="java.util.ArrayList"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <title>Peça | Atualização</title>        
        <%@include file="pages/styleHeader.jsp" %>
        <%@include file="pages/jsHeader.jsp" %>
        <%@include file="pages/jsValidacao.jsp" %>        
        <link rel="stylesheet" type="text/css" href="css/styleForms.css" />
        <link rel="shortcut icon" type="image/ico" href="images/fc_peca.ico" />
        <script type="text/javascript" src="js/maskPeca.js"></script>
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
            </ul>
        </div>

        <%
            PecaDTO peca = (PecaDTO) request.getAttribute("peca");
            pageContext.setAttribute("peca", peca);

            ArrayList<TipoPecaDTO> tpPecas = new TipoPecaImpl().getAllTiposPeca();

            ArrayList<FornecedorDTO> forns = new FornecedorImpl().getFornecedores();
        %>
        <div id="login">
            <form id="forms" class="formPeca" action="SiautoController" method="post">
                <input type="hidden" name="oper" value="alterarPeca" />
                <input type="hidden" name="entidade" value="peca" />
                <input type="hidden" name="id" value="${peca.idPeca}" />
                <fieldset>
                    <legend></legend>
                    <label for="nome">Nome:</label>
                    <input type="text" name="nome" id="nome" value="${peca.nome}" />
                    <label for="tipoPeca">Tipo de Peça:</label>
                    <select id="tipoPeca" name="tipoPeca" >
                        <option>selecione uma opção</option>
                        <%
                            for (TipoPecaDTO tp : tpPecas) {
                                pageContext.setAttribute("tp", tp);
                        %>
                        <option value="${tp.idTipoPeca}" ${tp.descricao == peca.tipoPeca.descricao? "selected":""}>${tp.descricao}</option>
                        <%
                                    }
                        %>
                    </select>
                    <label for="fornecedor">Fornecedor:</label>
                    <select id="fornecedor" name="fornecedor">
                        <option>selecione uma opção</option>
                        <%
                            for (FornecedorDTO f : forns) {
                                pageContext.setAttribute("f", f);
                        %>
                        <option value="${f.idFornecedor}" ${peca.fornecedor.razaoSocial == f.razaoSocial? "selected":""}>${f.razaoSocial}</option>
                        <%
                                    }
                        %>
                    </select>
                    <label for="descricao">Descrição:</label>
                    <input type="text" name="descricao" id="descricao" value="${peca.descricao}"/>
                    <label for="valorEntrada">Valor Entrada:</label>
                    <input type="text" name="valorEntrada" id="valorEntrada" value="${peca.stringValorEntrada}"/>
                </fieldset>
                <fieldset>
                    <label for="valorVenda">Valor Venda:</label>
                    <input type="text" name="valorVenda" id="valorVenda" value="${peca.stringValorVenda}"/>
                    <label for="qtdEstoque">Unidade:</label>
                    <input type="text" name="qtdEstoque" id="qtdEstoque" value="${peca.qtd}"/>
                </fieldset>
                <button type="submit">alterar</button>
            </form>
        </div>
    </body>
</html>
