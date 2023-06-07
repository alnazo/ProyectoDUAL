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
public class Grupo {
    private int id;
    private Servicio servicio;
    private Usuario user1;
    private Usuario user2;
    private Usuario user3;
    private Usuario user4;
    private Usuario user5;
    private Usuario user6;
    private Usuario user7;
    private Usuario user8;

    public Grupo(ResultSet result){
        try  {
            this.id = result.getInt("id");
            this.servicio = (Servicio) result.getObject("servicio");
            this.user1 = (Usuario) result.getObject("user1");
            this.user2 = (Usuario) result.getObject("user2");
            this.user3 = (Usuario) result.getObject("user3");
            this.user4 = (Usuario) result.getObject("user4");
            this.user5 = (Usuario) result.getObject("user5");
            this.user6 = (Usuario) result.getObject("user6");
            this.user7 = (Usuario) result.getObject("user7");
            this.user8 = (Usuario) result.getObject("user8");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}