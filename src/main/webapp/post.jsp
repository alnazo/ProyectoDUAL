<%@ page pageEncoding="UTF-8" %>
<%@ page import="com.dual.proyectoDUAL.dto.Tablon" %>
<%@ page import="java.util.List" %>
<%
    Tablon tab = (Tablon) session.getAttribute("tablon");
    ServletContext context = (ServletContext) session.getAttribute("servletContext");
%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <%@ include file="/parts/meta.jsp" %>
        <title>ProyectoDUAL</title>
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
                            <div class="post row mt-2 mb-2 pt-2 pb-2">
                               <div class="col-1 imgperfil">
                                   <%
                                   if (tab.getIdUsuario().sourceImagen(context) != null){ %>
                                        <img src="<%= tab.getIdUsuario().sourceImagen(context) %>" alt="imgprerfil" class="perfilimg"/>
                                   <% } else { %>
                                        <i class="fas fa-user-circle fa-lg perfilimg"></i>
                                   <% } %>
                               </div>
                               <div class="row col-11">
                                   <div class="row col-12 datainfo">
                                       <div class="sublink">
                                           <a href="/user/@<%= tab.getIdUsuario().getUsername()%>" class="mainlink"><%=tab.getIdUsuario().getUsername()%></a>
                                           <a href="/user/@<%= tab.getIdUsuario().getUsername()%>" >@<%=tab.getIdUsuario().getUsername()%></a>
                                           <spam>·<spam>
                                           <a title="<%= tab.getCreateAt() %>"><%= tab.timeAgo() %></a>
                                       </div>
                                   </div>
                                   <div class="col-12 message">
                                       <%= tab.getMessage() %>
                                   </div>
                                   <div class="col-12 sublink">
                                        <a href="">❤️<%= tab.getLikes() %></a>
                                   </div>
                               </div>
                           </div>
                        </div>
                        <div class="col-md-2 text-center">
                            <div>
                                OTROS
                            </div>
                            <div>
                                SERVICIOS
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </body>
    <%@ include file="/parts/footer.jsp" %>
</html>