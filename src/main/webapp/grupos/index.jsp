<%@ page pageEncoding="UTF-8" %>
<%@ page import="com.dual.proyectoDUAL.web.notifications.Notificaciones" %>
<%@ page import="com.dual.proyectoDUAL.dto.Grupo" %>

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
                                            <label for="servicio" class="form-label"></label>
                                            <select class="form-select" aria-label="servicio" id="sevicio" name="servicio">
                                                <% if (paramServicio != null){ %>
                                                    <option value="">Elija una opcion...</option>
                                                <% } else { %>
                                                    <option value="" selected>Elija una opcion...</option>
                                                <% } %>
                                                <% if (serviciosGruped != null) {
                                                    for (String servi : serviciosGruped.keySet()){
                                                %>
                                                    <% if (paramServicio != null){ %>
                                                        <option value="<%=servi%>" <%= (paramServicio.equals(servi)) ? "selected" : "" %>><%=servi%></option>
                                                    <% } else { %>
                                                        <option value="<%=servi%>"><%=servi%></option>
                                                <% } } } %>
                                            </select>
                                        </div>
                                        <div class="mb-3">
                                            <label for="plan" class="form-label"></label>

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
</html>