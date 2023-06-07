package com.dual.proyectoDUAL.dao;

import com.dual.proyectoDUAL.dto.Grupo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GrupoDAO {
    private Connection connection;

    public GrupoDAO(Connection connection) {
        this.connection = connection;
    }

    //GET
    public List<Grupo> obtenerTodoGrupo() {
        List<Grupo> grupos = new ArrayList<>();
        String sql = "SELECT * FROM grupo";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet result = statement.executeQuery()) {
            while (result.next()) {
                grupos.add(new Grupo(result));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return grupos;
    }

    public Grupo obtenerGrupoPorId(int id) {
        String sql = "SELECT * FROM grupo WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return new Grupo(result);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean insertarGrupo(Grupo grupo) {
        String sql = "INSERT INTO grupo (servicio, user1, user2, user3, user4, user5, user6, user7, user8) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, grupo.getServicio());
            statement.setInt(2, grupo.getUser1());
            statement.setInt(3, grupo.getUser2());
            statement.setObject(4, grupo.getUser3());
            statement.setObject(5, grupo.getUser4());
            statement.setObject(6, grupo.getUser5());
            statement.setObject(7, grupo.getUser6());
            statement.setObject(8, grupo.getUser7());
            statement.setObject(9, grupo.getUser8());
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean actualizarGrupo(Grupo grupo) {
        String sql = "UPDATE grupo SET servicio = ?, user1 = ?, user2 = ?, user3 = ?, user4 = ?, user5 = ?, " +
                "user6 = ?, user7 = ?, user8 = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, grupo.getServicio());
            statement.setInt(2, grupo.getUser1());
            statement.setInt(3, grupo.getUser2());
            statement.setObject(4, grupo.getUser3());
            statement.setObject(5, grupo.getUser4());
            statement.setObject(6, grupo.getUser5());
            statement.setObject(7, grupo.getUser6());
            statement.setObject(8, grupo.getUser7());
            statement.setObject(9, grupo.getUser8());
            statement.setInt(10, grupo.getId());
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void eliminarGrupo(int id) {
        String sql = "DELETE FROM grupo WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}