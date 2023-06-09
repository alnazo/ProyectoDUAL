package com.dual.proyectoDUAL.dao;

import com.dual.proyectoDUAL.dto.Tablon;
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

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class TablonDAOTest {

    @Mock
    private WebTarget webTargetMock;

    @Mock
    private Invocation.Builder builderMock;

    @Mock
    private Response responseMock;

    private TablonDAO tablonDAO;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        //tablonDAO = new TablonDAO(webTargetMock);
    }

    @Test
    public void findAll_ReturnsTablones() throws Exception {
        String json = "[{\"id\":1,\"message\":\"Hello\",\"idUsuario\":{\"id\":1,\"username\":\"john\",\"email\":\"john@example.com\"},\"likes\":0,\"createAt\":\"2023-05-30T10:00:00Z\"}]";
        List<Tablon> tablones = new ArrayList<>();
        tablones.add(new Tablon(1, "Hello", new Usuario(1, "john", "123456" ,"john@example.com", null, LocalDate.now(), false), 0, Timestamp.valueOf("2023-05-30 10:00:00")));

        when(webTargetMock.path(anyString())).thenReturn(webTargetMock);
        when(webTargetMock.request(MediaType.APPLICATION_JSON)).thenReturn(builderMock);
        when(builderMock.get(String.class)).thenReturn(json);

        List<Tablon> result = tablonDAO.findAll();

        Assert.assertEquals(tablones, result);
        verify(webTargetMock).path("getAll");
        verify(webTargetMock).request(MediaType.APPLICATION_JSON);
        verify(builderMock).get(String.class);
    }

    @Test
    public void findByUserId_ReturnsTablones() throws Exception {
        int id = 1;
        String json = "[{\"id\":1,\"message\":\"Hello\",\"idUsuario\":{\"id\":1,\"username\":\"john\",\"email\":\"john@example.com\"},\"likes\":0,\"createAt\":\"2023-05-30T10:00:00Z\"}]";
        List<Tablon> tablones = new ArrayList<>();
        tablones.add(new Tablon(1, "Hello", new Usuario(1, "john", "123456" ,"john@example.com", null, LocalDate.now(), false), 0, Timestamp.valueOf("2023-05-30 10:00:00")));

        when(webTargetMock.path(anyString())).thenReturn(webTargetMock);
        when(webTargetMock.request(MediaType.APPLICATION_JSON)).thenReturn(builderMock);
        when(builderMock.get(String.class)).thenReturn(json);

        List<Tablon> result = tablonDAO.findByUserId(id);

        Assert.assertEquals(tablones, result);
        verify(webTargetMock).path(id + "/getAllUser");
        verify(webTargetMock).request(MediaType.APPLICATION_JSON);
        verify(builderMock).get(String.class);
    }

    @Test
    public void findById_ReturnsTablon() throws Exception {
        int id = 1;
        String json = "{\"id\":1,\"message\":\"Hello\",\"idUsuario\":{\"id\":1,\"username\":\"john\",\"email\":\"john@example.com\"},\"likes\":0,\"createAt\":\"2023-05-30T10:00:00Z\"}";
        Tablon tablon = new Tablon(1, "Hello", new Usuario(1, "john", "123456" ,"john@example.com", null, LocalDate.now(), false), 0, Timestamp.valueOf("2023-05-30 10:00:00"));

        when(webTargetMock.path(anyString())).thenReturn(webTargetMock);
        when(webTargetMock.request(MediaType.APPLICATION_JSON)).thenReturn(builderMock);
        when(builderMock.get(String.class)).thenReturn(json);

        Tablon result = tablonDAO.findById(id);

        Assert.assertEquals(tablon, result);
        verify(webTargetMock).path(id + "/get");
        verify(webTargetMock).request(MediaType.APPLICATION_JSON);
        verify(builderMock).get(String.class);
    }

    @Test
    public void send_ReturnsTablon() throws Exception {
        Tablon tablon = new Tablon(1, "Hello", new Usuario(1, "john", "123456" ,"john@example.com", null, LocalDate.now(), false), 0, Timestamp.valueOf("2023-05-30 10:00:00"));

        when(webTargetMock.path(anyString())).thenReturn(webTargetMock);
        when(webTargetMock.request(MediaType.APPLICATION_JSON)).thenReturn(builderMock);
        when(builderMock.post(Entity.entity(tablon, MediaType.APPLICATION_JSON), Tablon.class)).thenReturn(tablon);

        Tablon result = tablonDAO.send(tablon);

        Assert.assertEquals(tablon, result);
        verify(webTargetMock).path("/add");
        verify(webTargetMock).request(MediaType.APPLICATION_JSON);
        verify(builderMock).post(Entity.entity(tablon, MediaType.APPLICATION_JSON), Tablon.class);
    }
}
