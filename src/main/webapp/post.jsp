<%@ page pageEncoding="UTF-8" %>
<%@ page import="com.dual.proyectoDUAL.dto.Tablon" %>
<%@ page import="java.util.List" %>
<%
    Tablon tab = (Tablon) session.getAttribute("tablon");
    ServletContext context = (ServletContext) session.getAttribute("servletContext");
    Usuario user = (Usuario) session.getAttribute("usuarioSesion");
%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <%@ include file="/parts/meta.jsp" %>
        <title>Sharefy - Mensaje de @<%= tab.getIdUsuario().getUsername() %></title>
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
                                   <div class="row col-12 datainfo justify-content-between">
                                       <div class="sublink col-8">
                                           <a href="/user/@<%= tab.getIdUsuario().getUsername()%>" class="mainlink"><%=tab.getIdUsuario().getUsername()%></a>
                                           <a href="/user/@<%= tab.getIdUsuario().getUsername()%>" >@<%=tab.getIdUsuario().getUsername()%></a>
                                           <spam>·<spam>
                                           <a title="<%= tab.getCreateAt() %>"><%= tab.timeAgo() %></a>
                                       </div>
                                       <div class="row col-1 text-end">
                                            <div class="col-1">
                                                <a class="copy" id="copy" onclick="copyLink('/post/<%=tab.getId() %>')" title="Copiar enlace"><i class="fas fa-share fa-sm"></i></a>
                                            </div>
                                            <%
                                                if (user != null){
                                                    if(tab.getIdUsuario().getId() == user.getId()){
                                            %>
                                                <div class="col-1">
                                                    <form action="/post/delete/<%=tab.getId()%>" method="POST">
                                                        <button class="trash btn" id="trash" type="submit" title="Eliminar mensaje"><i class="fas fa-trash-alt fa-sm"></i></button>
                                                    </form>
                                                </div>
                                            <% } } %>
                                      </div>
                                   </div>
                                   <div class="col-12 message">
                                       <%= tab.getMessage() %>
                                   </div>
                                   <div class="col-12 sublink">
                                        <form action="/like/post?actual=<%= tab.getLikes() %>" method="POST">
                                            <input hidden name="post" value="<%= tab.getId() %>" />
                                            <button class="btn" type="submit" id="like-post-<%= tab.getId() %>">❤️<%= tab.getLikes() %></button>
                                        </form>
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
    <script src="/js/tablon.js"></script>
</html>