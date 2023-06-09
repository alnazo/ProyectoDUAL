package com.dual.proyectoDUAL.dao;

import com.dual.proyectoDUAL.dto.Grupo;
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
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;

public class GrupoDAO {

    private final WebTarget webTarget;

    public GrupoDAO() {
        Client client = ClientBuilder.newClient();
        this.webTarget = client.target("http://localhost:8081/api/grupos/");
    }
    public Grupo findById(int id) {
        String path = id + "/get";
        return webTarget.path(path)
                .request(MediaType.APPLICATION_JSON)
                .get(Grupo.class);
    }

    public List<Grupo> getAll() throws JsonProcessingException {
        String path = "/getAll";
        String json = webTarget.path(path).request(MediaType.APPLICATION_JSON).get(String.class);

        List<Grupo> grupos = new ArrayList<>();
        if (json.length() > 4) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            CollectionType setType = mapper.getTypeFactory().constructCollectionType(List.class, Grupo.class);
            grupos = mapper.readValue(json, setType);
        }
        return grupos;
    }

    public Grupo getById(int id){
        String path = id+"/get";
        return webTarget.path(path)
                .request(MediaType.APPLICATION_JSON)
                .get(Grupo.class);
    }

    public Grupo create(Grupo grupo){
        String path = "/add";
        return webTarget.path(path)
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(grupo, MediaType.APPLICATION_JSON), Grupo.class);
    }

    public Grupo update(Grupo grupo){
        String path = grupo.getId()+"/update";
        return webTarget.path(path)
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.entity(grupo, MediaType.APPLICATION_JSON), Grupo.class);
    }

    public void delete(Grupo grupo) {
        String path = grupo.getId() + "/delete";
        webTarget.path(path)
                .request(MediaType.APPLICATION_JSON)
                .delete();
    }



}
