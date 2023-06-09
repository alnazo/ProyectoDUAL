<%@ page pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.dual.proyectoDUAL.dto.Usuario" %>
<%
    List<Usuario> usuarios= (List<Usuario>) session.getAttribute("usuario");
%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <%@ include file="/parts/meta.jsp" %>
        <title>Administración de usuarios - ProyectoDUAL</title>
        <%@ include file="/parts/header.jsp" %>
    </head>
    <body>
        <div class="container-fluid">
            <div class="row">
                <div class="col-2 menu">
                    <%@ include file="/parts/nav.jsp" %>
                </div>
                <div class="col-10 content">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-10">
                                <h2>Administración de usuarios</h2>
                            </div>
                            <ul class="nav mb-3">
                                <li class="nav-item"><a class="nav-link link" href="/admin/usuario">Usuarios</a></li>
                                <li class="nav-item"><a class="nav-link link" href="/admin/servicio">Servicios</a></li>
                                <li class="nav-item"><a class="nav-link link" href="/admin/tablon">Tablon</a></li>
                                <li class="nav-item"><a class="nav-link link" href="/admin/grupo">Grupos</a></li>
                            </ul>
                               <table class="usuarios-table">
                                    <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>Usuario</th>
                                            <th>Email</th>
                                            <th>Imagen de Perfil</th>
                                            <th>Nacimiento</th>
                                            <th>Admin</th>
                                            <th></th>
                                            <th></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                <%
                                    for(Usuario usuario:usuarios){
                                %>
                                    <tr>
                                        <td><%=usuario.getId()%></td>
                                         <td><%=usuario.getUsername()%></td>
                                          <td><%=usuario.getEmail()%></td>
                                           <td><%=usuario.getImagen()%></td>
                                            <td><%=usuario.getNacimiento()%></td>
                                             <td><%=usuario.getAdmin()%></td>
                                             <td><a href="" class="link" ><i class="fas fa-pencil-alt"></i></a></td>
                                             <td><form action="/admin/usuario" method="post" >
                                             <input type="text" hidden name="id" value="<%=usuario.getId()%>">
                                             <button><i class="fas fa-regular fa-trash" ></i></button>
                                             </form>
                                             </td>
                                    </tr>
                                <% } %>
                                </tbody>
                                </table>
                    </div>
                </div>
            </div>
        </div>
        </body>
    <%@ include file="/parts/footer.jsp" %>
</html>