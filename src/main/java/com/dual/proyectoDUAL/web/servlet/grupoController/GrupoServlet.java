package com.dual.proyectoDUAL.web.servlet.grupoController;

import com.dual.proyectoDUAL.dao.GrupoDAO;
import com.dual.proyectoDUAL.dao.ServicioDAO;
import com.dual.proyectoDUAL.dto.Grupo;
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

@WebServlet(name = "Grupos", urlPatterns = "/grupos")
public class GrupoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Grupo> grupos = new GrupoDAO().getAll();
        List<Servicio> servicios = new ServicioDAO().getAll();
        if(servicios != null) {
            Map<String, List<Servicio>> serviciosGruped = servicios.stream().collect(Collectors.groupingBy(e -> e.getNombre()));
            req.getSession().setAttribute("serviciosGruped", serviciosGruped);
        } else {
            req.getSession().setAttribute("serviciosGruped", null);
        }

        if(req.getParameter("servicio")!= null && req.getParameter("plan") != null){
            String servicio = req.getParameter("servicio");
            String plan = req.getParameter("plan");

            Servicio ser = new ServicioDAO().findBySP(servicio, plan);

        }

        req.getSession().setAttribute("grupos", grupos);

        req.getRequestDispatcher("/grupos/index.jsp").forward(req, resp);
    }
}
