package com.dual.proyectoDUAL.web.servlet.userController;


import com.dual.proyectoDUAL.dao.TablonDAO;
import com.dual.proyectoDUAL.dao.UsuarioDAO;
import com.dual.proyectoDUAL.dto.Tablon;
import com.dual.proyectoDUAL.dto.Usuario;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@WebServlet(name = "Usuario", urlPatterns = "/user/*")
@MultipartConfig
public class UserPerfil extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        req.getSession().setAttribute("servletContext", servletContext);

        String pathInfo = req.getPathInfo();
        if (pathInfo != null && pathInfo.length() > 1) {
            String name = pathInfo.substring(2);
            try {
                Usuario call = new UsuarioDAO().findByNombreExacto(name);
                List<Tablon> mens = new TablonDAO().findByUserId(call.getId());
                req.getSession().setAttribute("userSearch", call);
                req.getSession().setAttribute("userTablon", mens);

                req.getRequestDispatcher("/userControl/perfil.jsp").forward(req, resp);
            } catch (NumberFormatException e) {
                resp.sendRedirect("/");
            }
        } else {
            resp.sendRedirect("/");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Usuario user = (Usuario) req.getSession().getAttribute("usuarioSesion");

        String pass = req.getParameter("pass");
        String passC = req.getParameter("pass2");
        boolean password = pass.equals(passC);

        Part file = req.getPart("img");
        String fileName = Paths.get(file.getSubmittedFileName()).getFileName().toString();

        if(!pass.equals("") && password){
            user.setPassword(pass);
        }
        if(!fileName.equals("")){
            ServletContext servletContext = getServletContext();
            String path = servletContext.getRealPath("/img/usuarios/" + user.getId() + "/");
            File pathFiles = new File(path);

            if(new File(path+"/"+user.getImagen()).exists()){
                new File(path+"/"+user.getImagen()).delete();
            }

            if(!pathFiles.exists()) {
                Files.createDirectory(Paths.get(path));
            }

            File fileWork = new File(pathFiles+"/"+fileName);
            OutputStream outputStream = new FileOutputStream(fileWork);
            InputStream content = file.getInputStream();

            int read = 0;
            byte[] bytes = new byte[1024];
            while ((read = content.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
            user.setImagen(fileName);
        }
        new UsuarioDAO().update(user);

        resp.sendRedirect("/user/@"+user.getUsername());
    }
}

