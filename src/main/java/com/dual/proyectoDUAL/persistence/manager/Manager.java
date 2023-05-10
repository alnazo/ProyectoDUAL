package com.dual.proyectoDUAL.persistence.manager;

import com.dual.proyectoDUAL.dto.Usuario;

import java.sql.Connection;
import java.util.List;
import java.util.Set;

public interface Manager<T> {
    /**
     * Busca todos los objetos en la base de datos
     *
     * @param con conexion de base de datos
     * @return a {@link List} of {@link T}
     */
    List<T> findAll(Connection con);


    /**
     * Busca todos los objetos en la base de datos con una lista de ID
     *
     * @param con conexion de base de datos
     * @param ids Lista de ids para la busqueda de objetos en la base de datos
     * @return a {@link List} of {@link T}
     */
    List<T> findAllById(Connection con, Set<Integer> ids);

}
