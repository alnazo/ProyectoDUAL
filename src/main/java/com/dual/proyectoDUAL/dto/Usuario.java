package com.dual.proyectoDUAL.dto;

import lombok.*;

import java.io.File;
import java.nio.file.Path;
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

    private final String pathImg = "/img/usuarios/"+this.id+"/"+this.imagen;

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

    public String sourceImagen() {
        File f = new File(pathImg);
        if (f.exists() && !f.isDirectory()) {
            return "img/usuarios/" + this.id + "/" + this.imagen;
        } else {
            return null;
        }

    }

}


