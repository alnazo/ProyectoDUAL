package com.dual.proyectoDUAL.web.servlet.userController;


import com.dual.proyectoDUAL.dao.TablonDAO;
import com.dual.proyectoDUAL.dao.UsuarioDAO;
import com.dual.proyectoDUAL.dto.Tablon;
import com.dual.proyectoDUAL.dto.Usuario;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "Usuario", urlPatterns = "/user/*")
public class UserPerfil extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        req.getSession().setAttribute("servletContext", servletContext);

        String pathInfo = req.getPathInfo();
        if (pathInfo != null && pathInfo.length() > 1) {
            String name = pathInfo.substring(2);
            try {
                Usuario call = new UsuarioDAO().findByNombreExacto(name);
                List<Tablon> mens = new TablonDAO().findByUserId(call.getId());
                req.getSession().setAttribute("userSearch", call);
                req.getSession().setAttribute("userTablon", mens);

                req.getRequestDispatcher("/userControl/perfil.jsp").forward(req, resp);
            } catch (NumberFormatException e) {
                resp.sendRedirect("/");
            }
        } else {
            resp.sendRedirect("/");
        }
    }
}

