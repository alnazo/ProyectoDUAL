<%@ page pageEncoding="UTF-8" %>
<%@ page import="com.dual.proyectoDUAL.dto.Usuario" %>

<div class="row col-2 ms-1 sub-menu">
    <div class="top-section">
        <ul class="nav flex-column iconos">
            <li class="nav-item">
                <a class="nav-link ps-0" aria-current="page" href="/home"><i class="fas fa-home fa-lg"></i> <span class="text-hover">Inicio</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link ps-0" aria-current="page" href="/servicios"><i class="fas fa-search fa-lg"></i> <span class="text-hover">Servicios</span></a>
            </li>
            <% if(session.getAttribute("usuarioSesion") != null){ %>
            <li class="nav-item">
                <a class="nav-link ps-0" aria-current="page" href="/grupos"><i class="fas fa-users fa-lg"></i> <span class="text-hover">Grupos</span></a>
            </li>
            <% } %>
        </ul>
    </div>
    <div class="down-section">
        <ul class="nav flex-column">
            <li class="nav-item">
                <%
                if(session.getAttribute("usuarioSesion")!=null){
                Usuario usuario = (Usuario)session.getAttribute("usuarioSesion");
                %>
                    <div id="menUser" class="accordion-collapse collapse mb-3" data-bs-parent="#men">
                        <div class="accordion-body">
                            <ul class="nav flex-column">
                                <li class="nav-item">
                                    <a class="nav-link" href="/user/@<%=usuario.getUsername()%>">Perfil</a>
                                </li>
                                <% if (usuario.getAdmin()) { %>
                                <li class="nav-item">
                                    <a class="nav-link" href="/admin">Administracion</a>
                                </li>
                                <% } %>
                                <li class="nav-item">
                                    <a class="nav-link" href="/disconnect">Desconectar</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <div class="accordion-item">
                        <h2 class="accordion-header">
                            <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#menUser" aria-expanded="true" aria-controls="menUser">
                                Hola <%= usuario.getUsername() %>
                            </button>
                        </h2>
                    </div>
                <%} else {%>
                    <div>
                        <a href="/login" ><button class="btn">Inicio de sesión</button></a>
                    </div>
                    <div>
                        <a href="/registro" ><button class="btn">Registrar</button></a>
                    </div>
                <%}%>
            </li>
            <li class="nav-item">
                <button class="btn ps-0" id="switch-style" type="button"></button>
            </li>
        </ul>
    </div>
</div>