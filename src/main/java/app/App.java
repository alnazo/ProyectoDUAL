package app;

import com.dual.proyectoDUAL.dao.email.sender.Sender;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class App {
    //Comentario Prueba Pablo
    public static void main(String[] args) throws
            FileNotFoundException,
            IOException {
        new Sender().send("1234solojuegos@gmail.com", "1234solojuegos@gmail.com", "Contraseña olvidada",
                "Ha olvidado su contraseña", "bjgk");

    }
}
