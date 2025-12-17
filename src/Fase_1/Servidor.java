package Fase_1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    static void main() {

        //Declaración de las variables
        ServerSocket server = null;
        Socket socket = null;
        DataInputStream in = null;
        DataOutputStream out = null;

        //Puerto del servidor
        final int PUERTO = 5000;

        try{
            //Se crea el ServerSocket con el puerto indicado
            server = new ServerSocket(PUERTO);
            System.out.println("Servidor a la espera");

            //Servidor permanece en ejecución indefinidamente
            while(true){
                //Espera a que un cliente se conecte
                socket = server.accept();
                System.out.println("Cliente detectado. El servidor se va a dormir.");

                //Simula una tarea larga
                try{
                    Thread.sleep(15000);//15 segundos de bloqueo
                }catch(InterruptedException e){
                    e.printStackTrace();
                }

                System.out.println("Cliente atendido con éxito después de la siesta.");
                //Inicialización de flujos de comunicación
                in = new DataInputStream(socket.getInputStream());
                out = new DataOutputStream(socket.getOutputStream());

                //Lectura de mensaje enviado por cliente
                String msg = in.readUTF();
                System.out.println("Mensaje recibido: " + msg);

                //Envío de respuesta al cliente
                out.writeUTF("Saludos desde el Servidor.");

                //Cierre de socket y flujos junto a él
                socket.close();
                System.out.println("Conexión terminada.");
            }

        } catch (IOException e) {
            //Control errores
            throw new RuntimeException(e);
        }
    }
}