package com.dual.proyectoDUAL.dao;

import com.dual.proyectoDUAL.dto.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    private Connection connection;

    public UsuarioDAO(Connection connection) {
        this.connection = connection;
    }

    public void insertarUsuario(Usuario usuario) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO usuario (username, password, email, admin) VALUES (?, ?, ?, ?)"
            );
            statement.setString(1, usuario.getUsername());
            statement.setString(2, usuario.getPassword());
            statement.setString(3, usuario.getEmail());
            statement.setBoolean(4, usuario.isAdmin());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Usuario obtenerUsuario(int id) {
        Usuario usuario = null;
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM usuario WHERE id = ?"
            );
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                usuario = new Usuario(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }

    public List<Usuario> obtenerTodosLosUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM usuario");
            while (resultSet.next()) {
                Usuario usuario = new Usuario(resultSet);
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    public void actualizarUsuario(Usuario usuario) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE usuario SET username = ?, password = ?, email = ?, admin = ? WHERE id = ?"
            );
            statement.setString(1, usuario.getUsername());
            statement.setString(2, usuario.getPassword());
            statement.setString(3, usuario.getEmail());
            statement.setBoolean(4, usuario.isAdmin());
            statement.setInt(5, usuario.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarUsuario(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM usuario WHERE id = ?"
            );
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
