package com.dual.proyectoDUAL.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Tablon {

    private int id;
    private String mensaje;
    private Usuario id_user;
    private int likes;
    private Timestamp createdAt;

    public Tablon(ResultSet result){
        try {
            this.id = result.getInt("id");
            this.mensaje = result.getString("mensaje");
            this.id_user = new Usuario(result);
            this.likes = result.getInt("likes");
            this.createdAt = result.getTimestamp("create_at");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}