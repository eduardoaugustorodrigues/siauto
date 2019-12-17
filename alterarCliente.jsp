<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.dto.ClienteDTO" %>
<%@page import="model.dto.TipoTelefoneDTO" %>
<%@page import="java.util.ArrayList" %>

<jsp:useBean id="cli" class="model.dto.ClienteDTO" scope="request"/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <title>Cliente | Alteração</title>
        <%@include file="pages/styleHeader.jsp" %>
        <%@include file="pages/jsHeader.jsp" %>
        <%@include file="pages/jsValidacao.jsp" %>        
        <link rel="stylesheet" type="text/css" href="css/styleForms.css" />
        <link rel="shortcut icon" type="image/ico" href="images/fc_cliente.ico" />
        <script type="text/javascript" src="js/maskCliente.js"></script>       
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
                        <img src="images/cliente.png" alt="relatorio login" />
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
                        <img src="images/fornecedor1.png" alt="relatorio fucionário" />
                        <span>Consultar Fornecedor</span>
                    </a>
                </li>
            </ul>
        </div>
        
        <div class="menu">
            <ul>
                <li><a href="#"></a>
                    <ul>
                        <li><a href="#"></a></li>
                    </ul>
            </ul>
        </div>
        <%
            ClienteDTO cliente = (ClienteDTO) request.getAttribute("cliente");
            pageContext.setAttribute("c", cliente);
        %>
        <div id="corpo">
            <p>${msg}</p>
            <form id="forms" action="SiautoController"  method="post">
                <input type="hidden" name="oper" value="alterarCliente" />
                <input type="hidden" name="entidade" value="cliente" />
                <input type="hidden" name="id" value="${c.idCliente}" />
                <fieldset>
                    <label for="nome">Nome:</label>
                    <input type="text" name="nome" id="nome" value="${c.nome}"/>
                    <label for="dtNasc">Data de Nascimento:</label>
                    <input type="text" name="dtNasc" id="dtNasc" value="${c.dtNasc}" />
                    <label for="cpf">CPF:</label>
                    <input type="text" name="cpf" id="cpf" value="${c.cpf}"/>
                    <label for="numTel">Telefone:</label>
                    <input type="text" name="numTel" id="numTel" value="${c.telefone.numero}"/>
                    <label for="tipoTel">Tipo:</label>
                    <select name="tipoTel" id="tipoTel">                        
                     <%
                        ArrayList<TipoTelefoneDTO> listaTT = (ArrayList<TipoTelefoneDTO>) request.getAttribute("tt");

                        if(listaTT != null) {
                            for(TipoTelefoneDTO tt : listaTT){
                            pageContext.setAttribute("tt", tt);                            
                            //out.println("<option></option>");
                     %>
                     <option value="${tt.idTipoTel}" ${tt.descricao == c.telefone.tipoTelefone.descricao? "selected" : ""}>${tt.descricao}</option>
                     <%
                           }
                        }
                     %>                        
                    </select>
                    <label for="endereco"> Endereço:</label>
                    <input type="text" name="endereco" id="endereco" value="${c.endereco.endereco}"/>
                </fieldset>
                <fieldset>
                    <label for="uf">Estado:</label>
                    <input type="text" name="uf" id="uf" value="${c.endereco.uf}"/>
                    <label for="cidade">Cidade:</label>
                    <input type="text" name="cidade" id="cidade" value="${c.endereco.cidade}"/>
                    <label for="bairro">Bairro:</label>
                    <input type="text" name="bairro" id="bairro" value="${c.endereco.bairro}"/>
                    <label for="logradouro"> Logradouro:</label>
                    <input type="text" name="logradouro" id="logradouro" value="${c.endereco.logradouro}"/>
                    <label for="cep">CEP:</label>
                    <input type="text" name="cep" id="cep" value="${c.endereco.cep}"/>                    
                </fieldset>
                <button class="novaposicao" type="submit">alterar</button>
            </form>
        </div>        
    </body>
</html>
