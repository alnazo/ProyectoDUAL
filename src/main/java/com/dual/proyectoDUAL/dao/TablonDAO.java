package com.dual.proyectoDUAL.dao;

import com.dual.proyectoDUAL.dto.Tablon;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TablonDAO {
    private Connection connection;

    public TablonDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Tablon> obtenerTodoTablon() {
        List<Tablon> tablones = new ArrayList<>();
        String sql = "SELECT * FROM tablon";
        try (Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(sql)) {
            while (result.next()) {
                Tablon tablon = new Tablon(result);
                tablones.add(tablon);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tablones;
    }

    public Tablon obneterTablonPorId(int id) {
        String sql = "SELECT * FROM tablon WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    return new Tablon(result);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void insertarTablon(Tablon tablon) {
        String sql = "INSERT INTO tablon (mensaje, id_user, likes, create_at) " +
                "VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, tablon.getMensaje());
            statement.setInt(2, tablon.getId_user());
            statement.setInt(3, tablon.getLikes());
            statement.setTimestamp(4, Timestamp.valueOf(tablon.getCreatedAt()));
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                tablon.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizarTablon(Tablon tablon) {
        String sql = "UPDATE tablon SET mensaje = ?, id_user = ?, likes = ?, create_at = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, tablon.getMensaje());
            statement.setInt(2, tablon.getId_user());
            statement.setInt(3, tablon.getLikes());
            statement.setTimestamp(4, Timestamp.valueOf(tablon.getCreatedAt()));
            statement.setInt(5, tablon.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarTablon(int id) {
        String sql = "DELETE FROM tablon WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}