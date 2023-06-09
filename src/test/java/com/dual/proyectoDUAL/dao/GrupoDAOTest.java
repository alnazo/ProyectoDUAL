package com.dual.proyectoDUAL.dao;

import com.dual.proyectoDUAL.dto.Grupo;
import com.dual.proyectoDUAL.dto.Servicio;
import com.dual.proyectoDUAL.dto.Usuario;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GrupoDAOTest {

    @Mock
    private Connection mockConnection;

    @Mock
    private PreparedStatement mockStatement;

    @Mock
    private ResultSet mockResultSet;

    private GrupoDAO grupoDAO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        grupoDAO = new GrupoDAO(mockConnection);
    }

    @Test
    void obtenerTodoGrupo_DebeRetornarListaDeGrupos() throws SQLException, JsonProcessingException {
        Servicio servicio = new Servicio(1, "Netflix", "DUO", 13.4 ,"https://netflix.es/");
        Usuario user1 = new Usuario(1, "john", "123456", "john@example.com", null, LocalDate.now(), false);
        Usuario user2 = new Usuario(2, "john", "123456", "john@example.com", null, LocalDate.now(), false);
        Usuario user3 = new Usuario(3, "john", "123456", "john@example.com", null, LocalDate.now(), false);
        Usuario user4 = new Usuario(4, "john", "123456", "john@example.com", null, LocalDate.now(), false);
        Usuario user5 = new Usuario(5, "john", "123456", "john@example.com", null, LocalDate.now(), false);
        Usuario user6 = new Usuario(6, "john", "123456", "john@example.com", null, LocalDate.now(), false);
        Usuario user7 = new Usuario(7, "john", "123456", "john@example.com", null, LocalDate.now(), false);
        Usuario user8 = new Usuario(8, "john", "123456", "john@example.com", null, LocalDate.now(), false);

        List<Grupo> gruposEsperados = new ArrayList<>();
        gruposEsperados.add(new Grupo(1, servicio, user1, user2, user3, user4, user5, user6, user7, user8));
        gruposEsperados.add(new Grupo(2, servicio, user1, user2, user3, user4, user5, user6, user7, user8));

        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, true, false);
        when(mockResultSet.getInt("id")).thenReturn(1, 2);
        when(mockResultSet.getInt("servicio")).thenReturn(1, 2);
        when(mockResultSet.getInt("user1")).thenReturn(1, 2);
        when(mockResultSet.getInt("user2")).thenReturn(2, 3);
        when(mockResultSet.getObject("user3")).thenReturn(3, 4);
        when(mockResultSet.getObject("user4")).thenReturn(4, 5);
        when(mockResultSet.getObject("user5")).thenReturn(5, 6);
        when(mockResultSet.getObject("user6")).thenReturn(6, 7);
        when(mockResultSet.getObject("user7")).thenReturn(7, 8);
        when(mockResultSet.getObject("user8")).thenReturn(8, 9);

        List<Grupo> gruposObtenidos = grupoDAO.getAll();

        assertEquals(gruposEsperados.size(), gruposObtenidos.size());
        assertEquals(gruposEsperados.get(0).getId(), gruposObtenidos.get(0).getId());
        assertEquals(gruposEsperados.get(1).getId(), gruposObtenidos.get(1).getId());
        assertEquals(gruposEsperados.get(2).getId(), gruposObtenidos.get(2).getId());
    }

    @Test
    void obtenerGrupoPorId_DebeRetornarGrupoCorrecto() throws SQLException {
        Servicio servicio = new Servicio(1, "Netflix", "DUO", 13.4 ,"https://netflix.es/");
        Usuario user1 = new Usuario(1, "john", "123456", "john@example.com", null, LocalDate.now(), false);
        Usuario user2 = new Usuario(2, "john", "123456", "john@example.com", null, LocalDate.now(), false);
        Usuario user3 = new Usuario(3, "john", "123456", "john@example.com", null, LocalDate.now(), false);
        Usuario user4 = new Usuario(4, "john", "123456", "john@example.com", null, LocalDate.now(), false);
        Usuario user5 = new Usuario(5, "john", "123456", "john@example.com", null, LocalDate.now(), false);
        Usuario user6 = new Usuario(6, "john", "123456", "john@example.com", null, LocalDate.now(), false);
        Usuario user7 = new Usuario(7, "john", "123456", "john@example.com", null, LocalDate.now(), false);
        Usuario user8 = new Usuario(8, "john", "123456", "john@example.com", null, LocalDate.now(), false);

        int grupoId = 1;
        Grupo grupoEsperado = new Grupo(1, servicio, user1, user2, user3, user4, user5, user6, user7, user8);

        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getObject("id")).thenReturn(grupoId);
        when(mockResultSet.getObject("servicio")).thenReturn(grupoEsperado.getServicio());
        when(mockResultSet.getObject("user1")).thenReturn(grupoEsperado.getUser1());
        when(mockResultSet.getObject("user2")).thenReturn(grupoEsperado.getUser2());
        when(mockResultSet.getObject("user3")).thenReturn(grupoEsperado.getUser3());
        when(mockResultSet.getObject("user4")).thenReturn(grupoEsperado.getUser4());
        when(mockResultSet.getObject("user5")).thenReturn(grupoEsperado.getUser5());
        when(mockResultSet.getObject("user6")).thenReturn(grupoEsperado.getUser6());
        when(mockResultSet.getObject("user7")).thenReturn(grupoEsperado.getUser7());
        when(mockResultSet.getObject("user8")).thenReturn(grupoEsperado.getUser8());

        Grupo grupoObtenido = grupoDAO.getById(grupoId);

        assertNotNull(grupoObtenido);
        assertEquals(grupoEsperado.getId(), grupoObtenido.getId());
        assertEquals(grupoEsperado.getServicio(), grupoObtenido.getServicio());
        assertEquals(grupoEsperado.getUser1(), grupoObtenido.getUser1());
        assertEquals(grupoEsperado.getUser2(), grupoObtenido.getUser2());
        assertEquals(grupoEsperado.getUser3(), grupoObtenido.getUser3());
        assertEquals(grupoEsperado.getUser4(), grupoObtenido.getUser4());
        assertEquals(grupoEsperado.getUser5(), grupoObtenido.getUser5());
        assertEquals(grupoEsperado.getUser6(), grupoObtenido.getUser6());
        assertEquals(grupoEsperado.getUser7(), grupoObtenido.getUser7());
        assertEquals(grupoEsperado.getUser8(), grupoObtenido.getUser8());
    }

    @Test
    void obtenerGrupoPorId_DebeRetornarNullSiElGrupoNoExiste() throws SQLException {
        int grupoId = 1;

        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(false);

        Grupo grupoObtenido = grupoDAO.getById(grupoId);

        assertNull(grupoObtenido);
    }

    @Test
    void insertarGrupo_DebeInsertarGrupoCorrectamente() throws SQLException {
        Servicio servicio = new Servicio(1, "Netflix", "DUO", 13.4 ,"https://netflix.es/");
        Usuario user1 = new Usuario(1, "john", "123456", "john@example.com", null, LocalDate.now(), false);
        Usuario user2 = new Usuario(2, "john", "123456", "john@example.com", null, LocalDate.now(), false);
        Usuario user3 = new Usuario(3, "john", "123456", "john@example.com", null, LocalDate.now(), false);
        Usuario user4 = new Usuario(4, "john", "123456", "john@example.com", null, LocalDate.now(), false);
        Usuario user5 = new Usuario(5, "john", "123456", "john@example.com", null, LocalDate.now(), false);
        Usuario user6 = new Usuario(6, "john", "123456", "john@example.com", null, LocalDate.now(), false);
        Usuario user7 = new Usuario(7, "john", "123456", "john@example.com", null, LocalDate.now(), false);
        Usuario user8 = new Usuario(8, "john", "123456", "john@example.com", null, LocalDate.now(), false);

        Grupo grupo = new Grupo(1, servicio, user1, user2, user3, user4, user5, user6, user7, user8);

        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeUpdate()).thenReturn(1);

        boolean insertExitoso = grupoDAO.create(grupo) != null;

        assertTrue(insertExitoso);
        verify(mockStatement, times(1)).setInt(eq(1), eq(grupo.getServicio().getId()));
        verify(mockStatement, times(1)).setInt(eq(2), eq(grupo.getUser1().getId()));
        verify(mockStatement, times(1)).setInt(eq(3), eq(grupo.getUser2().getId()));
        verify(mockStatement, times(1)).setInt(eq(4), eq(grupo.getUser3().getId()));
        verify(mockStatement, times(1)).setInt(eq(5), eq(grupo.getUser4().getId()));
        verify(mockStatement, times(1)).setInt(eq(6), eq(grupo.getUser5().getId()));
        verify(mockStatement, times(1)).setInt(eq(7), eq(grupo.getUser6().getId()));
        verify(mockStatement, times(1)).setInt(eq(8), eq(grupo.getUser7().getId()));
        verify(mockStatement, times(1)).setInt(eq(9), eq(grupo.getUser8().getId()));
        verify(mockStatement, times(1)).executeUpdate();
    }

    @Test
    void eliminarGrupo_DebeEliminarGrupoCorrectamente() throws SQLException {
        Servicio servicio = new Servicio(1, "Netflix", "DUO", 13.4 ,"https://netflix.es/");
        Usuario user1 = new Usuario(1, "john", "123456", "john@example.com", null, LocalDate.now(), false);
        Usuario user2 = new Usuario(2, "john", "123456", "john@example.com", null, LocalDate.now(), false);
        Usuario user3 = new Usuario(3, "john", "123456", "john@example.com", null, LocalDate.now(), false);
        Usuario user4 = new Usuario(4, "john", "123456", "john@example.com", null, LocalDate.now(), false);
        Usuario user5 = new Usuario(5, "john", "123456", "john@example.com", null, LocalDate.now(), false);
        Usuario user6 = new Usuario(6, "john", "123456", "john@example.com", null, LocalDate.now(), false);
        Usuario user7 = new Usuario(7, "john", "123456", "john@example.com", null, LocalDate.now(), false);
        Usuario user8 = new Usuario(8, "john", "123456", "john@example.com", null, LocalDate.now(), false);

        Grupo grupo = new Grupo(1, servicio, user1, user2, user3, user4, user5, user6, user7, user8);

        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeUpdate()).thenReturn(1);
        grupoDAO.delete(grupo);
        verify(mockStatement, times(1)).setInt(eq(1), eq(grupo.getId()));
        verify(mockStatement, times(1)).executeUpdate();
    }

}
