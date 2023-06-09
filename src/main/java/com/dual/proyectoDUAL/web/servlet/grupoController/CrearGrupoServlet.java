package com.dual.proyectoDUAL.web.servlet.grupoController;


import com.dual.proyectoDUAL.dao.GrupoDAO;
import com.dual.proyectoDUAL.dao.ServicioDAO;
import com.dual.proyectoDUAL.dto.Grupo;
import com.dual.proyectoDUAL.dto.Servicio;
import com.dual.proyectoDUAL.dto.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "Crear Grupo", urlPatterns = "/grupo/crear")
public class CrearGrupoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String servicio = req.getParameter("servicioModal");
        String plan = req.getParameter("planModal");
        Usuario usersession = (Usuario)req.getSession().getAttribute("usuarioSesion");

        Servicio serv = new ServicioDAO().findBySP(servicio, plan);
        Grupo grupo = new Grupo(0, serv, usersession, null, null, null, null, null, null, null);
        new GrupoDAO().create(grupo);

        resp.sendRedirect("/grupos");

    }
<<<<<<< HEAD
}
=======
}
>>>>>>> main
