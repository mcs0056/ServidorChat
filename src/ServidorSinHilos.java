import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorSinHilos {
    static void main() {
        ServerSocket server = null;
        Socket socket = null;
        DataInputStream in = null;
        DataOutputStream out = null;

        final int PUERTO = 5000;

        String nombre = "Desconocido";

        try{
            server = new ServerSocket(PUERTO);
            System.out.println("Servidor iniciado...");

            socket = server.accept();
            Thread.sleep(15000);

            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            nombre = in.readUTF();//Lee el nombre del cliente

            System.out.println("Cliente conectado: " + nombre);

            boolean salir = false;

            while (!salir) {
                String mensaje = in.readUTF();
                System.out.println(nombre + ": " + mensaje);

                if (mensaje.equalsIgnoreCase("FIN")) {
                    salir = true;
                } else {
                    out.writeUTF("Eco: " + mensaje);
                }
            }

            socket.close();

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}