package com.dual.proyectoDUAL.web.servlet.userController;


import com.dual.proyectoDUAL.dao.UsuarioDAO;
import com.dual.proyectoDUAL.dto.Usuario;
import com.dual.proyectoDUAL.mail.Sender;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Random;

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
                String passnew = randomPassGenerator();
                user.setPassword(passnew);
                new UsuarioDAO().update(user);
                new Sender().send("sharefy@sharefy.com", user.getEmail(), "Recuperacion de contraseña", "Se le ha generado una contraseña aleatoria para su proximo inicio de sesion. \nEs la siguiente: "+ passnew, "");

                resp.sendRedirect("/login");
            } else {
                req.setAttribute("error", "Error, no existe usuario con ese email.");
                req.getRequestDispatcher("/userControl/forgot.jsp").forward(req, resp);
            }
        }
    }

    private String randomPassGenerator() {
        Random ran = new Random();
        int top = 6;
        char data = ' ';
        String dat = "";

        for (int i=0; i<=top; i++) {
            data = (char)(ran.nextInt(25)+97);
            dat = data + dat;
        }
        return dat;
    }

}
