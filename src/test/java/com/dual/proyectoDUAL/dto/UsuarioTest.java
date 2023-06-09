package com.dual.proyectoDUAL.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import jakarta.servlet.ServletContext;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UsuarioTest {
    @Mock
    private ResultSet resultSetMock;

    @Mock
    private ServletContext servletContextMock;

    private Usuario usuario = Mockito.mock(Usuario.class);

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        usuario = new Usuario();
    }

    @Test
    public void constructor_ResultSet_ObjectCreatedWithResultSetValues() throws SQLException {
        when(resultSetMock.getInt("id")).thenReturn(1);
        when(resultSetMock.getString("username")).thenReturn("lau");
        when(resultSetMock.getString("password")).thenReturn("123456");
        when(resultSetMock.getString("email")).thenReturn("lau.cg@gmail.com");
        when(resultSetMock.getString("img_perfil")).thenReturn("lauImagen.jpg");
        when(resultSetMock.getDate("nacimiento")).thenReturn(java.sql.Date.valueOf(LocalDate.now()));
        when(resultSetMock.getBoolean("admin")).thenReturn(true);

        usuario = new Usuario(resultSetMock);

        assertEquals(1, usuario.getId());
        assertEquals("lau", usuario.getUsername());
        assertEquals("123456", usuario.getPassword());
        assertEquals("lau.cg@gmail.com", usuario.getEmail());
        assertEquals("lauImagen.jpg", usuario.getImagen());
        assertEquals(LocalDate.now(), usuario.getNacimiento());
        assertTrue(usuario.getAdmin());
    }

    //TODO REVISAR ESTOS TEST

    @Test
    public void sourceImagen_ExistingImage_ReturnsImagePath() {
        ServletContext servletContext = Mockito.mock(ServletContext.class);
        Mockito.when(servletContext.getRealPath(Mockito.anyString())).thenReturn("/ruta/imagen.jpg");

        Usuario usuario = Usuario.builder()
                .id(1)
                .username("lau")
                .password("123456")
                .email("lau.cg@gmail.com")
                .imagen("lauImagen.jpg")
                .nacimiento(LocalDate.of(2000, 1, 3))
                .admin(true)
                .build();

        String result = usuario.sourceImagen((jakarta.servlet.ServletContext) servletContext);
        verify(servletContext).getRealPath("/img/usuarios/1/imagen.jpg");
        assertEquals("/img/usuarios/1/imagen.jpg", result);
    }

@Test
    public void sourceImagen_NonExistingImage_ReturnsNull() {
        usuario.setId(1);
        usuario.setImagen("nonexisting.jpg");
        when(servletContextMock.getRealPath("/img/usuarios/1/nonexisting.jpg")).thenReturn("/path/to/nonexisting.jpg");
        when(new File("/path/to/nonexisting.jpg").exists()).thenReturn(false);
        when(usuario.getImagen()).thenReturn("imagen.jpg");

        String imagePath = usuario.sourceImagen((jakarta.servlet.ServletContext) servletContextMock);

        assertNull(imagePath);
    }
}
