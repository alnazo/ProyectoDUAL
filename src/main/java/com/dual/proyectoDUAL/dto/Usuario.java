package com.dual.proyectoDUAL.dto;

import lombok.*;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    private int id;
    private String username;
    private String password;
    private String email;
    private boolean admin;

    public Usuario(ResultSet result){
        try{
            this.id = result.getInt("id");
            this.username = result.getString("username");
            this.password = result.getString("password");
            this.email = result.getString("email");
            this.admin = this.isAdmin(result.getInt("admin"));
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    private boolean isAdmin(int admin){
        boolean res = false;
        if(admin == 1){
            res = true;
        }
        return res;
    }

}
