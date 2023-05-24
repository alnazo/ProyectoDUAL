package com.dual.proyectoDUAL.dao;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;

public class GrupoDAO {

    private final WebTarget webTarget;
    public GrupoDAO(){
        Client client = ClientBuilder.newClient();
        this.webTarget = client.target("http://localhost:8081/api/grupos/");
    }


}
