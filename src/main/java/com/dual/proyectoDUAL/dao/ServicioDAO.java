package com.dual.proyectoDUAL.dao;

import com.dual.proyectoDUAL.dto.Servicio;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;

import java.util.ArrayList;
import java.util.List;

public class ServicioDAO {

    private final WebTarget webTarget;

    public ServicioDAO() {
        Client client = ClientBuilder.newClient();
        this.webTarget = client.target("http://localhost:8081/api/servicios/");
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

}
