<%@ page pageEncoding="UTF-8" %>
<%
    ServletContext context = (ServletContext) session.getAttribute("servletContext");
%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <%@ include file="../parts/meta.jsp" %>
        <title>Administración - ProyectoDUAL</title>
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
                                <h2>Administración</h2>
                            </div>
                            <ul>
                                <li><a href="admin/usuario">Usuarios</a></li>
                                 <li><a href="admin/servicio">Servicios</a></li>
                                  <li><a href="admin/tablon">Tablon</a></li>
                                   <li><a href="admin/grupo">Grupos</a></li>
                              </ul>

                        </div>
                    </div>
                </div>
            </div>
        </div>
        </body>
    <%@ include file="../parts/footer.jsp" %>
</html>