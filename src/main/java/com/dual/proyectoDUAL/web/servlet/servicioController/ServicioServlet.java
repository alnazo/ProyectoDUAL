package com.dual.proyectoDUAL.web.servlet.servicioController;

import com.dual.proyectoDUAL.dao.ServicioDAO;
import com.dual.proyectoDUAL.dto.Servicio;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "Servicios", urlPatterns = "/servicios")
public class ServicioServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Servicio> servicios = new ServicioDAO().getAll();
        req.getSession().setAttribute("servicios", servicios);

        req.getRequestDispatcher("/servicios/index.jsp").forward(req, resp);
    }
}
