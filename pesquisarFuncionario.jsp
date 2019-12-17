<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.dto.FuncionarioDTO"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <title>Funcionário | Consulta</title>
        <%@include file="pages/styleHeader.jsp" %>
        <%@include file="pages/jsHeader.jsp" %>
        <%@include file="pages/jsValidacao.jsp" %>
        <link rel="stylesheet" type="text/css" href="css/stylePesquisar.css" />
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
                    <a href="#">
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
                    <input type="hidden" name="oper" value="pesquisarFuncionario" />
                    <input type="hidden" name="entidade" value="funcionario" />
                    <fieldset>
                        <legend></legend>
                        <label for="psqNome">Funcionário</label>
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
            ArrayList<FuncionarioDTO> lista =  (ArrayList<FuncionarioDTO>)request.getAttribute("lista");
                if(lista != null){                
        %>        
        <div id="listaLogin">
            <table>
                <thead>
                    <tr>
                        <th class="tbhead" colspan="10">Lista de Funcionário</th>
                    </tr>
                    <tr>
                        <th>Nome</th>
                        <th>CPF</th>
                        <th>Salário</th>
                        <th>Data de Nascimento</th>
                        <th>Data de Admissão</th>
                        <th>Data de Demissão</th>
                        <th>Usuário</th>
                        <th>Senha</th>
                        <th colspan="2">Operações</th>
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
                        for(FuncionarioDTO f : lista) {
                            pageContext.setAttribute("func", f);
                            
                    %>
                    <tr>                       
                        <td>${func.nome}</td>
                        <td>${func.cpf}</td>
                        <td>${func.strSalario}</td>
                        <td>${func.dtNasc}</td>
                        <td>${func.dtAdmissao}</td>
                        <td>${func.dtDemissao}</td>
                        <td>${func.usuario}</td>
                        <td>${func.senha}</td>
                        <td>
                            <a href="SiautoController?oper=getFuncId&entidade=funcionario&id=${func.idFunc}">alterar</a>
                        </td>
                        <td>
                            <a href="SiautoController?oper=excluirFuncionario&entidade=funcionario&id=${func.idFunc}">excluir</a>
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
