package com.dual.proyectoDUAL.dao;

import com.dual.proyectoDUAL.dto.Tablon;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;

import java.util.HashSet;
import java.util.Set;

public class TablonDAO {

    private final WebTarget webTarget;

    public TablonDAO() {
        Client client = ClientBuilder.newClient();
        this.webTarget = client.target("http://localhost:8081/ProyectoDualWebService/api/tablon/");
    }

    public Set<Tablon> findAll() throws JsonProcessingException {
        String path = "getall";
        String json = webTarget.path(path).request(MediaType.APPLICATION_JSON).get(String.class);
        Set<Tablon> tablon = new HashSet<>();
        if(json.length()>4) {
            ObjectMapper mapper = new ObjectMapper();
            CollectionType setType = mapper.getTypeFactory().constructCollectionType(Set.class, Tablon.class);
            tablon = mapper.readValue(json, setType);
        } else {
            tablon = null;
        }
        return tablon;
    }

}
