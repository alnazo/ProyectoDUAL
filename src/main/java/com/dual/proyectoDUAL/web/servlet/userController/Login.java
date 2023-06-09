package com.dual.proyectoDUAL.web.servlet.userController;

import com.dual.proyectoDUAL.dao.UsuarioDAO;
import com.dual.proyectoDUAL.dto.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "Login", urlPatterns = "/login")
public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/userControl/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String emailIntroducido = req.getParameter("email");
        String passwordIntroducido = req.getParameter("password");
        String rememberSession = req.getParameter("remember");

        Usuario getUser = new UsuarioDAO().findByEmail(emailIntroducido);

        if (getUser != null) {
            if (getUser.getPassword().equals(passwordIntroducido)) {
                if (rememberSession != null) {
                    req.getSession().setMaxInactiveInterval(-1);
                } else {
                    req.getSession().setMaxInactiveInterval(Integer.parseInt(req.getServletContext().getInitParameter("sessionTimeout")));
                }
                req.getSession().setAttribute("usuarioSesion", getUser);
                resp.sendRedirect("home");
            } else {
                req.setAttribute("error", "Error al insertar la contraseña");
                req.getRequestDispatcher("/userControl/login.jsp").forward(req, resp);
            }
        } else {
            req.setAttribute("error", "Error, el correo introducido no existe");
            req.getRequestDispatcher("/userControl/login.jsp").forward(req, resp);
        }
    }

}
