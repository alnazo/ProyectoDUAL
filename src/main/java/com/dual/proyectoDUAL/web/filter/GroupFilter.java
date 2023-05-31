package com.dual.proyectoDUAL.web.filter;

import com.dual.proyectoDUAL.dto.Usuario;
import com.dual.proyectoDUAL.web.notifications.Notificaciones;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter(filterName = "FiltroSesion", urlPatterns = { "/grupos", "/grupos/*" }, dispatcherTypes = { DispatcherType.REQUEST, DispatcherType.FORWARD })
public class GroupFilter implements Filter{

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req=(HttpServletRequest)servletRequest;
        Usuario usuario = (Usuario)req.getSession().getAttribute("usuarioSesion");

        if (usuario == null) {
            Notificaciones.error = true;
            Notificaciones.msg = "Error, debes estar logueado para acceder.";
            ((HttpServletResponse)servletResponse).sendRedirect("/login");
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

}
