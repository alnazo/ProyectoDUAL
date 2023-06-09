package com.dual.proyectoDUAL.dto;

import jakarta.servlet.ServletContext;
import lombok.*;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Getter
@Setter
public class Usuario {
    private Integer id;
    private String username;
    private String password;
    private String email;
    private String imagen;
    private LocalDate nacimiento;
    private Boolean admin;

    public Usuario(ResultSet result) {
        try {
            this.id = result.getInt("id");
            this.username = result.getString("username");
            this.password = result.getString("password");
            this.email = result.getString("email");
            this.imagen = result.getString("img_perfil");
            this.nacimiento = result.getDate("nacimiento").toLocalDate();
            this.admin = result.getBoolean("admin");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String sourceImagen(ServletContext servletContext) {
        String file = servletContext.getRealPath("/img/usuarios/" + this.id + "/" + this.imagen);
        if (file != null && new File(file).exists() && this.imagen != null) {
            return "/img/usuarios/" + this.id + "/" + this.imagen;
        } else {
            return null;
        }

    }

}


