<%@ page pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.dual.proyectoDUAL.dto.Grupo" %>
<%
    List<Grupo> grupos= (List<Grupo>) session.getAttribute("grupo");
%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <%@ include file="../parts/meta.jsp" %>
        <title>Administración de grupos - ProyectoDUAL</title>
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
                                <h2>Administración de grupos</h2>
                            </div>
                               <table class="grupos-table">
                                    <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>Servicio</th>
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
                                         <td><%=grupo.getServicio()%></td>
                                          <td><%=grupo.getUser1()%></td>
                                           <td><%=grupo.getUser2()%></td>
                                            <td><%=grupo.getUser3()%></td>
                                             <td><%=grupo.getUser4()%></td>
                                             <td><%=grupo.getUser5()%></td>
                                             <td><%=grupo.getUser6()%></td>
                                             <td><%=grupo.getUser7()%></td>
                                             <td><%=grupo.getUser8()%></td>
                                             <td><form action="/admin/grupo" method="post" >
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
    <%@ include file="../parts/footer.jsp" %>
