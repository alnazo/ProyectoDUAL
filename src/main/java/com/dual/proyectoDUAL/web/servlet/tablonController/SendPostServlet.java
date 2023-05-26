package com.dual.proyectoDUAL.web.servlet.tablonController;

import com.dual.proyectoDUAL.dao.TablonDAO;
import com.dual.proyectoDUAL.dto.Tablon;
import com.dual.proyectoDUAL.dto.Usuario;
import com.dual.proyectoDUAL.web.notifications.Notificaciones;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "Send_Post", urlPatterns = "/sendPost")
public class SendPostServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String mensaje = req.getParameter("mensaje");
        Usuario user = (Usuario) req.getSession().getAttribute("usuarioSesion");
        if (mensaje.trim().length() != 0) {
            Tablon tab = new Tablon(mensaje, user);
            Tablon envio = new TablonDAO().send(tab);

            if (envio != null) {
                resp.sendRedirect("/home");
            } else {
                Notificaciones.error = true;
                Notificaciones.msg = "Error, ocurrio un problema al enviar el mensaje.";
                resp.sendRedirect("/home");
            }
        } else {
            Notificaciones.error = true;
            Notificaciones.msg = "Error, no se puede enviar el mensaje vacio.";
            resp.sendRedirect("/home");
        }

    }
}
