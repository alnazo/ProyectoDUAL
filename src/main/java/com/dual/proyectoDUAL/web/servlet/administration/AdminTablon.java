package com.dual.proyectoDUAL.web.servlet.administration;

import com.dual.proyectoDUAL.dao.TablonDAO;
import com.dual.proyectoDUAL.dao.UsuarioDAO;
import com.dual.proyectoDUAL.dto.Tablon;
import com.dual.proyectoDUAL.dto.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name="adminTablon", urlPatterns="/admin/tablon")
public class AdminTablon extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            List<Tablon> tablon = new TablonDAO().findAll();

            req.getSession().setAttribute("tablon", tablon);
            req.getRequestDispatcher("/administracion/tablon.jsp").forward(req, resp);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idPost= Integer.parseInt(req.getParameter("id"));
        Tablon post = new TablonDAO().findById(idPost);

        new TablonDAO().update(post);

        resp.sendRedirect("/admin/tablon");
    }
}
