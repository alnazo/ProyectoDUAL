package com.dual.proyectoDUAL.persistence.manager;

import com.dual.proyectoDUAL.dto.Usuario;

import java.sql.Connection;
import java.util.List;
import java.util.Set;

/**
 * Usuario DTO Manager.
 *
 * Contains all the queries used to consult and manipulate Usuario data.
 *
 */
public interface UsuarioManager extends Manager<Usuario>{

    /**
     *Busca todos los usuarios en la base de datos
     *
     * @param con DB connection
     * @return a {@link List} of {@link Usuario}
     */
    List<Usuario> findAll(Connection con);


    /**
     * Busca los usuarios en la base de datos de una lista de ID.
     *
     * @param con conexion de base de datos.
     * @param ids IDs de usuarios para buscarlos en la base de datos.
     * @return Un/a Una {@link List} de {@link Usuario}.
     */
    List<Usuario> findAllById(Connection con, Set<Integer> ids);


    /**
     * Busca el usuario en la base de datos de un ID.
     *
     * @param con conexion de base de datos.
     * @param id ID de un usuario especifico para buscarlo en la base de datos.
     * @return Un/a {@link Usuario}.
     */
    Usuario findById(Connection con, int id);


    /**
     * Busca el usuario en la base de datos de un nombre de usuario.
     *
     * @param con conexion de base de datos.
     * @param username username de un usuario especifico para buscarlo en la base de datos.
     * @return Un/a {@link Usuario}.
     */
    Usuario findByUsername(Connection con, String username);


    /**
     * Busca el usuario en la base de datos de un email.
     *
     * @param con conexion de base de datos.
     * @param email email de un usuario especifico para buscarlo en la base de datos.
     * @return Un/a {@link Usuario}.
     */
    Usuario findByEmail(Connection con, String email);





}
