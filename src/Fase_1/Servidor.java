package Fase_1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    static void main() {

        ServerSocket server = null;
        Socket socket = null;
        DataInputStream in = null;
        DataOutputStream out = null;

        final int PUERTO = 5000;

        try{
            server = new ServerSocket(PUERTO);
            System.out.println("Servidor a la espera");

            while(true){
                socket = server.accept();
                System.out.println("Fase_4.Cliente detectado. El servidor se va a dormir.");

                try{
                    Thread.sleep(15000);//15 segundos de bloqueo
                }catch(InterruptedException e){
                    e.printStackTrace();
                }

                System.out.println("Fase_4.Cliente atendido con éxito después de la siesta.");
                in = new DataInputStream(socket.getInputStream());
                out = new DataOutputStream(socket.getOutputStream());

                //Lectura de mensaje
                String msg = in.readUTF();
                System.out.println("Mensaje recibido: " + msg);

                //Envío de mensaje
                out.writeUTF("Saludos desde el Servidor.");

                //Cierre socket
                socket.close();
                System.out.println("Conexión terminada.");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}