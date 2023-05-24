package com.dual.proyectoDUAL.dao;

import com.dual.proyectoDUAL.dto.Usuario;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;

public class UsuarioDAO {

    private final WebTarget webTarget;

    public UsuarioDAO(){
        Client client = ClientBuilder.newClient();
        this.webTarget = client.target("http://localhost:8081/api/usuarios/");
    }

    public Usuario getUsuario(int id){
        String path = id+"/get";
        return webTarget.path(path)
                .request(MediaType.APPLICATION_JSON)
                .get(Usuario.class);
    }

    public Usuario findByNombreExacto(String nombre){
        String path = nombre+"/getn";
        return webTarget.path(path)
                .request(MediaType.APPLICATION_JSON)
                .get(Usuario.class);
    }


}
