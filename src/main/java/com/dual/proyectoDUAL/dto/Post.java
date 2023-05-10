package com.dual.proyectoDUAL.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    private int id;
    private int id_user;
    private String message;
    private int id_servicio;
    private Date create_at;

    public Post(ResultSet result){
        try {
            this.id = result.getInt("id");
            this.id_user = result.getInt("id_user");
            this.message = result.getString("message");
            this.id_servicio  = result.getInt("id_servicio");
            this.create_at = result.getDate("create_at");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
