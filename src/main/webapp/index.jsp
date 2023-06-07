<%@ page pageEncoding="UTF-8" %>
<%@ page import="com.dual.proyectoDUAL.dto.Tablon" %>
<%@ page import="com.dual.proyectoDUAL.dto.Usuario" %>
<%@ page import="com.dual.proyectoDUAL.web.notifications.Notificaciones" %>
<%@ page import="java.util.List" %>

<%
ServletContext context = (ServletContext) session.getAttribute("servletContext");
Usuario user = (Usuario) session.getAttribute("usuarioSesion");
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
                            <%
                            if(user != null){
                            %>
                            <div class="row mt-2 mb-3 publicar">
                                <form action="/sendPost" method="POST">
                                    <div class="col-12 mb-3 mt-3">
                                        <textarea class="form-control" placeholder="Escribe tu mensaje..." id="mensaje" name="mensaje" onkeyup="contarLongitud()"></textarea>
                                    </div>
                                    <div class="row col-12 mb-3 justify-content-between">
                                        <div class="col-6">
                                            <button id="mensaje-envio" type="submit" class="btn btn-primary">Enviar</button>
                                        </div>
                                        <div class="col-1">
                                            <span style="--progress: 0deg;" id="longitud"></span>
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <% } %>
                            <%
                            if(session.getAttribute("tablon")!=null){
                                List<Tablon> list = (List<Tablon>)session.getAttribute("tablon");
                                for (Tablon tab : list){
                            %>
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
                                               <a href="/user/@<%= tab.getIdUsuario().getUsername() %>" class="mainlink"><%=tab.getIdUsuario().getUsername()%></a>
                                               <a href="/user/@<%= tab.getIdUsuario().getUsername() %>" >@<%=tab.getIdUsuario().getUsername()%></a>
                                               <spam>·<spam>
                                               <a href="/post/<%=tab.getId()%>" title="<%= tab.getCreateAt() %>"><%= tab.timeAgo() %></a>
                                           </div>
                                           <div class="row col-1 text-end">
                                                <div class="col-1">
                                                    <a href="" title="Copiar enlace"><i class="fas fa-share fa-sm"></i></a>
                                                </div>
                                                <%
                                                if (user != null){
                                                    if(tab.getIdUsuario().getId() == user.getId()){
                                                %>
                                                <div class="col-1">
                                                    <a href="" ><i class="fas fa-trash-alt fa-sm"></i></a>
                                                </div>
                                                <% }} %>
                                           </div>
                                       </div>
                                       <div class="col-12 message">
                                           <%= tab.getMessage() %>
                                       </div>
                                       <div class="col-12 sublink">
                                            <a id="<%= tab.getId() %>" href="">❤️<%= tab.getLikes() %></a>
                                       </div>
                                   </div>
                               </div>
                            <%
                                }
                            } else {
                            %>
                                <div>No hay nada que mostar</div>
                            <% } %>
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
    <script src="/js/sendMenssage.js"></script>
</html>