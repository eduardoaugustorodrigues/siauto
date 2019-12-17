<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.dto.PecaDTO"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <title>Peça | Consulta</title>
    <%@include file="pages/styleHeader.jsp" %>
    <%@include file="pages/jsHeader.jsp" %>
    <%@include file="pages/jsValidacao.jsp" %>
    <link rel="stylesheet" type="text/css" href="css/stylePesquisar.css" />
    <link rel="shortcut icon" type="image/ico" href="images/fc_peca.ico" />
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
                <li>
                    <a href="compra.jsp">
                        <img src="images/compra.png" alt="fornecedor" />
                        <span>Compra</span>
                    </a>
                </li>
            <!--
            <li>
                <a href="pesquisarLogin.jsp">
                    <img src="images/login1.png" alt="relatorio login" />
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
                <a href="#">
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
        <div id="f-psq">
            <form action="SiautoController" method="post">
                <input type="hidden" name="oper" value="pesquisarPeca" />
                <input type="hidden" name="entidade" value="peca" />
                <fieldset>
                    <legend></legend>
                    <label for="psqNome">Peça</label>
                    <input type="text" name="psqNome" id="psqNome" size="33"/>
                    <button id="btn-psq" type="submit"></button>
                </fieldset>
            </form>
        </div>
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
                    <li><a href="#">pesquisar</a></li>
                    <li><a href="#">excluir</a></li>
                    <li><a href="#" style="width: 2px; padding: 0;" >&nbsp;</a></li>
            </ul>
-->
        </div>

        <div id="corpo">
            <%@include file="pages/msgPage.jsp" %>
        <%
            ArrayList<PecaDTO> lista = (ArrayList<PecaDTO>) request.getAttribute("lista");
                if(lista != null){                
        %>
        <div id="listaLogin">
            <table>
                <thead>
                    <tr>
                        <th class="tbhead" colspan="9">Relatório de Peça</th>
                    </tr>
                    <tr>
                        <th>Nome</th>
                        <th>Descrição</th>
                        <th>Valor Entrada</th>
                        <th>Valor Venda</th>
                        <th>Unidade(s)</th>
                        <th>Tipo de Peça</th>
                        <th>Fornecedor</th>
                        <th colspan="2">Operações</th>
                    </tr>
                    <tr>
                        <th class="tbhead" colspan="9"></th>
                    </tr>
                </thead>
                <tfoot>
                    <tr>
                        <th  class="tbfoot" colspan="9"></th>
                    </tr>
                </tfoot>
                <tbody>
                    <%                        
                        for(PecaDTO peca : lista){
                            pageContext.setAttribute("peca", peca);
                    %>
                    <tr>
                        <td>${peca.nome}</td>
                        <td>${peca.descricao}</td>
                        <td>${peca.stringValorEntrada}</td>
                        <td>${peca.stringValorVenda}</td>
                        <td>${peca.qtd}</td>
                        <td>${peca.tipoPeca.descricao}</td>
                        <td>${peca.fornecedor.razaoSocial}</td>
                        <td>
                            <a href="SiautoController?entidade=peca&oper=getPecaId&id=${peca.idPeca}">alterar</a>
                        </td>
                        <td>
                            <a href="SiautoController?entidade=peca&oper=excluirPeca&id=${peca.idPeca}">excluir</a>
                        </td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
        </div>
        <%
            }
        %>
        </div>
    </body>
</html>
