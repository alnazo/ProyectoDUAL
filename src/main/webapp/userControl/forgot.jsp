<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <%@ include file="../parts/meta.jsp" %>
        <title>Recordar contraseña - Sharefy</title>
        <%@ include file="../parts/header.jsp" %>
    </head>
    <body class="container-fluid p-0">
        <div class="container-fluid">
            <div class="row">
                <div class="col-2 menu">
                    <%@ include file="../parts/nav.jsp" %>
                </div>
                <div class="col-10 content">
                    <form action="forgot" method="POST">
                        <div class="mb-3">
                            <label for="email" class="form-label">Introduzca su email:</label>
                            <input type="email" class="form-control" id="email" name="email">
                        </div>
                        <button type="submit" class="btn btn-primary">Pedir contraseña nueva</button>
                    </form>
                </div>
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