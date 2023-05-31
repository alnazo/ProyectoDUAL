<%@ page pageEncoding="UTF-8" %>
<%@ page import="com.dual.proyectoDUAL.dto.Usuario" %>
<%@ page import="com.dual.proyectoDUAL.dto.Tablon" %>
<%@ page import="java.util.List" %>
<%
ServletContext context = (ServletContext) session.getAttribute("servletContext");
Usuario user = (Usuario) session.getAttribute("userSearch");
List<Tablon> list = (List<Tablon>)session.getAttribute("userTablon");
int size = (list!=null) ? list.size() : 0;
%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <%@ include file="../parts/meta.jsp" %>
        <title>Perfil de @<%=user.getUsername()%></title>
        <%@ include file="../parts/header.jsp" %>
    </head>
    <body>
    <div class="container-fluid">
        <div class="row">
            <div class="col-2 menu">
                <%@ include file="../parts/nav.jsp" %>
            </div>
            <div class="col-10 content">

                <div class="col">
                    <div class="card">
                        <div class="rounded-top text-white d-flex flex-row" style="background-color: #000; height:200px;">
                            <div class="ms-4 mt-5 d-flex flex-column" style="width: 150px;">
                                <%
                                if (user.sourceImagen(context) != null){ %>
                                <img src="<%= user.sourceImagen(context) %>"
                                alt="Imagen de <%=user.getUsername()%>"class="img-fluid img-thumbnail mt-4 mb-2"
                                style="width: 150px; z-index: 1"/>
                                <% } else { %>
                                <i class="fas fa-user-circle fa-lg img-fluid img-thumbnail imgdefault mt-4 mb-2" style="width: 150px; z-index: 1"></i>
                                <% } %>
                                <button type="button" class="btn btn-outline-dark" data-mdb-ripple-color="dark"
                                style="z-index: 1;">
                                Edit profile
                                </button>
                            </div>
                            <div class="ms-3" style="margin-top: 130px;">
                                <h5>@<%= user.getUsername() %></h5>
                            </div>
                        </div>
                        <div class="p-4 text-black" style="background-color: #f8f9fa;">
                            <div class="d-flex justify-content-end text-center py-1">
                                <div class="px-3">
                                <p class="mb-1 h5">0</p>
                                <p class="small text-muted mb-0">Grupos</p>
                                </div>
                                <div>
                                <p class="mb-1 h5"><%= size %></p>
                                <p class="small text-muted mb-0">Posts</p>
                                </div>
                            </div>
                        </div>
                        <div class="card-body p-4 text-black">
                            <div class="mb-5">
                                <p class="lead fw-normal mb-1">Posts</p>
                                <div class="p-4">
                                    <%
                                    if (list != null){
                                    for (Tablon tab : list){
                                    %>
                                        <div class="row post mt-2 mb-2 pt-2 pb-2">
                                            <div class="row col-12 datainfo">
                                                <div class="sublink">
                                                    <a href="/post/<%=tab.getId() %>" class="mainlink"><%=tab.getIdUsuario().getUsername()%></a>
                                                    <a href="/post/<%=tab.getId() %>" >@<%=tab.getIdUsuario().getUsername()%></a>
                                                    <spam>·<spam>
                                                    <a href="/post/<%=tab.getId()%>" title="<%= tab.getCreateAt() %>"><%= tab.timeAgo() %></a>
                                                </div>
                                            </div>
                                            <div class="col-12 message">
                                                <%= tab.getMessage() %>
                                            </div>
                                            <div class="col-12 sublink">
                                                <a id="<%= tab.getId() %>" href="">❤️<%= tab.getLikes() %></a>
                                            </div>
                                        </div>
                                    <% } } else { %>
                                        <div>No hay nada que mostar</div>
                                    <% } %>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>
    </body>
    <%@ include file="../parts/footer.jsp" %>
</html>