package com.dual.proyectoDUAL.web.servlet.userController;

import com.dual.proyectoDUAL.dto.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "Registro", urlPatterns = "/registro")
public class Registro extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/userControl/registro.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String usuarioIntroducido = req.getParameter("usuario");
        String passwordIntroducido = req.getParameter("password");
        String emailIntroducido = req.getParameter("email");
        String nacimientoIntroducido = req.getParameter("nacimiento");

        if(usuarioIntroducido.isEmpty() || passwordIntroducido.isEmpty() || emailIntroducido.isEmpty() || nacimientoIntroducido.isEmpty()){
            req.setAttribute("error", "Error, faltan datos por rellenar.");
            req.getRequestDispatcher("/userControl/registro.jsp").forward(req, resp);
        }else if (passwordIntroducido.equals(req.getParameter("confirm_password"))) {
            Date date = null;
            try {
                date = new SimpleDateFormat("yyyy-MM-dd").parse(nacimientoIntroducido.toString());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            Usuario newUser = new Usuario(1, usuarioIntroducido, passwordIntroducido, emailIntroducido, null, date, Boolean.FALSE);
            //TODO -- send newUser to DB

        } else {
            req.setAttribute("error", "Error, las contraseñas no coinciden.");
            req.getRequestDispatcher("/userControl/registro.jsp").forward(req, resp);
        }
    }
}
