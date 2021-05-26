<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Formulario de Usuario</title>
        <link rel="stylesheet" href="css/bootstrap.min.css">
    </head>
    <body>
        <jsp:include page="menu.jsp">
            <jsp:param name="opcion" value="usuarios" />
        </jsp:include>
        <div class="container">
            <h1><c:if test="${usuario.id == 0}">Nuevo</c:if>
                <c:if test="${usuario.id != 0}">Editar</c:if> usuario
                </h1>
                <form action="UsuarioController" method="post">
                    <input type="hidden" name="id" value="${usuario.id}" />
                <div class="form-group">
                    <label>Usuario:</label>
                    <input type="text" name="usuario" value="${usuario.usuario}" class="form-control" >
                </div>
                <div class="form-group">
                    <label>Correo:</label>
                    <input type="mail" name="correo" value="${usuario.correo}" class="form-control" >
                </div>
                <div class="form-group">
                    <label>Password:</label>
                    <input type="password" name="clave" value="${usuario.clave}" class="form-control" >
                </div>
                <button type="submit" class="btn btn-primary">Enviar</button>
            </form>
        </div>
        <script type="text/javascript" src="js/jquery-3.3.1.slim.min.js"></script>
        <script type="text/javascript" src="js/bootstrap.min.js"></script>
    </body>
</html>
