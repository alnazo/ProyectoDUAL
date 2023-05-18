<%@ page pageEncoding="UTF-8" %>
<%@ page import="com.dual.proyectoDUAL.dto.Usuario" %>

<div class="top-section">
    <ul class="nav flex-column iconos">
        <li class="nav-item">
            <a class="nav-link" aria-current="page" href="home"><i class="fas fa-home fa-lg"></i> <span class="text-hover">Inicio</span></a>
        </li>
        <li class="nav-item">
            <a class="nav-link" aria-current="page" href="busqueda"><i class="fas fa-search fa-lg"></i> <span class="text-hover">Busqueda</span></a>
        </li>
    </ul>
</div>
<div class="down-section">
    <ul class="nav flex-column">
        <li class="nav-item">
            <%
            if(session.getAttribute("usuarioSesion")!=null){
            Usuario usuario = (Usuario)session.getAttribute("usuarioSesion");
            %>
                <div class="dropdown dropstart">
                    <button class="btn" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Hola <%= usuario.getUsername() %>
                    </button>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="perfil">Perfil</a></li>
                        <% if (usuario.getAdmin()) { %>
                            <li><a class="dropdown-item" href="admin">Administracion</a></li>
                        <% } %>
                        <li><a class="dropdown-item" href="disconnect">Desconectar</a></li>
                    </ul>
                </div>
            <%} else {%>
                <a href="login"><button class="btn">Inicio de sesión</button></a>
                <a href="registro"><button class="btn">Registrar</button></a>
            <%}%>
        </li>
        <li class="nav-item">
            <button class="btn" id="switch-style" type="button"></button>
        </li>
    </ul>
</div>
