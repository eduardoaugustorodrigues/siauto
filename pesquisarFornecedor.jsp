<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.dto.FornecedorDTO"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <title>Fornecedor | Consulta</title>
        <%@include file="pages/styleHeader.jsp" %>
        <%@include file="pages/jsHeader.jsp" %>
        <%@include file="pages/jsValidacao.jsp" %>
        <link rel="stylesheet" type="text/css" href="css/stylePesquisar.css" />
        <link rel="shortcut icon" type="image/ico" href="images/fc_fornecedor.ico" />
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
                        <img src="images/cliente.png" alt="login" />
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
                    <a href="pesquisarPeca.jsp">
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
                        <img src="images/fornecedor1.png" alt="relatorio fornecedor" />
                        <span>Consultar Funcionário</span>
                    </a>
                </li>
            </ul>
            <div id="f-psq">
                <form action="SiautoController" method="post">
                    <input type="hidden" name="oper" value="pesquisarFornecedor" />
                    <input type="hidden" name="entidade" value="fornecedor" />
                    <fieldset>
                        <legend></legend>
                        <label for="psqNome">Fornecedor</label>
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
            ArrayList<FornecedorDTO> lista =  (ArrayList<FornecedorDTO>)request.getAttribute("lista");
                if(lista != null){                
        %>        
        <div id="listaLogin">
            <table>
                <thead>
                    <tr>
                        <th class="tbhead" colspan="11">Lista de Fornecedor</th>
                    </tr>
                    <tr> <!-- <tr style="background: #89ABAE;"> -->
                        <th>Razão Social</th>
                        <th>Representante</th>
                        <th>E-mail</th>
                        <th>Numero</th>
                        <th>Telefone</th>
                        <th>Endereço</th>
                        <th>Cidade</th>
                        <th>Bairro</th>
                        <th>UF</th>
                        <th colspan="2">Operações</th>
                    </tr>
                    <tr>
                        <th class="tbhead" colspan="11"></th>
                    </tr>
                </thead>
                <tfoot>
                    <tr>
                        <th class="tbfoot" colspan="11"></th>
                    </tr>
                </tfoot>
                <tbody>
                    <%
                        for(FornecedorDTO f : lista) {
                            pageContext.setAttribute("forn", f);
                            
                    %>
                    <tr>                       
                        <td>${forn.razaoSocial}</td>
                        <td>${forn.representante}</td>
                        <td>${forn.email}</td>
                        <td>${forn.telefone.numero}</td>
                        <td>${forn.telefone.tipoTelefone.descricao}</td>                        
                        <td>${forn.endereco.endereco}</td>
                        <td>${forn.endereco.cidade}</td>
                        <td>${forn.endereco.bairro}</td>
                        <td>${forn.endereco.uf}</td>
                        <td>
                            <a href="SiautoController?oper=getFornecedorId&entidade=fornecedor&id=${forn.idFornecedor}">alterar</a>
                        </td>
                        <td>
                            <a href="SiautoController?oper=excluirFornecedor&entidade=fornecedor&id=${forn.idFornecedor}">excluir</a>
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
