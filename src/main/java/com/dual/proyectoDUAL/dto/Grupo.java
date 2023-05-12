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
    private int servicio;
    private int user1;
    private int user2;
    private Integer user3;
    private Integer user4;
    private Integer user5;
    private Integer user6;
    private Integer user7;
    private Integer user8;

    public Grupo(ResultSet result){
        try  {
            this.id = result.getInt("id");
            this.servicio = result.getInt("servicio");
            this.user1 = result.getInt("user1");
            this.user2 = result.getInt("user2");
            this.user3 = (Integer) result.getObject("user3");
            this.user4 = (Integer) result.getObject("user4");
            this.user5 = (Integer) result.getObject("user5");
            this.user6 = (Integer) result.getObject("user6");
            this.user7 = (Integer) result.getObject("user7");
            this.user8 = (Integer) result.getObject("user8");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}