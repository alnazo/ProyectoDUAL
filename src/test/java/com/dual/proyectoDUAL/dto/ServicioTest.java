package com.dual.proyectoDUAL.dto;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.Mockito.*;

public class ServicioTest {

    @Mock
    private ResultSet resultSetMock;

    @Before
    public void setup() throws SQLException {
        MockitoAnnotations.openMocks(this);
        when(resultSetMock.getInt("id")).thenReturn(1);
        when(resultSetMock.getString("nombre")).thenReturn("Test Service");
        when(resultSetMock.getDouble("precio")).thenReturn(9.99);
        when(resultSetMock.getString("web")).thenReturn("www.testservice.com");
    }

    @Test
    public void constructor_FromResultSet_PopulatesFields() throws SQLException {
        Servicio servicio = new Servicio(resultSetMock);

        Assert.assertEquals(1, servicio.getId());
        Assert.assertEquals("Test Service", servicio.getNombre());
        Assert.assertEquals(9.99, servicio.getPrecio(), 0.01);
        Assert.assertEquals("www.testservice.com", servicio.getWeb());
    }
}
