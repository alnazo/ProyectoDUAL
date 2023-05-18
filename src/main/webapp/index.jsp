<%@ page pageEncoding="UTF-8" %>
<%@ page import="com.dual.proyectoDUAL.dto.Tablon" %>
<%@ page import="java.util.List" %>
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
                            if(session.getAttribute("tablon")!=null){
                                List<Tablon> list = (List<Tablon>)session.getAttribute("tablon");
                                for (Tablon tab : list){
                            %>
                                <div class="post row">
                                   <div class="col-2 imgperfil">
                                       <img src="<%= tab.getId_user().sourceImagen() %>" alt="imgprerfil" class="perfilimg"/>
                                   </div>
                                   <div class="row col-10">
                                       <div class="row col-12 datainfo">
                                           <div class="sublink">
                                               <a href="" class="mainlink"><%=tab.getId_user().getUsername()%></a>
                                               <a href="" >@<%=tab.getId_user().getUsername()%></a>
                                               <spam>·<spam>
                                               <a href="" title="<%= tab.getCreatedAt() %>"><%= tab.timeAgo() %></a>
                                           </div>
                                       </div>
                                       <div class="col-12 message">
                                           <%= tab.getMensaje() %>
                                       </div>
                                       <div class="col-12 sublink">
                                            <a href="">❤️<%= tab.getLikes() %></a>
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
    </body>
    <%@ include file="/parts/footer.jsp" %>
</html>