package Fase_1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Cliente2 {
    static void main() {
        final String HOST = "localhost";
        final int PUERTO = 5000;

        Socket socket = null;
        DataInputStream in = null;
        DataOutputStream out = null;

        try{
            System.out.println("Intentando conectar con el servidor...");
            socket = new Socket(HOST, PUERTO);

            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            out.writeUTF("Hola, soy el cliente 2.");

            String msg = in.readUTF();
            System.out.println("Respuesta del servidor: " + msg);

            socket.close();

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}