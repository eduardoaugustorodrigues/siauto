<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.dto.ClienteDTO"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <title>Cliente | Configração Cliente</title>
        <%@include file="pages/styleHeader.jsp" %>
        <%@include file="pages/jsHeader.jsp" %>        
        <link rel="stylesheet" type="text/css" href="css/styleForms.css" />
        <link rel="stylesheet" type="text/css" href="css/stylePesquisar.css" />
        <link rel="shortcut icon" type="image/ico" href="images/fc_cliente.ico" />
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
                <!--
                <li>
                    <a href="login.jsp">
                        <img src="images/login.png" alt="login" />
                        <span>Login</span>
                    </a>
                </li>
                -->
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
                        <span>Pesquisar Login</span>
                    </a>
                </li>
                -->
                <li>
                    <a href="#">
                        <img src="images/relatorioCliente.png" alt="relatorio login" />
                        <span>Pesquisar Cliente</span>
                    </a>
                </li>
                <li>
                    <a href="pesquisarPeca.jsp">
                        <img src="images/relatorioPeca.png" alt="relatorio peça" />
                        <span>Pesquisar Peça</span>
                    </a>
                </li>
                <li>
                    <a href="pesquisarFuncionario.jsp">
                        <img src="images/relatorioAmigo.png" alt="relatorio fucionário" />
                        <span>Pesquisar Funcionário</span>
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
                    <input type="hidden" name="oper" value="pesquisarCliente" />
                    <input type="hidden" name="entidade" value="cliente" />
                    <fieldset>
                        <legend></legend>
                        <label for="psqNome">Cliente</label>
                        <input type="text" name="psqNome" id="psqNome" size="33"/>
                        <button id="btn-psq" type="submit"></button>
                    </fieldset>
                </form>
            </div>
        </div>

        <div class="menu">
            <!--
            <ul>
                <li><a href="cliente.jsp">incluir</a></li>
                <li><a href="#" style="width: 2px; padding: 0;" >&nbsp;</a></li>
            </ul>
            -->
        </div>
        <div id="corpo">
            <%@include file="pages/msgPage.jsp" %>
        <%
            ArrayList<ClienteDTO> lista = (ArrayList) request.getAttribute("lista");
                if(lista != null){                
        %>
        <div id="listaLogin">
            <table>
                <thead>
                    <tr>
                        <th class="tbhead" colspan="10">Lista de Cliente</th>
                    </tr>
                    <tr>
                        <th>Nome</th>
                        <th>CPF</th>
                        <th>Nascimento</th>
                        <th>Numero</th>
                        <th>Telefone</th>
                        <th>Endereço</th>
                        <th>Cidade</th>
                        <th>Bairro</th>
                        <th>UF</th>                        
                        <th colspan=2">Operações</th>
                    </tr>
                    <tr>
                        <th class="tbhead" colspan="10"></th>
                    </tr>
                </thead>
                <tfoot>
                    <tr>
                        <th class="tbfoot" colspan="10"></th>
                    </tr>
                </tfoot>
                <tbody>
                    <%
                        for(ClienteDTO c : lista){
                            pageContext.setAttribute("cliente", c);
                    %>
                    <tr>
                        <td>${cliente.nome}</td>
                        <td>${cliente.cpf}</td>
                        <td>${cliente.dtNasc}</td>
                        <td>${cliente.telefone.numero}</td>
                        <td>${cliente.telefone.tipoTelefone.descricao}</td>
                        <td>${cliente.endereco.endereco}</td>
                        <td>${cliente.endereco.cidade}</td>
                        <td>${cliente.endereco.bairro}</td>
                        <td>${cliente.endereco.uf}</td>                        
                        <td>
                            <a href="SiautoController?oper=configCliente&entidade=compra&id=${cliente.idCliente}">incluir</a>
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
