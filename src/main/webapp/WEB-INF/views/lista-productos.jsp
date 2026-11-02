<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lista de Productos</title>
</head>
<body>
	<p>
		Sesión iniciada como: <b>${pageContext.request.userPrincipal.name}</b>
	</p>
	<form action="${pageContext.request.contextPath}/logout" method="post">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		<input type="submit" value="Cerrar sesión" />
	</form>

	<h1>Lista de Productos</h1>
	<table border="1">
		<tr>
			<th>ID</th>
			<th>Nombre</th>
			<th>Precio</th>
			<th>Acciones</th>
		</tr>
		<c:forEach var="producto" items="${productos}">
			<tr>
				<td>${producto.id}</td>
				<td>${producto.nombre}</td>
				<td>${producto.precio}</td>
				<td><a href="${pageContext.request.contextPath}/edit/${producto.id}">Editar</a>
					| <a href="${pageContext.request.contextPath}/delete/${producto.id}">Eliminar</a></td>
			</tr>
		</c:forEach>
	</table>
	<p>
		<a href="${pageContext.request.contextPath}/new">Agregar un nuevo producto</a>
	</p>
</body>
</html>
