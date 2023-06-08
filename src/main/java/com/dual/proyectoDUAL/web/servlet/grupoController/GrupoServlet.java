package com.dual.proyectoDUAL.web.servlet.grupoController;

import com.dual.proyectoDUAL.dao.GrupoDAO;
import com.dual.proyectoDUAL.dao.ServicioDAO;
import com.dual.proyectoDUAL.dto.Grupo;
import com.dual.proyectoDUAL.dto.Servicio;
import com.dual.proyectoDUAL.dto.Usuario;
import jakarta.servlet.ServletContext;
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
        ServletContext servletContext = getServletContext();
        req.getSession().setAttribute("servletContext" ,servletContext);

        List<Grupo> grupos = new GrupoDAO().getAll();
        List<Servicio> servicios = new ServicioDAO().getAll();
        if(servicios != null) {
            Map<String, List<Servicio>> serviciosGruped = servicios.stream().collect(Collectors.groupingBy(Servicio::getNombre));
            req.getSession().setAttribute("serviciosGruped", serviciosGruped);
        } else {
            req.getSession().setAttribute("serviciosGruped", null);
        }

        if(req.getParameter("servicio")!= null && req.getParameter("plan") != null){
            String servicio = req.getParameter("servicio");
            if(servicio.contains(" ")){
                servicio = servicio.replace(" ", "+");
            }
            String plan = req.getParameter("plan");

            Servicio ser = new ServicioDAO().findBySP(servicio, plan);
            if(ser != null) {
                grupos = grupos.stream().filter(e -> e.getServicio().getNombre().equals(ser.getNombre()) && e.getServicio().getPlan().equals(ser.getPlan())).toList();
            }
        }

        req.getSession().setAttribute("grupos", grupos);

        req.getRequestDispatcher("/grupos/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("servicioModal") != null && req.getParameter("planModal") != null){
            req.getRequestDispatcher("/grupo/crear").forward(req, resp);
        } else {
            Usuario usersession = (Usuario) req.getSession().getAttribute("usuarioSesion");
            Grupo gp = new GrupoDAO().getById(Integer.parseInt(req.getParameter("gr")));
            int position = Integer.parseInt(req.getParameter("ps"));

            switch (position) {
                case 2:
                    gp.setUser2(usersession);
                    new GrupoDAO().update(gp);
                    break;
                case 3:
                    gp.setUser3(usersession);
                    new GrupoDAO().update(gp);
                    break;
                case 4:
                    gp.setUser4(usersession);
                    new GrupoDAO().update(gp);
                    break;
                case 5:
                    gp.setUser5(usersession);
                    new GrupoDAO().update(gp);
                    break;
                case 6:
                    gp.setUser6(usersession);
                    new GrupoDAO().update(gp);
                    break;
                case 7:
                    gp.setUser7(usersession);
                    new GrupoDAO().update(gp);
                    break;
                case 8:
                    gp.setUser8(usersession);
                    new GrupoDAO().update(gp);
                    break;
            }

            resp.sendRedirect("/grupos");
        }
    }
}
