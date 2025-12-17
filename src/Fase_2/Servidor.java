package Fase_2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) {

        //Declaración de variables
        ServerSocket servidor = null;
        Socket sc = null;
        DataInputStream in = null;
        DataOutputStream out = null;

        //Puerto del servidor
        final int PUERTO = 5000;

        System.out.println("Servidor iniciado");

        try {
            //crea socket
            servidor = new ServerSocket(PUERTO);
            //Espera cliente
            sc = servidor.accept();
            System.out.println("Cliente conectado");

            //Entrada, salida
            in = new DataInputStream(sc.getInputStream());
            out = new DataOutputStream(sc.getOutputStream());

            //Variable control salida
            boolean salir = false;

            while (!salir) {
                //Lee el mensaje del cliente
                String mensaje = in.readUTF();
                System.out.println("Cliente: " + mensaje);

                //Si el mensaje es FIN, termina la conexión
                if (mensaje.equalsIgnoreCase("FIN")) {
                    salir = true;
                    //Mensaje de cierre
                    out.writeUTF("Conexión terminada");
                } else {
                    out.writeUTF("Servidor: " + mensaje);
                }
            }

            System.out.println("FIN");

        } catch (IOException e) {
            //Control de errores
            e.printStackTrace();
        }
    }
}
