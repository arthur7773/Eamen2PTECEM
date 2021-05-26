<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Formulario de Permisos</title>
        <link rel="stylesheet" href="css/bootstrap.min.css">
    </head>
    <body>
        <jsp:include page="menu.jsp">
            <jsp:param name="opcion" value="permisos" />
        </jsp:include>
        <div class="container">
            <h1><c:if test="${permiso.id == 0}">Nuevo</c:if>
                <c:if test="${permiso.id != 0}">Editar</c:if> permiso
                </h1>
                <form action="PermisoController" method="post">
                    <input type="hidden" name="id" value="${permiso.id}" />
                <div class="form-group">
                    <label>Usuario:</label>
                    <input type="text" name="id_usuario" value="${permiso.id_usuario}" class="form-control" >
                </div>
                <div class="form-group">
                    <label>Rol:</label>
                    <input type="text" name="id_rol" value="${permiso.id_rol}" class="form-control" >
                </div>
                <button type="submit" class="btn btn-primary">Enviar</button>
            </form>
        </div>
        <script type="text/javascript" src="js/jquery-3.3.1.slim.min.js"></script>
        <script type="text/javascript" src="js/bootstrap.min.js"></script>
    </body>
</html>
