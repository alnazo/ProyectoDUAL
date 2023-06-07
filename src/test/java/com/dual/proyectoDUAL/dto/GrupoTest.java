package com.dual.proyectoDUAL.dto;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.Mockito.*;

public class GrupoTest {

    @Mock
    private ResultSet resultSetMock;

    @Before
    public void setup() throws SQLException {
        MockitoAnnotations.openMocks(this);
        when(resultSetMock.getInt("id")).thenReturn(1);
        when(resultSetMock.getInt("servicio")).thenReturn(2);
        when(resultSetMock.getInt("user1")).thenReturn(3);
        when(resultSetMock.getInt("user2")).thenReturn(4);
        when(resultSetMock.getObject("user3")).thenReturn(null);
        when(resultSetMock.getObject("user4")).thenReturn(5);
        when(resultSetMock.getObject("user5")).thenReturn(6);
        when(resultSetMock.getObject("user6")).thenReturn(null);
        when(resultSetMock.getObject("user7")).thenReturn(null);
        when(resultSetMock.getObject("user8")).thenReturn(7);
    }

    @Test
    public void constructor_FromResultSet_PopulatesFields() throws SQLException {
        Grupo grupo = new Grupo(resultSetMock);

        Assert.assertEquals(1, grupo.getId());
        Assert.assertEquals(2, grupo.getServicio());
        Assert.assertEquals(3, grupo.getUser1());
        Assert.assertEquals(4, grupo.getUser2());
        Assert.assertNull(grupo.getUser3());
        Assert.assertEquals(Integer.valueOf(5), grupo.getUser4());
        Assert.assertEquals(Integer.valueOf(6), grupo.getUser5());
        Assert.assertNull(grupo.getUser6());
        Assert.assertNull(grupo.getUser7());
        Assert.assertEquals(Integer.valueOf(7), grupo.getUser8());
    }
}
