package com.dual.proyectoDUAL.dao;

import com.dual.proyectoDUAL.dto.Usuario;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class UsuarioDAOTest {

    @Mock
    private WebTarget webTargetMock;

    @Mock
    private Invocation.Builder builderMock;

    @Mock
    private Response responseMock;

    private UsuarioDAO usuarioDAO;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        usuarioDAO = new UsuarioDAO();
    }

    @Test
    public void getUsuario_ReturnsUsuario() {
        int id = 1;
        Usuario usuario = new Usuario(1, "john", "password123", "john@example.com", "imagen.jpg", LocalDate.of(1990, 5, 10), true);

        when(webTargetMock.path(anyString())).thenReturn(webTargetMock);
        when(webTargetMock.request(MediaType.APPLICATION_JSON)).thenReturn(builderMock);
        when(builderMock.get(Usuario.class)).thenReturn(usuario);

        Usuario result = usuarioDAO.getUsuario(id);

        Assert.assertEquals(usuario, result);
        verify(webTargetMock).path(id + "/get");
        verify(webTargetMock).request(MediaType.APPLICATION_JSON);
        verify(builderMock).get(Usuario.class);
    }

    @Test
    public void findByNombreExacto_ReturnsUsuario() {
        String nombre = "john";
        Usuario usuario = new Usuario(1, "john", "password123", "john@example.com", "imagen.jpg", LocalDate.of(1990, 5, 10), true);

        when(webTargetMock.path(anyString())).thenReturn(webTargetMock);
        when(webTargetMock.request(MediaType.APPLICATION_JSON)).thenReturn(builderMock);
        when(builderMock.get(Usuario.class)).thenReturn(usuario);

        Usuario result = usuarioDAO.findByNombreExacto(nombre);

        Assert.assertEquals(usuario, result);
        verify(webTargetMock).path(nombre + "/getn");
        verify(webTargetMock).request(MediaType.APPLICATION_JSON);
        verify(builderMock).get(Usuario.class);
    }

    @Test
    public void findAll_ReturnsUsuarios() throws Exception {
        String json = "[{\"id\":1,\"username\":\"john\",\"password\":\"password123\",\"email\":\"john@example.com\",\"imagen\":\"imagen.jpg\",\"nacimiento\":\"1990-05-10\",\"admin\":true}]";
        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(new Usuario(1, "john", "password123", "john@example.com", "imagen.jpg", LocalDate.of(1990, 5, 10), true));

        when(webTargetMock.path(anyString())).thenReturn(webTargetMock);
        when(webTargetMock.request(MediaType.APPLICATION_JSON)).thenReturn(builderMock);
        when(builderMock.get(String.class)).thenReturn(json);

        List<Usuario> result = usuarioDAO.findAll();

        Assert.assertEquals(usuarios, result);
        verify(webTargetMock).path("getAll");
        verify(webTargetMock).request(MediaType.APPLICATION_JSON);
        verify(builderMock).get(String.class);
    }

    @Test
    public void findByEmail_ReturnsUsuario() {
        String email = "john@example.com";
        Usuario usuario = new Usuario(1, "john", "password123", "john@example.com", "imagen.jpg", LocalDate.of(1990, 5, 10), true);

        when(webTargetMock.path(anyString())).thenReturn(webTargetMock);
        when(webTargetMock.request(MediaType.APPLICATION_JSON)).thenReturn(builderMock);
        when(builderMock.get(Usuario.class)).thenReturn(usuario);

        Usuario result = usuarioDAO.findByEmail(email);

        Assert.assertEquals(usuario, result);
        verify(webTargetMock).path(email + "/getM");
        verify(webTargetMock).request(MediaType.APPLICATION_JSON);
        verify(builderMock).get(Usuario.class);
    }

    @Test
    public void register_ReturnsUsuario() {
        Usuario usuario = new Usuario(1, "john", "password123", "john@example.com", "imagen.jpg", LocalDate.of(1990, 5, 10), true);

        when(webTargetMock.path(anyString())).thenReturn(webTargetMock);
        when(webTargetMock.request(MediaType.APPLICATION_JSON)).thenReturn(builderMock);
        when(builderMock.post(Entity.entity(usuario, MediaType.APPLICATION_JSON), Usuario.class)).thenReturn(usuario);

        Usuario result = usuarioDAO.register(usuario);

        Assert.assertEquals(usuario, result);
        verify(webTargetMock).path("/add");
        verify(webTargetMock).request(MediaType.APPLICATION_JSON);
        verify(builderMock).post(Entity.entity(usuario, MediaType.APPLICATION_JSON), Usuario.class);
    }
}
