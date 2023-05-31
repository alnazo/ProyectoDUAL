package com.dual.proyectoDUAL.web.servlet;


import com.dual.proyectoDUAL.dao.TablonDAO;
import com.dual.proyectoDUAL.dao.UsuarioDAO;
import com.dual.proyectoDUAL.dto.Tablon;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "Index", urlPatterns = {"/home", ""})
public class Index extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        //Forzado de que exista un usuario
        req.getSession().setAttribute("usuarioSesion", new UsuarioDAO().getUsuario(4));
        //Forzado de que exista un usuario

        ServletContext servletContext = getServletContext();
        req.getSession().setAttribute("servletContext" ,servletContext);
        List<Tablon> tablon = new TablonDAO().findAll();
        req.getSession().setAttribute("tablon", tablon);

        req.getRequestDispatcher("/index.jsp").forward(req, res);
    }
}