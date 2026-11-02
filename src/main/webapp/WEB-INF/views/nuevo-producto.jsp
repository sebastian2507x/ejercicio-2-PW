<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Nuevo Producto</title>
</head>
<body>
	<h1>Agregar Nuevo Producto</h1>
	<form action="${pageContext.request.contextPath}/save" method="post">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		Nombre: <input type="text" name="nombre" /><br />
		Precio: <input type="text" name="precio" /><br />
		<input type="submit" value="Guardar" />
	</form>
	<p>
		<a href="${pageContext.request.contextPath}/">Volver</a>
	</p>
</body>
</html>
