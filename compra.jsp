<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="model.dto.PecaDTO"%>
<%@ page import="model.dto.ItemPecaDTO"%>
<%@ page import="model.dao.inter.PecaDAO"%>
<%@ page import="model.dao.*"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <title>Siauto | Compra</title>
        <%@ include file="pages/styleHeader.jsp" %>
        <%@ include file="pages/jsHeader.jsp" %>
        <%@ include file="pages/jsValidacao.jsp" %>        
        <link rel="stylesheet" type="text/css" href="css/stylePesquisar.css" />
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
                <li>
                    <a href="#">
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
                PecaDAO pDAO = null;
                ArrayList<PecaDTO> pecas = null;
                
                if(request.getSession(false).getAttribute("ssfunc") != null){
                    pDAO = new PecaImpl();
                    pecas = pDAO.getListaPecas();
            %>            
            <div id="listaLogin">
                <form action="SiautoController" method="get">
                    <input type="hidden" name="oper" value="adicionarItem" />
                    <input type="hidden" name="entidade" value="compra" />
                    <table>
                        <thead>
                            <tr>
                                <th class="tbhead" colspan="6">Listas de Peça Disponíveis</th>
                            </tr>
                            <tr>
                                <th colspan="2">Nome</th>
                                <th>Valor</th>
                                <th>Unidade(s)</th>
                                <th>Tipo de Peça</th>                                
                            </tr>
                            <tr>
                                <th class="tbhead" colspan="6"></th>
                            </tr>
                        </thead>
                        <tfoot>
                            <tr>
                                <th class="tbfoot" colspan="5"></th>
                                <th class="tbfoot">
                                    <button type="listaItensCompra.jsp">Peças incluidas</button>
                                </th>
                            </tr>
                        </tfoot>
                        <tbody>
                            <%
                                for (PecaDTO peca : pecas) {
                                    pageContext.setAttribute("peca", peca);
                            %>
                            <tr>
                                <th>
                                    <input type="hidden" name="idPeca" value="${peca.idPeca}"/>
                                </th>
                                <td>
                                    ${peca.nome}
                                    <input type="hidden" name="nome" value="${peca.nome}"/>
                                </td>
                                <td>
                                    ${peca.stringValorVenda}
                                    <input type="hidden" name="valorVenda" value="${peca.stringValorVenda}"/>
                                </td>
                                <td>
                                    ${peca.qtd}
                                    <input type="hidden" name="qtd" value="${peca.qtd}"/>
                                </td>
                                <td>
                                    ${peca.tipoPeca.descricao}
                                    <input type="hidden" name="descTipoPeca" value="${peca.tipoPeca.descricao}"/>
                                </td>
                                <td>
                                    <a href="SiautoController?oper=adicionarItem&entidade=compra&idPeca=${peca.idPeca}">incluir</a>
                                </td>
                            </tr>
                            <%
                                }
                            %>
                        </tbody>
                    </table>
                </form>
            </div>
            <%
                }
            %>
        </div>
    </body>
</html>
