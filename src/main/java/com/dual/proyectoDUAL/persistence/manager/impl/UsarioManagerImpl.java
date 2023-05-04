package com.dual.proyectoDUAL.persistence.manager.impl;

import com.dual.proyectoDUAL.dto.Usuario;
import com.dual.proyectoDUAL.persistence.manager.UsuarioManager;

import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

public class UsarioManagerImpl implements UsuarioManager {

    @Override
    public List<Usuario> findAll(Connection con) {
        List<Usuario> usuarios= new ArrayList<>();

        try (Statement stmt = con.createStatement()) {
            ResultSet result = stmt.executeQuery("SELECT * FROM usuario");
            result.beforeFirst();
            while (result.next()){
                usuarios.add(new Usuario(result));
            }

            return usuarios;

        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Usuario> findAllById(Connection con, Set<Integer> ids) {
        String sql = String.format("SELECT * FROM usuario WHERE id IN (%s)",ids.stream().map(data -> "\""+data+"\"").collect(Collectors.joining(", ")));
        try (Statement stmt = con.createStatement()){
            ResultSet result = stmt.executeQuery(sql);
            result.beforeFirst();


            List<Usuario> usuarios= new ArrayList<>();

            while (result.next()){
                //Añado cada resultado a la lista
                Usuario club = new Usuario(result);
                usuarios.add(club);
            }

            return usuarios;

        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }



    @Override
    public Usuario findById(Connection con, int id) {
        String sql = "SELECT * FROM usuario WHERE ID = ?";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet result = stmt.executeQuery();

            result.beforeFirst();

            //Inicializo variable
            Usuario usuario = null;


            while (result.next()){
                //Inicializo un usuario para cada resultado
                usuario = new Usuario(result);
            }

            return usuario;

        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Usuario findByUsername(Connection con, String username) {
        String sql = "SELECT * FROM usuario WHERE usuario = ?";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1,username);
            ResultSet result = stmt.executeQuery();

            result.beforeFirst();

            //Inicializo variable
            Usuario usuario = null;


            while (result.next()){
                //Inicializo un usuario para cada resultado
                usuario = new Usuario(result);
            }

            return usuario;

        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Usuario findByEmail(Connection con, String email) {
        String sql = "SELECT * FROM usuario WHERE email = ?";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1,email);
            ResultSet result = stmt.executeQuery();

            result.beforeFirst();

            //Inicializo variable
            Usuario usuario = null;


            while (result.next()){
                //Inicializo un usuario para cada resultado
                usuario = new Usuario(result);
            }

            return usuario;

        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
}
