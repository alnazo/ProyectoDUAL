package com.dual.proyectoDUAL.dao;

import com.dual.proyectoDUAL.dto.Servicio;
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

public class ServicioDAO {

    private final WebTarget webTarget;

    public ServicioDAO() {
        Client client = ClientBuilder.newClient();
        this.webTarget = client.target("http://localhost:8081/api/servicios/");
    }

    public Servicio findById(int id) throws JsonProcessingException {
        Servicio sv = null;

        String path = id + "/get";
        String json = webTarget.path(path).request(MediaType.APPLICATION_JSON).get(String.class);
        if (json.length() > 4) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            sv = mapper.readValue(json, Servicio.class);

        }

        return sv;
    }


    public List<Servicio> getAll() throws JsonProcessingException {
        String path = "/getAll";
        String json = webTarget.path(path).request(MediaType.APPLICATION_JSON).get(String.class);

        List<Servicio> servicios = null;
        if (json.length() > 4) {
            ObjectMapper mapper = new ObjectMapper();
            CollectionType setType = mapper.getTypeFactory().constructCollectionType(List.class, Servicio.class);
            servicios = mapper.readValue(json, setType);
        }
        return servicios;
    }

    public List<Servicio> getByName(String servicio) throws JsonProcessingException {
        String path = servicio+"/getS";
        String json = webTarget.path(path).request(MediaType.APPLICATION_JSON).get(String.class);

        List<Servicio> servicios = null;
        if(json.length()>4){
            ObjectMapper mapper = new ObjectMapper();
            CollectionType setType = mapper.getTypeFactory().constructCollectionType(List.class, Servicio.class);
            servicios = mapper.readValue(json, setType);
        }
        return servicios;
    }

    public Servicio findBySP(String servicio, String plan) throws JsonProcessingException {
        String path = servicio+"/"+plan+"/"+"getSP";
        String json = webTarget.path(path).request(MediaType.APPLICATION_JSON).get(String.class);

        Servicio servi = null;
        if(json.length() > 4){
            ObjectMapper mapper = new ObjectMapper();
            servi = mapper.readValue(json, Servicio.class);
        }
        return servi;
    }

    public List<Servicio> findAll() throws JsonProcessingException {
        String path = "getAll";
        String json = webTarget.path(path).request(MediaType.APPLICATION_JSON).get(String.class);

        List<Servicio> servicios = new ArrayList<>();
        if (json.length() > 4) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            CollectionType setType = mapper.getTypeFactory().constructCollectionType(List.class, Servicio.class);
            servicios = mapper.readValue(json, setType);


        } else {
            servicios = null;
        }
        return servicios;
    }

    public Servicio register(Servicio servicio) {
        String path = "/add";
        return webTarget.path(path)
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(servicio, MediaType.APPLICATION_JSON), Servicio.class);
    }

    public void delete(Servicio servicio){
        String path = servicio.getId() + "/delete";
        webTarget.path(path)
                .request(MediaType.APPLICATION_JSON)
                .delete();

    }
}
