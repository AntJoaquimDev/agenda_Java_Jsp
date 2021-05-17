<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>
<%@ page import="model.ContatoBeans"%>
<%@ page import="java.util.ArrayList"%>
<%
	ArrayList<ContatoBeans> lista = (ArrayList<ContatoBeans>) request.getAttribute("contatos");
/*
for (int i = 0; i < lista.size(); i++) {
	out.println(lista.get(i).getNome());
}*/
%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="utf-8">
<title>Agenda de Contatos JSP</title>

<link rel="icon" href="img/fone.png">
<link rel="stylesheet" href="style.css">

</head>
<body>

	<h1>Agenda de Contatos</h1>
	<a href="NovoContato.html" class="Botao1">Novo contato</a>
	<table id="tabela">
		<thead>
			<tr>
				<th>Id</th>
				<th>Nome</th>
				<th>Fone</th>
				<th>E-mail</th>
				<th>Opções</th>
			</tr>
		</thead>
		<tbody>
			<%
				for (int i = 0; i < lista.size(); i++) {
			%>
			<tr>
				<td><%=lista.get(i).getId()%></td>
				<td><%=lista.get(i).getNome()%></td>
				<td><%=lista.get(i).getFone()%></td>
				<td><%=lista.get(i).getEmail()%></td>

				<td>
				<a href="select?id=<%=lista.get(i).getId()%>" class="Botao1">Editar</a>
				
				
				<a href="javascript: confirmar(<%=lista.get(i).getId() %>)" class="Botao2">Deletar</a>
				</td>
			</tr>

			<% } %>

		</tbody>

	</table>

<script src="scripts/confirmarExclusao.js"></script>

</body>
</html>