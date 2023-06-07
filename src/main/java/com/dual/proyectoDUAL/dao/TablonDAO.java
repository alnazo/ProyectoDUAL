package com.dual.proyectoDUAL.dao;

import com.dual.proyectoDUAL.dto.Tablon;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

public class TablonDAO {

    private final WebTarget webTarget;

    public TablonDAO() {
        Client client = ClientBuilder.newClient();
        this.webTarget = client.target("http://localhost:8081/api/tablones/");
    }

    public List<Tablon> findAll() throws JsonProcessingException {
        String path = "getAll";
        String json = webTarget.path(path).request(MediaType.APPLICATION_JSON).get(String.class);

        List<Tablon> tablones = new ArrayList<>();
        if (json.length() > 4) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            CollectionType setType = mapper.getTypeFactory().constructCollectionType(List.class, Tablon.class);
            tablones = mapper.readValue(json, setType);


            for (Tablon tablon : tablones) {
                Timestamp timestamp = tablon.getCreateAt();
                LocalDateTime localDateTime = timestamp.toLocalDateTime().minusHours(2);
                Timestamp adjustedTimestamp = Timestamp.valueOf(localDateTime.atOffset(ZoneOffset.UTC).toLocalDateTime());
                tablon.setCreateAt(adjustedTimestamp);
            }


        } else {
            tablones = null;
        }
        return tablones;
    }

    public List<Tablon> findByUserId(int id) throws JsonProcessingException {
        String path = id + "/getAllUser";
        String json = webTarget.path(path).request(MediaType.APPLICATION_JSON).get(String.class);

        List<Tablon> tablones = new ArrayList<>();
        if (json.length() > 4) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            CollectionType setType = mapper.getTypeFactory().constructCollectionType(List.class, Tablon.class);
            tablones = mapper.readValue(json, setType);

            for (Tablon tablon : tablones) {
                Timestamp timestamp = tablon.getCreateAt();
                LocalDateTime localDateTime = timestamp.toLocalDateTime().minusHours(2);
                Timestamp adjustedTimestamp = Timestamp.valueOf(localDateTime.atOffset(ZoneOffset.UTC).toLocalDateTime());
                tablon.setCreateAt(adjustedTimestamp);
            }


        } else {
            tablones = null;
        }
        return tablones;
    }

    public Tablon findById(int id) throws JsonProcessingException {
        Tablon tab = null;

        String path = id + "/get";
        String json = webTarget.path(path).request(MediaType.APPLICATION_JSON).get(String.class);
        if (json.length() > 4) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            tab = mapper.readValue(json, Tablon.class);

            Timestamp timestamp = tab.getCreateAt();
            LocalDateTime localDateTime = timestamp.toLocalDateTime().minusHours(2);
            Timestamp adjustedTimestamp = Timestamp.valueOf(localDateTime.atOffset(ZoneOffset.UTC).toLocalDateTime());
            tab.setCreateAt(adjustedTimestamp);
        }

        return tab;
    }

    public Tablon send(Tablon tab) throws JsonProcessingException {
        String path = "/add";
        return webTarget.path(path)
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(tab, MediaType.APPLICATION_JSON), Tablon.class);
    }

    public void delete(Tablon post) {

    }
}
