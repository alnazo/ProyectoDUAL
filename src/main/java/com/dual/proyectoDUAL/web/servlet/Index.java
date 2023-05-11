package com.dual.proyectoDUAL.web.servlet;


import com.dual.proyectoDUAL.dao.UsuarioDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name="Index", urlPatterns = {"/home"})
public class Index extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        //Forzado de que exista un usuario
        req.getSession().setAttribute("usuarioSesion", new UsuarioDAO().getUsuario(1));
        //Forzado de que exista un usuario

        req.getRequestDispatcher("/index.jsp").forward(req, res);
    }
}