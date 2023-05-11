package com.dual.proyectoDUAL.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Builder;
import lombok.ToString;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

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
    private Date nacimiento;
    private Boolean admin;

    public Usuario(ResultSet result) {
        try {
            this.id = result.getInt("id");
            this.username = result.getString("username");
            this.password = result.getString("password");
            this.email = result.getString("email");
            this.imagen = result.getString("img_perfil");
            this.nacimiento = result.getDate("nacimiento");
            this.admin = result.getBoolean("admin");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}


