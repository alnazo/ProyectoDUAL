<%@ page pageEncoding="UTF-8" %>
<%@ page import="com.dual.proyectoDUAL.dto.Tablon" %>
<%@ page import="java.util.Set" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <%@ include file="/parts/meta.jsp" %>
        <title>ProyectoDUAL</title>
        <%@ include file="/parts/header.jsp" %>
    </head>
    <body class="container-fluid p-0">
        <%@ include file="/parts/nav.jsp" %>
        <div class="container">
            <div class="row">
                <div class="col-md-2 text-center">
                    SERVICIOS
                </div>
                <div class="col-md-8">
                    <%
                    if(session.getAttribute("tablon")!=null){
                        Set<Tablon> list = (Set<Tablon>)session.getAttribute("tablon");
                        for (Tablon tab : list){
                    %>
                        <div class="post row">
                           <div class="col-2 imgperfil">
                               <img src="" alt="imgprerfil" class="perfilimg"/>
                           </div>
                           <div class="row col-10">
                               <div class="row col-12 datainfo">
                                   <div class="sublink">
                                       <a href="" class="mainlink"><%=tab.getId_user().getUsername()%></a>
                                       <a href="">@<%=tab.getId_user().getUsername()%></a>
                                       <spam>·<spam>
                                       <a href=""><%= tab.getCreatedAt() %></a>
                                   </div>
                               </div>
                               <div class="col-12 message">
                                   <%= tab.getMensaje() %>
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
                    OTROS
                </div>
            </div>
        </div>
        <%@ include file="/parts/footer.jsp" %>
    </body>
</html>