package com.dual.proyectoDUAL.servlet.userController;

import com.dual.proyectoDUAL.dto.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="Login", urlPatterns = "/login")
public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/userControl/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Usuario usuario = (Usuario) req.getSession().getAttribute("usuarioSesion");

        if (usuario != null) {
            homePage(resp, usuario);
        } else {
            String emailConfigurado=getServletContext().getInitParameter("email");
            String passwordConfigurado=getServletContext().getInitParameter("password");

            String emailIntroducido = req.getParameter("email");
            String passwordIntroducido = req.getParameter("password");
            String rememberSession = req.getParameter("remember");

            if ((emailIntroducido != null && emailIntroducido.equals(emailConfigurado)) && (passwordIntroducido != null && passwordIntroducido.equals(passwordConfigurado))) {
                usuario = Usuario.builder().email(emailIntroducido).password(passwordIntroducido).build();

                if(rememberSession != null){
                    req.getSession().setMaxInactiveInterval(-1);
                } else {
                    req.getSession().setMaxInactiveInterval(Integer.parseInt(req.getServletContext().getInitParameter("sessionTimeout")));
                }
                req.getSession().setAttribute("usuarioSesion", usuario);
                resp.sendRedirect("home");
            }
            else {
                req.setAttribute("error","Error al insertar el correo o la contraseña");
                req.getRequestDispatcher("/userControl/login.jsp").forward(req, resp);
            }
        }

    }

    private void homePage(HttpServletResponse resp, Usuario usuario) throws IOException {

    }


}
