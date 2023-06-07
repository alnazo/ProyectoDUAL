<%@ page pageEncoding="UTF-8" %>
<%@ page import="com.dual.proyectoDUAL.web.notifications.Notificaciones" %>
<%@ page import="com.dual.proyectoDUAL.dto.Grupo" %>
<%@ page import="com.dual.proyectoDUAL.dto.Servicio" %>


<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%

List<Grupo> grupos = (List<Grupo>) session.getAttribute("grupos");
Map<String, List<Servicio>> serviciosGruped = (Map<String, List<Servicio>>) session.getAttribute("serviciosGruped");

%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <%@ include file="/parts/meta.jsp" %>
        <title>Grupos - ProyectoDUAL</title>
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

                    <button class="btn btn-primary mb-4" type="button" data-bs-toggle="modal" data-bs-target="#CrearGrupo">Crear Grupo</button>

                    <div class="modal fade" id="CrearGrupo" tabindex="-1" aria-labelledby="CrearGrupo" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <form acction="/grupos" method="POST">
                                    <div class="modal-header">
                                        <h1 class="modal-title fs-5" id="exampleModalLabel">Crear grupo</h1>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        <div class="mb-3">
                                            <label for="servicio" class="form-label">Seleccione plataforma</label>
                                            <select class="form-select" aria-label="servicio" id="sevicio" name="servicio">
                                                <option value="" selected>Elija una opcion...</option>
                                                <% if (serviciosGruped != null) {
                                                    for (String servi : serviciosGruped.keySet()){
                                                %>
                                                    <option value="<%=servi%>"><%=servi%></option>
                                                <% } } %>
                                            </select>
                                        </div>
                                        <div class="mb-3">
                                            <label for="plan" class="form-label">Seleccione plan</label>
                                            <select disabled class="form-select" aria-label="plan" id="plan" name="plan">
                                                <option value="" selected>Elija una opcion...</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-primary">Crear grupo</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>

                    <% if(grupos.size() > 0){ %>
                        <table class="table">
                            <thead>
                                <tr>
                                    <th scope="col">Servicio</th>
                                    <th scope="col">Plan</th>
                                    <th scope="col">Precio (desde)</th>
                                    <th scope="col">Hueco 1 (Lider)</th>
                                    <th scope="col">Hueco 2</th>
                                    <th scope="col">Hueco 3</th>
                                    <th scope="col">Hueco 4</th>
                                    <th scope="col">Hueco 5</th>
                                    <th scope="col">Hueco 6</th>
                                    <th scope="col">Hueco 7</th>
                                    <th scope="col">Hueco 8</th>
                                </tr>
                            </thead>
                            <tbody>
                                <% for(Grupo gr : grupos) { %>
                                    <tr>
                                        <td scope="row"><%= gr.getServicio().getNombre() %></td>
                                        <td><%= gr.getServicio().getPlan() %></td>
                                        <td><%= gr.getServicio().getPrecio()/8 %></td>
                                        <td><%= gr.getUser1().getUsername() %></td>
                                        <% if(gr.getUser2() != null) { %>
                                            <td><%= gr.getUser2().getUsername() %></td>
                                        <% } else { %>
                                            <td><i class="fas fa-plus-circle fa-lg"></i></td>
                                        <% } %>
                                        <% if(gr.getUser3() != null) { %>
                                            <td><%= gr.getUser3().getUsername() %></td>
                                        <% } else { %>
                                            <td><i class="fas fa-plus-circle fa-lg"></i></td>
                                        <% } %>
                                        <% if(gr.getUser4() != null) { %>
                                            <td><%= gr.getUser4().getUsername() %></td>
                                        <% } else { %>
                                            <td><i class="fas fa-plus-circle fa-lg"></i></td>
                                        <% } %>
                                        <% if(gr.getUser5() != null) { %>
                                            <td><%= gr.getUser5().getUsername() %></td>
                                        <% } else { %>
                                            <td><i class="fas fa-plus-circle fa-lg"></i></td>
                                        <% } %>
                                        <% if(gr.getUser6() != null) { %>
                                            <td><%= gr.getUser6().getUsername() %></td>
                                        <% } else { %>
                                            <td><i class="fas fa-plus-circle fa-lg"></i></td>
                                        <% } %>
                                        <% if(gr.getUser7() != null) { %>
                                            <td><%= gr.getUser7().getUsername() %></td>
                                        <% } else { %>
                                            <td><i class="fas fa-plus-circle fa-lg"></i></td>
                                        <% } %>
                                        <% if(gr.getUser8() != null) { %>
                                            <td><%= gr.getUser8().getUsername() %></td>
                                        <% } else { %>
                                            <td><i class="fas fa-plus-circle fa-lg"></i></td>
                                        <% } %>
                                    </tr>
                                <% } %>
                            </tbody>
                        </table>
                    <% } else { %>
                        <h2>No hay grupos</h2>
                    <% } %>
                </div>
            </div>
        </div>
    </div>

    <%if(Notificaciones.error){%>
    <div class="toast-container position-fixed bottom-0 end-0 p-3">
        <div class="toast fade show" role="alert" aria-live="assertive" aria-atomic="true">
            <div class="toast-header">
                <svg class="bd-placeholder-img rounded me-2" width="20" height="20" xmlns="http://www.w3.org/2000/svg" aria-hidden="true" preserveAspectRatio="xMidYMid slice" focusable="false"><rect width="100%" height="100%" fill="#DF1C44"></rect></svg>
                <strong class="me-auto">Error</strong>
                <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
            </div>
            <div class="toast-body">
                <div id="errorMsg"><%=Notificaciones.msg%></div>
            </div>
        </div>
    </div>
    <%
    Notificaciones.error = false;
    Notificaciones.msg = null;
    }
    %>

    </body>
    <%@ include file="/parts/footer.jsp" %>
    <script src="/js/grupos.js" charset="UTF-8"></script>
</html>