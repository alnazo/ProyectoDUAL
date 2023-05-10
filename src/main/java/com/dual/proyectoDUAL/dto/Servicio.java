package com.dual.proyectoDUAL.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Servicio {
    private int id;
    private String plataforma;
    private String url;

    public Servicio(ResultSet result){
        try {
            this.id = result.getInt("id");
            this.plataforma = result.getString("plataforma");
            this.url = result.getString("url");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
