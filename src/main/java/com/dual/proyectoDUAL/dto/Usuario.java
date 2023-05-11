package com.dual.proyectoDUAL.dto;

import lombok.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Usuario {
    private int id;
    private String username;
    private String password;
    private String email;
    private LocalDate nacimiento;
    private boolean admin;

    public Usuario(ResultSet result){
        try{
            this.id = result.getInt("id");
            this.username = result.getString("usuario");
            this.password = result.getString("pass");
            this.email = result.getString("email");
            this.nacimiento = result.getDate("nacimiento").toLocalDate();
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
