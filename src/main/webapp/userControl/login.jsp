<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <%@ include file="../parts/meta.jsp" %>
        <title>Inicio de sesión - ProyectoDUAL</title>
        <%@ include file="../parts/header.jsp" %>
    </head>
    <body class="container-fluid p-0">
        <%@ include file="../parts/nav.jsp" %>
        <div class="container text-center col-md-6">
            <form action="login" method="POST">
              <div class="mb-3">
                <label for="email" class="form-label">Email:</label>
                <input type="email" class="form-control" id="email" name="email">
              </div>
              <div class="mb-3">
                <label for="password" class="form-label">Contraseña:</label>
                <input type="password" class="form-control" id="password" name="password">
              </div>
              <div class="mb-3 form-check">
                <input type="checkbox" class="form-check-input" id="remember" name="remember">
                <label class="form-check-label" for="remember">Recordar</label>
              </div>
              <button type="submit" class="btn btn-primary">Iniciar sesión</button>
            </form>
            <div class="mt-2">
              <a class="link" href="forgot"><button type="button" class="btn">¿Has perdido tu cotraseña?</button></a>
            </div>
        </div>

        <%if(request.getAttribute("error")!=null){%>
            <div class="toast-container position-fixed bottom-0 end-0 p-3">
              <div class="toast fade show" role="alert" aria-live="assertive" aria-atomic="true">
                <div class="toast-header">
                  <svg class="bd-placeholder-img rounded me-2" width="20" height="20" xmlns="http://www.w3.org/2000/svg" aria-hidden="true" preserveAspectRatio="xMidYMid slice" focusable="false"><rect width="100%" height="100%" fill="#DF1C44"></rect></svg>
                  <strong class="me-auto">Error</strong>
                  <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
                </div>
                <div class="toast-body">
                    <div id="errorMsg"><%=request.getAttribute("error")%></div>
                </div>
              </div>
            </div>
        <%}%>
    </body>
    <%@ include file="../parts/footer.jsp" %>
</html>