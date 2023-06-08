<%@ page pageEncoding="UTF-8" %>
<%@ page import="com.dual.proyectoDUAL.dto.Usuario" %>
<%@ page import="com.dual.proyectoDUAL.dto.Tablon" %>
<%@ page import="java.util.List" %>
<%
ServletContext context = (ServletContext) session.getAttribute("servletContext");
Usuario user = (Usuario) session.getAttribute("userSearch");
Usuario userSesion = (Usuario) session.getAttribute("usuarioSesion");
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
                                <% }
                                if (userSesion != null) {
                                if (user.getId() == userSesion.getId()) { %>
                                    <button type="button" class="btn btn-outline-dark" data-mdb-ripple-color="dark" style="z-index: 1;" data-bs-toggle="modal" data-bs-target="#editarPerfil">
                                        Edit profile
                                    </button>

                                    <div class="modal fade" id="editarPerfil" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="editarPerfilLabel" aria-hidden="true">
                                      <div class="modal-dialog">
                                        <div class="modal-content">
                                        <form action="/user/@<%= user.getUsername() %>" method="POST" enctype="multipart/form-data">
                                          <div class="modal-header">
                                            <h1 class="modal-title fs-5" id="editarPerfilLabel">Editar perfil</h1>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                          </div>
                                          <div class="modal-body">
                                            <div class="mb-3">
                                                <label class="form-label" for="pass">Contraseña nueva:</label>
                                                <input type="password" class="form-control" name="pass"/>
                                            </div>
                                            <div class="mb-3">
                                                <label class="form-label" for="pass2">Repetir contraseña:</label>
                                                <input type="password" class="form-control" name="pass2"/>
                                            </div>
                                            <div class="row mb-3 justify-content-between">
                                                <label class="form-label" for="img">Cambiar imagen perfil:</label>
                                                <div class="col-auto">
                                                    <input type="file" accept="image/*" class="form-control" name="img"/>
                                                </div>
                                                <div class="col-auto">
                                                    <input type="button" class="btn btn-danger" value="Borrar">
                                                </div>
                                                <div id="fileHelp" class="form-text">Solo se admiten imagenes.</div>
                                            </div>
                                          </div>
                                          <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                                            <button type="submit" class="btn btn-primary">Guardar cambios</button>
                                          </div>
                                        </div>
                                        </form>
                                      </div>
                                    </div>
                                <% } }%>
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
                                            <div class="row col-12 datainfo justify-content-between">
                                                <div class="sublink col-8">
                                                    <a href="/post/<%=tab.getId() %>" class="mainlink"><%=tab.getIdUsuario().getUsername()%></a>
                                                    <a href="/post/<%=tab.getId() %>" >@<%=tab.getIdUsuario().getUsername()%></a>
                                                    <spam>·<spam>
                                                    <a href="/post/<%=tab.getId()%>" title="<%= tab.getCreateAt() %>"><%= tab.timeAgo() %></a>
                                                </div>
                                                <div class="row col-1 text-end">
                                                    <div class="col-1">
                                                        <a class="copy" id="copy" onclick="copyLink('/post/<%=tab.getId() %>')" title="Copiar enlace"><i class="fas fa-share fa-sm"></i></a>
                                                    </div>
                                                <%
                                                if (userSesion != null){
                                                    if(tab.getIdUsuario().getId() == userSesion.getId()){
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
                                                <button class="btn" type="button" id="like-post-<%= tab.getId() %>" onclick="like('like-post-<%= tab.getId() %>')">❤️<%= tab.getLikes() %></button>
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
    <script src="/js/tablon.js"></script>
</html>