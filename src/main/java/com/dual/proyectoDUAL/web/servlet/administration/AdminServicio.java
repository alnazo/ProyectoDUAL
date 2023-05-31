package com.dual.proyectoDUAL.web.servlet.administration;

import com.dual.proyectoDUAL.dao.ServicioDAO;
import com.dual.proyectoDUAL.dao.TablonDAO;
import com.dual.proyectoDUAL.dto.Servicio;
import com.dual.proyectoDUAL.dto.Tablon;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name="adminServicio", urlPatterns="/admin/servicio")
public class AdminServicio extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            List<Servicio> servicios = new ServicioDAO().findAll();

            req.getSession().setAttribute("servicio", servicios);
            req.getRequestDispatcher("/administracion/servicio.jsp").forward(req, resp);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nombreIntroducido= req.getParameter("nombre");
        String planIntroducido = req.getParameter("plan");
        String precioIntroducido= req.getParameter("precio");
        String webIntroducido= req.getParameter("web");
        if(nombreIntroducido.isEmpty() ||planIntroducido.isEmpty() || precioIntroducido.isEmpty() || webIntroducido.isEmpty()){
            req.setAttribute("error","Faltan datos por rellenar");
        }else{
            Servicio s1 = new Servicio(0,nombreIntroducido,planIntroducido,Double.parseDouble(precioIntroducido),webIntroducido);
            new ServicioDAO().register(s1);

        }
        resp.sendRedirect("/admin/servicio");
    }
}
