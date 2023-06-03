package com.dual.proyectoDUAL.web.servlet.mailController;

import com.dual.proyectoDUAL.dto.Usuario;
import com.dual.proyectoDUAL.email.sender.Sender;
import com.dual.proyectoDUAL.itext.PdfItext;
import com.itextpdf.text.DocumentException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URISyntaxException;

@WebServlet (name = "SendMail", urlPatterns = {"/send"})
public class MailController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Usuario usuario = (Usuario) request.getSession().getAttribute("sesionDeUsuario");
        String emailTo = request.getParameter("email");
        String pdf = "";
        String nombre = request.getParameter("username");
        String emailFrom = "1234solojuegos@gmail.com";
        try {
            new PdfItext().createPDF(pdf, nombre, emailFrom, "Gracias por registrarse en nuestra aplicación");
            new Sender().send(emailFrom,emailTo, "Mensaje enviado", "Usuario registrado", pdf);

            request.setAttribute("notificacion", "Mensaje enviado con exito");
            request.getRequestDispatcher("");

        } catch (DocumentException | URISyntaxException e) {
            e.printStackTrace();
            //response.sendRedirect("/error.jsp");
        }
    }
}