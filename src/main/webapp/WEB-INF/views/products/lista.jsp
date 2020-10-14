<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

</head>
<body>

	<h1>Tabela de Produtos</h1>

	<p>${sucesso}</p>

	<table>
		<tr>
			<td style="padding-right: 10rem;"><b>Título</td>
			<td style="padding-right: 25rem;"><b>Descricao</td>
			<td style="padding-right: 10rem;"><b>Paginas</td>
		</tr>

		<c:forEach items="${products }" var="product">
			<tr>
				<td><a
					href="${s:mvcUrl('PC#detalhe').arg(0, product.id).build() }">${product.titulo}</a></td>
				<td>${product.descricao}</td>
				<td>${product.paginas}</td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>