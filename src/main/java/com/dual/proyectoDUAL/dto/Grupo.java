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

    private int id_servicio;
    private int id_user1;
    private int id_user2;
    private int id_user3;
    private int id_user4;
    private int id_user5;
    private int id_user6;
    private int id_user7;
    private int id_user8;

    public Grupo(ResultSet result) {
        try{
            this.id_servicio = result.getInt("id_servicio");
            this.id_user1 = result.getInt("id_user1");
            this.id_user2 = result.getInt("id_user2");
            this.id_user3 = result.getInt("id_user3");
            this.id_user4 = result.getInt("id_user4");
            this.id_user5 = result.getInt("id_user5");
            this.id_user6 = result.getInt("id_user6");
            this.id_user7 = result.getInt("id_user7");
            this.id_user8 = result.getInt("id_user8");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

}
