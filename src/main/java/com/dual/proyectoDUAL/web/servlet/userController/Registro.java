package com.dual.proyectoDUAL.web.servlet.userController;

import com.dual.proyectoDUAL.dto.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet(name = "Registro", urlPatterns = "/registro")
public class Registro extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/userControl/registro.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Usuario usuario = (Usuario) req.getSession().getAttribute("usuarioSesion");
        if (usuario != null) {
            resp.sendRedirect("home");
        } else {
            String usuarioIntroducido = req.getParameter("usuario");
            String passwordIntroducido = req.getParameter("password");
            String emailIntroducido = req.getParameter("email");
            String nacimientoIntroducido = req.getParameter("nacimiento");

            if(usuarioIntroducido.isEmpty() || passwordIntroducido.isEmpty() || emailIntroducido.isEmpty() || nacimientoIntroducido.isEmpty()){
                req.setAttribute("error", "Error, faltan datos por rellenar.");
                req.getRequestDispatcher("/userControl/registro.jsp").forward(req, resp);
            }else if (passwordIntroducido.equals(req.getParameter("confirm_password"))) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate nacimientoIntroducidoFormat = LocalDate.parse(nacimientoIntroducido, formatter);

                Usuario newUser = new Usuario(1, usuarioIntroducido, passwordIntroducido, emailIntroducido, Date.valueOf(nacimientoIntroducidoFormat), 0);
                //TODO -- send newUser to DB

            } else {
                req.setAttribute("error", "Error, las contraseñas no coinciden.");
                req.getRequestDispatcher("/userControl/registro.jsp").forward(req, resp);
            }
        }
    }
}
