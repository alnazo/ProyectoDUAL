package com.dual.proyectoDUAL.web.servlet.tablonController;

import com.dual.proyectoDUAL.dao.TablonDAO;
import com.dual.proyectoDUAL.dto.Tablon;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "Likes", urlPatterns = "/like/post")
public class LikeServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("post"));
        int actuales = Integer.parseInt(req.getParameter("actual"));
        String url = req.getHeader("referer");

        Tablon tab = new TablonDAO().findById(id);
        if(tab.getLikes() == actuales){
            tab.setLikes(actuales+1);
            new TablonDAO().updateLikes(tab);
        }

        resp.sendRedirect(url);
    }
}
