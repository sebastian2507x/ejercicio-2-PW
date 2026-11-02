<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Editar Producto</title>
</head>
<body>
	<h1>Editar Producto</h1>
	<form action="${pageContext.request.contextPath}/update/${producto.id}" method="post">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		Nombre: <input type="text" name="nombre" value="${producto.nombre}" /><br />
		Precio: <input type="text" name="precio" value="${producto.precio}" /><br />
		<input type="submit" value="Actualizar" />
	</form>
	<p>
		<a href="${pageContext.request.contextPath}/">Volver</a>
	</p>
</body>
</html>
