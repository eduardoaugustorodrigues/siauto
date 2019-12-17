<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="model.dto.*"%>
<%@ page import="model.dao.inter.*"%>
<%@ page import="model.dao.*"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <title>Siauto | Compra | Lista de Itens</title>
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
      //          PecaDAO pDAO = null;
                List<ItemPecaDTO> pecas = null;                
                
                if(request.getSession(false).getAttribute("sscmp") != null){
                    CompraDTO cmpDTO = (CompraDTO) request.getSession(false).getAttribute("sscmp");
                    pecas = cmpDTO.getListaItens();
            %>            
            <div id="listaLogin">
                <form action="#" method="post">
                    <input type="hidden" name="oper" value="configClienteCompra" />
                    <input type="hidden" name="entidade" value="compra" />
                    <input type="hidden" name="idPeca" value="${sscmp.listaItens[cont].peca.idPeca}"/>
                    <table>
                        <thead>
                            <tr>
                                <th class="tbhead" colspan="7">Lista de Peças Selecionadas</th>
                            </tr>
                            <tr>
                                <th>Código</th>
                                <th>Nome da Peça</th>
                                <th>Valor</th>
                                <th>Unidade(s) Disponíveis</th>
                                <th>Tipo de Peça</th>
                                <th>Quantidade</th>
                                <th>Operação</th>
                            </tr>
                            <tr>
                                <th class="tbhead" colspan="7"></th>
                            </tr>
                        </thead>
                        <tfoot>
                            <tr>
                                <th class="tbfoot" colspan="5"></th>
                                <th class="tbfoot">
                                    <a href="compra.jsp">adicionar peça</a>
                                </th>
                                <th class="tbfoot">
                                    <a href="configClienteCompra.jsp">próximo passo</a>
                                </th>
                            </tr>
                        </tfoot>
                        <tbody>                            
                            <%
                                for (ItemPecaDTO ip : pecas) {
                                    pageContext.setAttribute("peca", ip.getPeca());
                            %>
                            <tr>
                                <th>
                                    ${peca.idPeca}
                                </th>
                                <td>                                    
                                    ${peca.nome}
                                </td>
                                <td>
                                    ${peca.stringValorVenda}                                    
                                </td>
                                <td>
                                    ${peca.qtd}                                    
                                </td>
                                <td>
                                    ${peca.tipoPeca.descricao}                                    
                                </td>
                                <td>
                                    <input type="text" name="qtdItem" size="6"/>
                                </td>
                                <td>
                                    <a href="SiautoController?oper=removerItem&entidade=compra&idPeca=${peca.idPeca}">excluir</a>
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
