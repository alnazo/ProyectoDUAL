package com.dual.proyectoDUAL.web.filter;

import com.dual.proyectoDUAL.dto.Usuario;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter(filterName = "FiltroSesion", urlPatterns = { "/login", "/registro", "/forgot" }, dispatcherTypes = { DispatcherType.REQUEST, DispatcherType.FORWARD })
public class UserFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req=(HttpServletRequest)servletRequest;

        Usuario usuario = (Usuario)req.getSession().getAttribute("usuarioSesion");

        if (usuario != null) {
            ((HttpServletResponse)servletResponse).sendRedirect("home");
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }

}
