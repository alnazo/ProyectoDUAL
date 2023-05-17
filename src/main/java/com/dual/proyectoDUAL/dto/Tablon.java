package com.dual.proyectoDUAL.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class Tablon implements Comparable<Tablon> {

    private int id;
    private String mensaje;
    private Usuario id_user;
    private int likes;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private Timestamp createdAt;

    public Tablon(ResultSet result) {
        try {
            this.id = result.getInt("id");
            this.mensaje = result.getString("mensaje");
            this.id_user = new Usuario(result);
            this.likes = result.getInt("likes");
            this.createdAt = Timestamp.valueOf(result.getString("create_at"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int compareTo(Tablon otroObjeto) {
        return this.createdAt.compareTo(otroObjeto.getCreatedAt());
    }

}