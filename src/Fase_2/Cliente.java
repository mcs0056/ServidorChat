package Fase_2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {

        // Host del servidor
        final String HOST = "LocalHost";
        // Puerto del servidor
        final int PORT = 5000;

        Scanner sc = null;
        Socket socket = null;
        DataInputStream in = null;
        DataOutputStream out = null;

        try {
            socket = new Socket(HOST, PORT);

            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            sc = new Scanner(System.in);

            boolean salir = false;

            while (!salir) {
                System.out.print("Escribe un mensaje: ");
                String mensaje = sc.nextLine();

                out.writeUTF(mensaje);

                String respuesta = in.readUTF();
                System.out.println(respuesta);

                if (mensaje.equalsIgnoreCase("FIN")) {
                    salir = true;
                }
            }

            System.out.println("Conexion del cliente terminada");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (sc != null) sc.close();
                if (in != null) in.close();
                if (out != null) out.close();
                if (socket != null) socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
