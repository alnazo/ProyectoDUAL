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
    private String nombre;
    private double precio;
    private String web;
    public Servicio(ResultSet result){
        try {
            this.id = result.getInt("id");
            this.nombre = result.getString("nombre");
            this.precio = result.getDouble("precio");
            this.web = result.getString("web");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}