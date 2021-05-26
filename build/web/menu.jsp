<%
    String opcion = request.getParameter("opcion");
%>
<nav class="navbar navbar-expand-md navbar-dark bg-primary">
    <div class="container">
        <a class="navbar-brand" href="#">Gestión de Usuarios</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavDropdown">
            <ul class="navbar-nav">
                <li class="nav-item <%= (opcion.equals("inicio") ? "active" : "")%>">
                    <a class="nav-link" href="UsuarioController">Usuarios <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item <%= (opcion.equals("roles") ? "active" : "")%>">
                    <a class="nav-link" href="RolController">Roles</a>
                </li>
                <li class="nav-item <%= (opcion.equals("permisos") ? "active" : "")%>">
                    <a class="nav-link" href="PermisoController">Permisos</a>
                </li>
            </ul>
        </div>
    </div>
</nav>