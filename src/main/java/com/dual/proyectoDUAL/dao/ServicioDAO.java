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

import java.util.ArrayList;
import java.util.List;

public class ServicioDAO {

    private final WebTarget webTarget;
    public ServicioDAO(){
        Client client = ClientBuilder.newClient();
        this.webTarget = client.target("http://localhost:8081/api/servicios/");
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
            servicios= null;
        }
        return servicios;
    }

    public Servicio register(Servicio servicio){
        String path = "/add";
        return webTarget.path(path)
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(servicio, MediaType.APPLICATION_JSON), Servicio.class);
    }
}
