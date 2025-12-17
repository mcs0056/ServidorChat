package Fase_1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Cliente2 {
    public static void main(String[] args) {
        //Host del servidor
        final String HOST = "localhost";
        //Puerto del servidor
        final int PUERTO = 5000;

        //Declaración de las variables
        Socket socket = null;
        DataInputStream in = null;
        DataOutputStream out = null;

        try{
            //Mensaje informativo por consola
            System.out.println("Intentando conectar con el servidor...");
            //Conexión con servidor
            socket = new Socket(HOST, PUERTO);

            //Inicialización flujo de entrada para recibir datos del servidor
            in = new DataInputStream(socket.getInputStream());
            //Inicialización flujo de salida para enviar datos del servidor
            out = new DataOutputStream(socket.getOutputStream());

            //Envío de mensaje al servidor
            out.writeUTF("Hola, soy el cliente 2.");

            //Espera de respuesta
            String msg = in.readUTF();
            //Respuesta recibida
            System.out.println("Respuesta del servidor: " + msg);

            //Cierre de socket y flujos junto a él
            socket.close();

        } catch (IOException e) {
            //Recoge y muestra cualquier posible error
            System.out.println("Error: " + e.getMessage());
        }
    }
}