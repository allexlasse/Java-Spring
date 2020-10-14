<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>Cadastro de Livros</h1>

	<form:form action="${s:mvcUrl('PC#save').build() }" method="POST"
		commandName="products" enctype="multipart/form-data">
		<div>
			<label>Título</label> <input type="text" name="titulo">
			<form:errors path="titulo" />
		</div>

		<div>
			<label>Descrição</label>
			<textarea rows="10" cols="20" name="descricao"></textarea>
			<form:errors path="descricao" />
		</div>

		<div>
			<label>Páginas</label> <input type="text" name="paginas">
			<form:errors path="paginas" />
		</div>

		<div>
			<label>Data de Lançamento</label> <input type="text"
				name="dataLancamento">
			<form:errors path="dataLancamento" />
		</div>
		
		<div>
			<label>Sumário</label>
			<input type="file" name="sumario"> 
		</div>

		<br><p><b>Valores: </b>
		<c:forEach items="${tipos}" var="tipoPreco" varStatus="status">
			<div>
				<label>${tipoPreco}</label> <input type="text"
					name="listPreco[${status.index }].valor"> <input
					type="hidden" name="listPreco[${status.index }].tipoPreco"
					value="${tipoPreco}">
			</div>
		</c:forEach>


		<br>
		<button type="submit">Cadastrar</button>
	</form:form>
</body>
</html>