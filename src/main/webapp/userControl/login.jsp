<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <%@ include file="../parts/meta.jsp" %>
        <title>ProyectoDUAL</title>
        <%@ include file="../parts/header.jsp" %>
    </head>
    <body class="container-fluid row">
        <%@ include file="../parts/nav.jsp" %>
        <div class="container text-center col-md-6">
            <form action="login" method="POST">
              <div class="mb-3">
                <label for="email" class="form-label">Email address</label>
                <input type="email" class="form-control" id="email" name="email">
              </div>
              <div class="mb-3">
                <label for="password" class="form-label">Password</label>
                <input type="password" class="form-control" id="password" name="password">
              </div>
              <div class="mb-3 form-check">
                <input type="checkbox" class="form-check-input" id="remember" name="remember">
                <label class="form-check-label" for="remember">Recordar</label>
              </div>
              <button type="submit" class="btn btn-success">Iniciar sesión</button>
            </form>
            <%if(request.getAttribute("error")!=null){%>
                <div style="color: red"><%=request.getAttribute("error")%></div>
            <%}%>
        </div>
        <%@ include file="../parts/footer.jsp" %>
    </body>
</html>