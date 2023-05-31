<%@ page pageEncoding="UTF-8" %>
<%@ page import="com.dual.proyectoDUAL.dto.Usuario" %>
<%@ page import="com.dual.proyectoDUAL.dto.Tablon" %>
<%@ page import="java.util.List" %>
<%
ServletContext context = (ServletContext) session.getAttribute("servletContext");
Usuario use = (Usuario) session.getAttribute("userSearch");
List<Tablon> list = (List<Tablon>)session.getAttribute("userTablon");
%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <%@ include file="../parts/meta.jsp" %>
        <title>Perfil de @<%=use.getUsername()%></title>
        <%@ include file="../parts/header.jsp" %>
    </head>
    <body>
    <div class="container-fluid">
        <div class="row">
            <div class="col-2 menu">
                <%@ include file="../parts/nav.jsp" %>
            </div>
            <div class="col-10 content">

            </div>
        </div>
    </div>
    </body>
    <%@ include file="../parts/footer.jsp" %>
</html>