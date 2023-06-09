<%@ page pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.dual.proyectoDUAL.dto.Servicio" %>
<%
    List<Servicio> servicios= (List<Servicio>) session.getAttribute("servicio");
%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <%@ include file="/parts/meta.jsp" %>
        <title>Administración de servicios - ProyectoDUAL</title>
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
                                <h2>Administración de servicios</h2>
                            </div>
                            <ul class="nav mb-3">
                                <li class="nav-item"><a class="nav-link link" href="/admin/usuario">Usuarios</a></li>
                                <li class="nav-item"><a class="nav-link link" href="/admin/servicio">Servicios</a></li>
                                <li class="nav-item"><a class="nav-link link" href="/admin/tablon">Tablon</a></li>
                                <li class="nav-item"><a class="nav-link link" href="/admin/grupo">Grupos</a></li>
                            </ul>
                            <div class="col-3 p-0" >
                            <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
                                Añadir servicio
                            </button>
                            </div>
                            <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                              <div class="modal-dialog">
                                <div class="modal-content">
                                  <div class="modal-header">
                                    <h1 class="modal-title fs-5" id="exampleModalLabel">Modal title</h1>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                  </div>
                                   <form action="/admin/servicio" method="POST">
                                   <div class="modal-body">


                                      <label for="nombre">Nombre:</label>
                                      <input type="text" id="nombre" name="nombre" required><br>

                                      <label for="plan">Plan:</label>
                                      <input type="text" id="plan" name="plan" required><br>

                                      <label for="precio">Precio:</label>
                                      <input type="number" id="precio" name="precio" step="0.01" required><br>


                                      <label for="web">Sitio web:</label>
                                      <input type="url" id="web" name="web" required><br>
                                  </div>
                                  <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                    <button type="submit" class="btn btn-primary">Save changes</button>
                                  </div>
                                   </form>
                                </div>
                              </div>
                            </div>
                               <table class="servicios-table">
                                    <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>Nombre</th>
                                            <th>Plan</th>
                                            <th>Precio</th>
                                             <th>Web</th>
                                            <th></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                <%
                                    for(Servicio servicio:servicios){
                                %>
                                    <tr>
                                        <td><%=servicio.getId()%></td>
                                         <td><%=servicio.getNombre()%></td>
                                         <td><%=servicio.getPlan()%></td>
                                         <td><%=servicio.getPrecio()%></td>
                                         <td><a href="<%=servicio.getWeb()%>" target="blank"><%=servicio.getWeb()%></a></td>

                                          <td><form action="/servicios" method="post" >
                                          <input type="text" hidden name="id" value="<%=servicio.getId()%>">
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