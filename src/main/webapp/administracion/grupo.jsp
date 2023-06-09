<%@ page pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.dual.proyectoDUAL.dto.Grupo" %>
<%
    List<Grupo> grupos= (List<Grupo>) session.getAttribute("grupo");
%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <%@ include file="/parts/meta.jsp" %>
        <title>Administración de grupos - ProyectoDUAL</title>
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
                                <h2>Administración de grupos</h2>
                            </div>

                            <ul class="nav mb-3">
                                <li class="nav-item"><a class="nav-link link" href="/admin/usuario">Usuarios</a></li>
                                <li class="nav-item"><a class="nav-link link" href="/admin/servicio">Servicios</a></li>
                                <li class="nav-item"><a class="nav-link link" href="/admin/tablon">Tablon</a></li>
                                <li class="nav-item"><a class="nav-link link" href="/admin/grupo">Grupos</a></li>
                            </ul>

                               <table class="grupos-table">
                                    <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>Servicio</th>
                                            <th>Plan</th>
                                            <th>User1</th>
                                            <th>User2</th>
                                            <th>User3</th>
                                            <th>User4</th>
                                            <th>User5</th>
                                            <th>User6</th>
                                            <th>User7</th>
                                            <th>User8</th>
                                            <th></th>
                                            <th></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                <%
                                    for(Grupo grupo:grupos){
                                %>
                                    <tr>
                                        <td><%=grupo.getId()%></td>
                                         <td><%=grupo.getServicio().getNombre()%></td>
                                         <td><%=grupo.getServicio().getPlan()%></td>
                                          <td><a href="/user/@<%=grupo.getUser1().getUsername()%>" ><%=grupo.getUser1().getUsername()%></a></td>
                                          <% if(grupo.getUser2() != null){ %>
                                            <td><a href="/user/@<%=grupo.getUser2().getUsername()%>" ><%=grupo.getUser2().getUsername()%></a></td>
                                           <% } else { %>
                                            <td>Sin usuario</td>
                                           <% } %>
                                           <% if(grupo.getUser3() != null){ %>
                                                <td><a href="/user/@<%=grupo.getUser3().getUsername()%>" ><%=grupo.getUser3().getUsername()%></a></td>
                                            <% } else { %>
                                                <td>Sin usuario</td>
                                            <% } %>
                                            <% if(grupo.getUser4() != null){ %>
                                                <td><a href="/user/@<%=grupo.getUser4().getUsername()%>" ><%=grupo.getUser4().getUsername()%></a></td>
                                             <% } else { %>
                                                <td>Sin usuario</td>
                                             <% } %>
                                             <% if(grupo.getUser5() != null){ %>
                                                <td><a href="/user/@<%=grupo.getUser5().getUsername()%>" ><%=grupo.getUser5().getUsername()%></a></td>
                                             <% } else { %>
                                                <td>Sin usuario</td>
                                             <% } %>
                                             <% if(grupo.getUser6() != null){ %>
                                                <td><a href="/user/@<%=grupo.getUser6().getUsername()%>" ><%=grupo.getUser6().getUsername()%></a></td>
                                             <% } else { %>
                                                <td>Sin usuario</td>
                                             <% } %>
                                             <% if(grupo.getUser7() != null){ %>
                                                <td><a href="/user/@<%=grupo.getUser7().getUsername()%>" ><%=grupo.getUser7().getUsername()%></a></td>
                                             <% } else { %>
                                                <td>Sin usuario</td>
                                             <% } %>
                                             <% if(grupo.getUser8() != null){ %>
                                                <td><a href="/user/@<%=grupo.getUser8().getUsername()%>" ><%=grupo.getUser8().getUsername()%></a></td>
                                             <% } else { %>
                                                <td>Sin usuario</td>
                                             <% } %>
                                             <td>
                                                 <form action="/admin/grupo" method="post" >
                                                     <input type="text" hidden name="id" value="<%=grupo.getId()%>">
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
