package Fase_2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) {

        ServerSocket servidor = null;
        Socket sc = null;
        DataInputStream in = null;
        DataOutputStream out = null;

        final int PUERTO = 5000;

        System.out.println("Servidor iniciado");

        try {
            servidor = new ServerSocket(PUERTO);
            sc = servidor.accept();
            System.out.println("Cliente conectado");

            in = new DataInputStream(sc.getInputStream());
            out = new DataOutputStream(sc.getOutputStream());

            boolean salir = false;

            while (!salir) {
                String mensaje = in.readUTF();
                System.out.println("Cliente: " + mensaje);

                if (mensaje.equalsIgnoreCase("FIN")) {
                    salir = true;
                    out.writeUTF("Conexión terminada");
                } else {
                    out.writeUTF("Servidor: " + mensaje);
                }
            }

            System.out.println("FIN");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
