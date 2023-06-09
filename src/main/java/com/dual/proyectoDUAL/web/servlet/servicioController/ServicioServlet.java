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
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet(name = "Servicios", urlPatterns = "/servicios")
public class ServicioServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Servicio> servicios = new ServicioDAO().getAll();
        Map<String, List<Servicio>> serviciosGruped = servicios.stream().collect(Collectors.groupingBy(e -> e.getNombre()));
        req.getSession().setAttribute("serviciosGruped", serviciosGruped);

        String name = req.getParameter("servicio");
        String min = req.getParameter("min");
        String max = req.getParameter("max");
        if (name != null && min != null && max != null) {
            if (!name.equals("") || (!min.equals("") && !max.equals(""))) {
                if (!name.equals("")) {
                    servicios = new ServicioDAO().getByName(name);
                    Double mind = Double.parseDouble(min);
                    Double maxd = Double.parseDouble(max);
                    servicios = servicios.stream().filter(e -> e.getPrecio() >= mind && e.getPrecio() <= maxd).toList();
                } else {
                    Double mind = Double.parseDouble(min);
                    Double maxd = Double.parseDouble(max);
                    servicios = servicios.stream().filter(e -> e.getPrecio() >= mind && e.getPrecio() <= maxd).toList();
                }
            }
        }
        req.getSession().setAttribute("servicios", servicios);
        req.getRequestDispatcher("/servicios/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Servicio servicio = new ServicioDAO().findById(Integer.parseInt(req.getParameter("id")));

        new ServicioDAO().delete(servicio);

        resp.sendRedirect("/admin/servicio");
    }

}
