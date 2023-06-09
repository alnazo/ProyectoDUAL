package com.dual.proyectoDUAL.web.servlet.tablonController;


import com.dual.proyectoDUAL.dao.TablonDAO;
import com.dual.proyectoDUAL.dto.Tablon;
import com.dual.proyectoDUAL.dto.Usuario;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Objects;

@WebServlet(name = "Post", urlPatterns = "/post/*")
public class TablonServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        req.getSession().setAttribute("servletContext", servletContext);

        String pathInfo = req.getPathInfo();
        if (pathInfo != null && pathInfo.length() > 1) {
            String id = pathInfo.substring(1);
            try {
                int idInt = Integer.parseInt(id);
                Tablon tab = new TablonDAO().findById(idInt);

                req.getSession().setAttribute("tablon", tab);

                req.getRequestDispatcher("/post.jsp").forward(req, resp);
            } catch (NumberFormatException e) {
                resp.sendRedirect("/");
            }
        } else {
            resp.sendRedirect("/");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getPathInfo().split("/")[2]);
        Tablon tab = new TablonDAO().findById(id);
        Usuario user = (Usuario) req.getSession().getAttribute("usuarioSesion");
        if (user != null) {
            if (Objects.equals(tab.getIdUsuario().getId(), user.getId())) {
                new TablonDAO().delete(tab);
            }
        }
        resp.sendRedirect("/");
    }

}
