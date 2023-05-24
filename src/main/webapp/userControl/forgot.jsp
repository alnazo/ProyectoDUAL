<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <%@ include file="../parts/meta.jsp" %>
        <title>Recordar contraseña - ProyectoDUAL</title>
        <%@ include file="../parts/header.jsp" %>
    </head>
    <body class="container-fluid p-0">
        <%@ include file="../parts/nav.jsp" %>
        <div class="container text-center col-md-6">
            <form action="forgot" method="POST">
              <div class="mb-3">
                <label for="email" class="form-label">Introduzca su email:</label>
                <input type="email" class="form-control" id="email" name="email">
              </div>
              <button type="submit" class="btn btn-primary">Pedir contraseña nueva</button>
            </form>
            <%if(request.getAttribute("error")!=null){%>
                <div style="color: red"><%=request.getAttribute("error")%></div>
            <%}%>
        </div>
    </body>
    <%@ include file="../parts/footer.jsp" %>
</html>