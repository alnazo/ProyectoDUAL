package com.dual.proyectoDUAL.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Tablon implements Serializable {

    private int id;
    private String message;
    private Usuario idUsuario;
    private int likes;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone="Europe/Berlin")
    private Timestamp createAt;

    public Tablon (String mensaje, Usuario user){
        this.id = 0;
        this.message = mensaje;
        this.idUsuario = user;
        this.likes = 0;
        this.createAt = Timestamp.from(Instant.now());
    }

    public Tablon(ResultSet result) {
        try {
            this.id = result.getInt("id");
            this.message = result.getString("mensaje");
            this.idUsuario = new Usuario(result);
            this.likes = result.getInt("likes");
            this.createAt = Timestamp.valueOf(result.getString("create_at"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String timeAgo() {
        Date now = new Date();
        Date ob = new Date(this.createAt.getTime());
        long dif = now.getTime() - ob.getTime();

        long seg = dif / 1000;
        long min = seg / 60;
        long hor = min / 60;
        long dia = hor / 24;
        long mes = dia / 30;
        long annio = mes / 12;

        String time = "";
        if (seg < 60) {
            time = seg + "s";
        } else if (min >= 1 && min < 60) {
            time = min + "m";
        } else if (hor >= 1 && hor < 24) {
            time = hor + "h";
        } else if (dia >= 1 && dia < 30) {
            time = dia + "D";
        } else if (mes >= 1 && mes < 12) {
            time = mes + "M";
        } else {
            time = annio + "Y";
        }

        return "Hace: " + time;
    }

    public void upLike(int id){
        System.out.println(id);
    }

}