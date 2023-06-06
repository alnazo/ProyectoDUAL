package com.dual.proyectoDUAL.web.servlet.administration;

import com.dual.proyectoDUAL.dao.TablonDAO;
import com.dual.proyectoDUAL.dao.UsuarioDAO;
import com.dual.proyectoDUAL.dto.Tablon;
import com.dual.proyectoDUAL.dto.Usuario;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name="adminUsuario", urlPatterns="/admin/usuario")
public class AdminUsuario extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            List<Usuario> usuarios = new UsuarioDAO().findAll();

            req.getSession().setAttribute("usuario", usuarios);
            req.getRequestDispatcher("/administracion/usuario.jsp").forward(req, resp);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}