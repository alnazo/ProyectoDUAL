package com.dual.proyectoDUAL.web.servlet.tablonController;


import com.dual.proyectoDUAL.dao.TablonDAO;
import com.dual.proyectoDUAL.dto.Tablon;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "Post", urlPatterns = "/post/*")
public class TablonServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        req.getSession().setAttribute("servletContext" ,servletContext);

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
}
