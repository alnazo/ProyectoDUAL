<%@ page pageEncoding="UTF-8" %>
<%@ page import="com.dual.proyectoDUAL.dto.Usuario" %>
<nav class="navbar navbar-expand-md mb-3">
  <div class="container-fluid">
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="home">Home</a>
        </li>
      </ul>
      <div class="d-flex">
          <%
          if(session.getAttribute("usuarioSesion")!=null){
            Usuario usuario = (Usuario)session.getAttribute("usuarioSesion");
          %>
            <div class="dropdown dropstart">
              <button class="btn btn-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                Menu
              </button>
              <ul class="dropdown-menu">
                <li><div class="dropdown-item">Hola <%= usuario.getUsername() %></div></li>
                <li><a class="dropdown-item" href="perfil">Perfil</a></li>
                <% if (usuario.isAdmin()) { %>
                    <li><a class="dropdown-item" href="admin">Administracion</a></li>
                <% } %>
                <li><a class="dropdown-item" href="disconnect">Desconectar</a></li>
              </ul>
            </div>
          <% } else { %>
            <a href="login"><button class="btn">Inicio de sesión</button></a>
            <a href="registro"><button class="btn">Registrar</button></a>
          <% } %>
      </div>
    </div>
  <button class="btn" id="switch-style" type="button"></button>
  </div>
</nav>