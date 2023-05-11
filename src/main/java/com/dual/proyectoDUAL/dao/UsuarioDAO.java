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

    public List<Usuario> obtenerTodoUsuario() {
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

    public Usuario obtenerUsuarioPorId(int id) {
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

    public void insertarUsuario(Usuario usuario) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO usuario (usuario, pass, email, nacimiento, admin) VALUES (?, ?, ?, ?, ?)"
            );
            statement.setString(1, usuario.getUsername());
            statement.setString(2, usuario.getPassword());
            statement.setString(3, usuario.getEmail());
            statement.setDate(4, Date.valueOf(usuario.getNacimiento()));
            statement.setInt(5, usuario.isAdmin() ? 1 : 0);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizarUsuario(Usuario usuario) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE usuario SET usuario = ?, pass = ?, email = ?, nacimiento = ?, admin = ? WHERE id = ?"
            );
            statement.setString(1, usuario.getUsername());
            statement.setString(2, usuario.getPassword());
            statement.setString(3, usuario.getEmail());
            statement.setDate(4, Date.valueOf(usuario.getNacimiento()));
            statement.setInt(5, usuario.isAdmin() ? 1 : 0);
            statement.setInt(6, usuario.getId());
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
