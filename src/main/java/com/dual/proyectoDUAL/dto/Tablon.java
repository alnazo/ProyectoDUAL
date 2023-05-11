package com.dual.proyectoDUAL.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Tablon {

    private int id;
    private String mensaje;
    private int id_user;
    private int likes;
    private LocalDateTime createdAt;

    public Tablon(ResultSet result){
        try {
            this.id = result.getInt("id");
            this.mensaje = result.getString("mensaje");
            this.id_user = result.getInt("id_user");
            this.likes = result.getInt("likes");
            this.createdAt = result.getTimestamp("create_at").toLocalDateTime();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
