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
                                        <td><%=grupos.getId()%></td>
                                         <td><%=grupos.getServicio()%></td>
                                          <td><%=grupos.getUser1()%></td>
                                           <td><%=grupos.getUser2()%></td>
                                            <td><%=grupos.getUser3()%></td>
                                             <td><%=grupos.getUser4()%></td>
                                             <td><%=grupos.getUser5()%></td>
                                             <td><%=grupos.getUser6()%></td>
                                             <td><%=grupos.getUser7()%></td>
                                             <td><%=grupos.getUser8()%></td>
                                             <td><a href="" ><i clasgruposs="fa-solid fa-pencil"></i></a></td>
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
