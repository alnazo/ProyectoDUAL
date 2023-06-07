package com.dual.proyectoDUAL.dao;

import com.dual.proyectoDUAL.dto.Usuario;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    private final WebTarget webTarget;

    public UsuarioDAO() {
        Client client = ClientBuilder.newClient();
        this.webTarget = client.target("http://localhost:8081/api/usuarios/");
    }

    public Usuario getUsuario(int id) {
        String path = id + "/get";
        return webTarget.path(path)
                .request(MediaType.APPLICATION_JSON)
                .get(Usuario.class);
    }

    public Usuario findByNombreExacto(String nombre) {
        String path = nombre + "/getn";
        return webTarget.path(path)
                .request(MediaType.APPLICATION_JSON)
                .get(Usuario.class);
    }


    public List<Usuario> findAll() throws JsonProcessingException {
        String path = "getAll";
        String json = webTarget.path(path).request(MediaType.APPLICATION_JSON).get(String.class);

        List<Usuario> usuarios = new ArrayList<>();
        if (json.length() > 4) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            CollectionType setType = mapper.getTypeFactory().constructCollectionType(List.class, Usuario.class);
            usuarios = mapper.readValue(json, setType);

        } else {
            usuarios = null;
        }
        return usuarios;
    }


    public Usuario findByEmail(String email) {
        String path = email + "/getM";
        return webTarget.path(path)
                .request(MediaType.APPLICATION_JSON)
                .get(Usuario.class);
    }

    public Usuario register(Usuario user) {
        String path = "/add";
        return webTarget.path(path)
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(user, MediaType.APPLICATION_JSON), Usuario.class);
    }

}