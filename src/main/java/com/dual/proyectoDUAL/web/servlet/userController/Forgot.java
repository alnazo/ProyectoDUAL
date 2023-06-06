package com.dual.proyectoDUAL.web.servlet.userController;


import com.dual.proyectoDUAL.dao.UsuarioDAO;
import com.dual.proyectoDUAL.dao.email.sender.Sender;
import com.dual.proyectoDUAL.dto.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "Forgot", urlPatterns = "/forgot")
public class Forgot extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/userControl/forgot.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String emailIntroducido = req.getParameter("email");

        if (emailIntroducido.isEmpty()) {
            req.setAttribute("error", "Error, no has rellenado el correo.");
            req.getRequestDispatcher("/userControl/forgot.jsp").forward(req, resp);
        } else {
            Usuario user = new UsuarioDAO().findByEmail(emailIntroducido);
            if (user != null) {
                //TODO -- revisar funcionamiento correcto
                Sender sender = new Sender();
                sender.send("1234solojuegos@gmail.com", user.getEmail(), "Contraseña olvidada",
                        "Ha olvidado su contraseña, necesita volver a loggearse", "");

                resp.sendRedirect("/login");
            } else {
                req.setAttribute("error", "Error, no existe usuario con ese email.");
                req.getRequestDispatcher("/userControl/forgot.jsp").forward(req, resp);
            }
        }
    }
}