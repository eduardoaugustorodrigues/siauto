<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="model.dto.FornecedorDTO"%>
<%@ page import="model.dto.PecaDTO"%>
<%@ page import="model.dto.TipoPecaDTO"%>
<%@ page import="model.dao.TipoPecaImpl, model.dao.inter.TipoPecaDAO"%>
<%@ page import="model.dao.FornecedorImpl, model.dao.inter.FornecedorDAO"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <title>Peça | Cadastro</title>
        <%@ include file="pages/styleHeader.jsp" %>
        <%@ include file="pages/jsHeader.jsp" %>
        <%@ include file="pages/jsValidacao.jsp" %>
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
                    <a href="#">
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
            <!--
            <ul>
                <li><a href="#">incluir</a></li>
                <li><a href="#">atualizar</a></li>
                <li><a href="pesquisarPeca.jsp">consultar</a></li>
                <li><a href="#">excluir</a></li>
                <li><a href="#" style="width: 2px; padding: 0;" >&nbsp;</a></li>
            </ul>
            -->
        </div>

        <div id="corpo">
            <%@include file="pages/msgPage.jsp" %>
        <%
            ArrayList<TipoPecaDTO> tpPecas = null;
            ArrayList<FornecedorDTO> listFornecedor = null;

            if(request.getSession(false).getAttribute("ssfunc") != null) {                
                tpPecas = new TipoPecaImpl().getAllTiposPeca();               
                listFornecedor = new FornecedorImpl().getFornecedores();
        %>
            <form id="forms" class="formPeca" action="SiautoController" method="post">
                <input type="hidden" name="oper" value="cadastrarPeca" />
                <input type="hidden" name="entidade" value="peca" />                
                <fieldset>
                    <legend></legend>
                    <label for="nome">Nome:</label>
                    <input type="text" name="nome" id="nome"/>
                    <label for="tipoPeca">Tipo de Peça:</label>
                    <select id="tipoPeca" name="tipoPeca" >
                        <option>selecione uma opção</option>
                        <%
                                    for (TipoPecaDTO tp : tpPecas) {
                                        pageContext.setAttribute("tp", tp);
                        %>
                        <option value="${tp.idTipoPeca}">${tp.descricao}</option>
                        <%
                                    }
                        %>
                    </select>
                    <label for="fornecedor">Fornecedor:</label>
                    <select id="fornecedor" name="fornecedor">
                        <option value="0">selecione uma opção</option>
                        <%
                                    for (FornecedorDTO f : listFornecedor) {
                                        pageContext.setAttribute("f", f);
                        %>
                        <option value="${f.idFornecedor}">${f.razaoSocial}</option>
                        <%
                                    }
                        %>
                    </select>
                    <label for="descricao">Descrição:</label>
                    <input type="text" name="descricao" id="descricao"/>
                    <label for="valorEntrada">Valor Entrada:</label>
                    <input type="text" name="valorEntrada" id="valorEntrada"/>
                </fieldset>
                <fieldset>
                    <label for="valorVenda">Valor Venda:</label>
                    <input type="text" name="valorVenda" id="valorVenda"/>
                    <label for="qtdEstoque">Unidade:</label>
                    <input type="text" name="qtdEstoque" id="qtdEstoque"/>                    
                </fieldset>
                <button type="submit">incluir</button>
            </form>
        <%
            }
        %>
        </div>
    </body>
</html>
