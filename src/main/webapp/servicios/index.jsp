<%@ page pageEncoding="UTF-8" %>
<%@ page import="com.dual.proyectoDUAL.web.notifications.Notificaciones" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.dual.proyectoDUAL.dto.Servicio" %>


<%
List<Servicio> servicios = (List<Servicio>) session.getAttribute("servicios");
Map<String, List<Servicio>> serviciosGruped = (Map<String, List<Servicio>>) session.getAttribute("serviciosGruped");

String paramServicio = request.getParameter("servicio");
String paramMin = request.getParameter("min");
String paramMax = request.getParameter("max");
%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <%@ include file="/parts/meta.jsp" %>
        <title>Servicios - ProyectoDUAL</title>
        <link rel="stylesheet" href="/css/formRangeSlider.css">
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

                    <form action"#" method="GET">
                        <div class="row mb-3 align-items-center">
                            <div class="col-auto me-3 text-center">
                                <label for="servicio" class="form-label">Servicio:</label>
                                <select class="form-select" aria-label="servicios" id="sevicios" name="servicio">
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
                            <div class="col-auto me-3 text-center">
                                <label for="precio" class="form-label">Precio:</label>
                                <div class="min-max-slider" data-legendnum="2">
                                    <label for="min">Minimo</label>
                                    <input id="min" class="min" name="min" type="range" step="1" min="1" max="100" />
                                    <label for="max">Maximo</label>
                                    <input id="max" class="max" name="max" type="range" step="1" min="1" max="100" />
                                </div>
                            </div>
                            <div class="col-auto me-3">
                                <button class="btn btn-primary" type="submit">Filtrar</button>
                                <a href="/servicios"><button class="btn btn-primary" type="button">Limpiar filtro</button></a>
                            </div>
                        </div>
                    </form>

                    <%
                    if (servicios != null){
                    %>
                        <div class="row row-cols-2 row-cols-lg-4 g-4">
                            <%
                                for (Servicio servicio : servicios){
                            %>
                                <div class="col">
                                    <div class="card">
                                      <div class="card-body">
                                        <h4 class="card-title"><%= servicio.getNombre() %> </h4>
                                        <h5 class="card-tittle"><%= servicio.getPlan()%> </h5>
                                        <p class="card-text">Precio: <%=servicio.getPrecio()%> €</p>
                                        <a href="/grupos?servicio=<%= servicio.getNombre() %>&plan=<%= servicio.getPlan()%>" class="btn btn-primary">Buscar grupo</a>
                                      </div>
                                    </div>
                                </div>
                            <% } %>
                        </div>
                    <% } else { %>
                        <div> No hay Servicios disponibles</div>
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
    <script>
        var parMin = <%= paramMin %>;
        var parMax = <%= paramMax %>;
    </script>
    <script src="/js/rangeSlider.js"></script>
</html>