package app;

import com.dual.proyectoDUAL.email.sender.Sender;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class App {
    //Comentario Prueba Pablo
    public static void main(String[] args) throws FileNotFoundException, IOException {
        System.out.println("Hola mundo");

        File[] adjuntos = new File[] {
                new File("Check.png"),
                new File("gato.jpg")
        };

        new Sender().send("1234solojuegos@gmail.com", "1234solojuegos@gmail.com", "Para mi hermanita del pompón", "<h3>Hola bu, he hecho un metodo para enviar emails</h3>", Arrays.toString(adjuntos));

    }
}
