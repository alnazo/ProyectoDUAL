package com.dual.proyectoDUAL.web.servlet.grupoController;

import com.dual.proyectoDUAL.dao.GrupoDAO;
import com.dual.proyectoDUAL.dto.Grupo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "Grupos", urlPatterns = "/grupos")
public class GrupoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Grupo> grupos = new GrupoDAO().getAll();

        String servicio = req.getParameter("servicio");
        String plan = req.getParameter("plan");



        req.getRequestDispatcher("/grupos/index.jsp").forward(req, resp);
    }
}
