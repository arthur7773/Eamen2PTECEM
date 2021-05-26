
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Roles</title>
        <link rel="stylesheet" href="css/bootstrap.min.css">
    </head>
    <body>
        <jsp:include page="menu.jsp">
            <jsp:param name="opcion" value="roles" />
        </jsp:include>
        <div class="container">
            <h1>Roles</h1>
            <p><a href="RolController?action=add" class="btn btn-success">Nuevo</a></p>
            <table class="table table-bordered table-striped ">
                <tr>
                    <th>Id</th>
                    <th>Descripción</th>
                    <th></th>
                    <th></th>
                </tr>
                <c:forEach var="item" items="${roles}">
                    <tr>
                        <td>${item.id}</td>
                        <td>${item.descripcion}</td>
                        <td><a href="RolController?action=edit&id=${item.id}" class="btn btn-primary">Editar</a></td>
                        <td><a href="RolController?action=delete&id=${item.id}" class="btn btn-danger"onclick="return(confirm('Esta seguro ???'))">Eliminar</a></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <script type="text/javascript" src="js/jquery-3.3.1.slim.min.js"></script>
        <script type="text/javascript" src="js/bootstrap.min.js"></script>
    </body>
</html>
