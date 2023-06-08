<%@ page pageEncoding="UTF-8" %>
<%
    ServletContext context = (ServletContext) session.getAttribute("servletContext");
%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <%@ include file="../parts/meta.jsp" %>
        <title>Registro - Sharefy</title>
        <%@ include file="../parts/header.jsp" %>
    </head>
    <body>
        <div class="container-fluid">
            <div class="row">
                <div class="col-2 menu">
                    <%@ include file="../parts/nav.jsp" %>
                </div>
                <div class="col-10 content">
                    <form action="registro" method="POST">
                        <div class="mb-3">
                            <label for="usuario" class="form-label">Nombre de usuario:</label>
                            <div class="input-group">
                                <div class="input-group-text">@</div>
                                <input type="text" class="form-control" id="usuario" name="usuario">
                            </div>
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
                        <button type="submit" class="btn btn-primary">Registro</button>
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