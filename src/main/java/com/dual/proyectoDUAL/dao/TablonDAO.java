package com.dual.proyectoDUAL.dao;

import com.dual.proyectoDUAL.dto.Tablon;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class TablonDAO {

    private final WebTarget webTarget;

    public TablonDAO() {
        Client client = ClientBuilder.newClient();
        this.webTarget = client.target("http://localhost:8081/ProyectoDualWebService/api/tablon/");
    }

    public Set<Tablon> findAll() throws JsonProcessingException {
        String path = "getall";
        String json = webTarget.path(path).request(MediaType.APPLICATION_JSON).get(String.class);

        Set<Tablon> tablones = new TreeSet<>(Comparator.comparing(Tablon::getCreatedAt).reversed());
        if (json.length() > 4) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            CollectionType setType = mapper.getTypeFactory().constructCollectionType(Set.class, Tablon.class);
            tablones = mapper.readValue(json, setType);


        } else {
            tablones = null;
        }
        return tablones;
    }


    private String removeUTCTimestamp(String json) {

        return null;
    }
}
