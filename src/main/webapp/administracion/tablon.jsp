<%@ page pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.dual.proyectoDUAL.dto.Tablon" %>
<%
    List<Tablon> posts= (List<Tablon>) session.getAttribute("tablon");
%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <%@ include file="/parts/meta.jsp" %>
        <title>Administración del Tablon - ProyectoDUAL</title>
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
                                <h2>Administración de posts</h2>
                            </div>

                            <ul class="nav mb-3">
                                <li class="nav-item"><a class="nav-link link" href="/admin/usuario">Usuarios</a></li>
                                <li class="nav-item"><a class="nav-link link" href="/admin/servicio">Servicios</a></li>
                                <li class="nav-item"><a class="nav-link link" href="/admin/tablon">Tablon</a></li>
                                <li class="nav-item"><a class="nav-link link" href="/admin/grupo">Grupos</a></li>
                            </ul>

                               <table class="tablon-table">
                                    <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>Mensaje</th>
                                            <th>Usuario</th>
                                            <th></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                <%
                                    for(Tablon tablon:posts){
                                %>
                                    <tr>
                                        <td><%=tablon.getId()%></td>
                                         <td><%=tablon.getMessage()%></td>
                                          <td><%=tablon.getIdUsuario().getUsername()%></td>
                                          <td><form action="/admin/tablon" method="post">
                                          <input type="text" hidden name="id" value="<%=tablon.getId()%>">
                                          <button><i class="fas fa-regular fa-trash"></i></button>
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