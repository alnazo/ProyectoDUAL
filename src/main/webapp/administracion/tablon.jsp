<%@ page pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.dual.proyectoDUAL.dto.Tablon" %>
<%
    List<Tablon> posts= (List<Tablon>) session.getAttribute("tablon");
%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <%@ include file="../parts/meta.jsp" %>
        <title>Administración del Tablon - ProyectoDUAL</title>
        <%@ include file="../parts/header.jsp" %>
    </head>
    <body>
        <div class="container-fluid">
            <div class="row">
                <div class="col-2 menu">
                    <%@ include file="../parts/nav.jsp" %>
                </div>
                <div class="col-10 content">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-10">
                                <h2>Administración de posts</h2>
                            </div>
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
                                             <td><a href="" ><i class="fa-regular fa-trash"></i></a></td>
                                    </tr>
                                <% } %>
                                </tbody>
                                </table>
                    </div>
                </div>
            </div>
        </div>
        </body>
    <%@ include file="../parts/footer.jsp" %>
</html>