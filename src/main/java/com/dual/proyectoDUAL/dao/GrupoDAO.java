package com.dual.proyectoDUAL.dao;

import com.dual.proyectoDUAL.dto.Grupo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;

import java.util.ArrayList;
import java.util.List;

public class GrupoDAO {

    private final WebTarget webTarget;

    public GrupoDAO() {
        Client client = ClientBuilder.newClient();
        this.webTarget = client.target("http://localhost:8081/api/grupos/");
    }

    public List<Grupo> getAll() throws JsonProcessingException {
        String path = "/getAll";
        String json = webTarget.path(path).request(MediaType.APPLICATION_JSON).get(String.class);

        List<Grupo> grupos = new ArrayList<>();
        if (json.length() > 4) {
            ObjectMapper mapper = new ObjectMapper();
            CollectionType setType = mapper.getTypeFactory().constructCollectionType(List.class, Grupo.class);
            grupos = mapper.readValue(json, setType);
        }
        return grupos;
    }

}
