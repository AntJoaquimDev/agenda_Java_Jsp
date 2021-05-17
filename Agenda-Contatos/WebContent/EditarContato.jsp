<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="utf-8">
<title>Agenda de Contatos JSP</title>

<link rel="icon" href="img/fone.png">
<link rel="stylesheet" href="style.css">
</head>
<body>
	<h1>Editar Contato</h1>

	<form name="frmContato" action="update">
		<table>
			<tr>
				<td><input type="text" name="id" id="caixa3" readonly="readonly" value="<%out.print(request.getAttribute("id")); %>"/></td>
			<tr>
			<tr>
				<td><input type="text" name="nome" class="caixa1"  value="<%out.print(request.getAttribute("nome")); %>"/></td>
			<tr>
				<td><input type="text" name="fone" class="caixa2"  value="<%out.print(request.getAttribute("fone")); %>"/></td>
			</tr>
			<tr>
				<td><input type="text" name="email" class="caixa1"  value="<%out.print(request.getAttribute("email")); %>"/></td>
			</tr>

		</table>
		<input type="button" value="Salvar" class="Botao1" onclick="validar()">
		

	</form>
	<script src="scripts/validar.js">
		
	</script>
</body>
</html>