package com.dual.proyectoDUAL.web.servlet.userController;

import com.dual.proyectoDUAL.dao.UsuarioDAO;
import com.dual.proyectoDUAL.dto.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

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

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-uuuu", Locale.UK);
        LocalDate date = LocalDate.parse(nacimientoIntroducido, formatter);

        Usuario existEmail = new UsuarioDAO().findByEmail(emailIntroducido);
        Usuario existUsu = new UsuarioDAO().findByNombreExacto(usuarioIntroducido);

        if (existEmail != null) {
            req.setAttribute("error", "Error, ya existe un usuario con ese correo.");
            req.getRequestDispatcher("/userControl/registro.jsp").forward(req, resp);
        } else if (existUsu != null) {
            req.setAttribute("error", "Error, ya existe un usuario con ese nombre de usuario.");
            req.getRequestDispatcher("/userControl/registro.jsp").forward(req, resp);
        } else {
            if (usuarioIntroducido.isEmpty() || passwordIntroducido.isEmpty() || emailIntroducido.isEmpty() || nacimientoIntroducido.isEmpty()) {
                req.setAttribute("error", "Error, faltan datos por rellenar.");
                req.getRequestDispatcher("/userControl/registro.jsp").forward(req, resp);
            } else if (passwordIntroducido.equals(req.getParameter("confirm_password"))) {
                Usuario newUser = new Usuario(0, usuarioIntroducido, passwordIntroducido, emailIntroducido, null, date, Boolean.FALSE);
                Usuario registro = new UsuarioDAO().register(newUser);

                if (registro != null) {
                    req.getRequestDispatcher("/userControl/login.jsp").forward(req, resp);
                } else {
                    req.setAttribute("error", "Error, al crear usuario, si el error persiste, por favor contactenos.");
                    req.getRequestDispatcher("/userControl/registro.jsp").forward(req, resp);
                }
            } else {
                req.setAttribute("error", "Error, las contraseñas no coinciden.");
                req.getRequestDispatcher("/userControl/registro.jsp").forward(req, resp);
            }
        }
    }
}