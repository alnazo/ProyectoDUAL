<%@ page pageEncoding="UTF-8" %>
<%
    ServletContext context = (ServletContext) session.getAttribute("servletContext");
%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <%@ include file="../parts/meta.jsp" %>
        <title>Registro - ProyectoDUAL</title>
        <%@ include file="../parts/header.jsp" %>
    </head>
    <body class="container-fluid p-0">
        <%@ include file="../parts/nav.jsp" %>
        <div class="container text-center">
            <form action="registro" method="POST">
                <div class="mb-3">
                    <label for="usuario" class="form-label">Nombre de usuario:</label>
                    <input type="text" class="form-control" id="usuario" name="usuario">
                </div>
                <div class="mb-3">
                    <label for="email" class="form-label">Email:</label>
                    <input type="email" class="form-control" id="email" name="email">
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label">Password:</label>
                    <input type="password" class="form-control" id="password" name="password">
                </div>
                <div class="mb-3">
                    <label for="confirm_password" class="form-label">Repetir password:</label>
                    <input type="password" class="form-control" id="confirm_password" name="confirm_password">
                </div>
                <div class="mb-3">
                    <label for="nacimiento" class="form-label">Fecha de nacimiento:</label>
                    <div class="input-group date" id="nacimiento">
                        <input type="text" class="form-control" name="nacimiento" placeholder="Fecha de nacimiento" />
                        <span class="input-group-append">
                            <span class="input-group-text bg-light d-block">
                                <i class="fa fa-calendar"></i>
                            </span>
                        </span>
                    </div>
                </div>
                <%if(request.getAttribute("error")!=null){%>
                    <div style="color: red" class="mb-3"><%=request.getAttribute("error")%></div>
                <%}%>
                <button type="submit" class="btn btn-primary">Registro</button>
            </form>
        </div>
    </body>
    <%@ include file="../parts/footer.jsp" %>
</html>