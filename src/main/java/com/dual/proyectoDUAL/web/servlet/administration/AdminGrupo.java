package com.dual.proyectoDUAL.web.servlet.administration;

import com.dual.proyectoDUAL.dao.GrupoDAO;
import com.dual.proyectoDUAL.dao.ServicioDAO;
import com.dual.proyectoDUAL.dao.UsuarioDAO;
import com.dual.proyectoDUAL.dto.Grupo;
import com.dual.proyectoDUAL.dto.Servicio;
import com.dual.proyectoDUAL.dto.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name="adminGrupo", urlPatterns="/admin/grupo")
public class AdminGrupo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            List<Grupo> grupos = new GrupoDAO().getAll();

            req.getSession().setAttribute("grupo", grupos);
            req.getRequestDispatcher("/administracion/grupo.jsp").forward(req, resp);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
