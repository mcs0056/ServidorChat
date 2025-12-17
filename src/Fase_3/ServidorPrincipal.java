package Fase_3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorPrincipal {
    static void main() {
        //Declaración de variables
        ServerSocket server = null;
        Socket socket = null;
        DataInputStream in = null;
        DataOutputStream out = null;

        //Puerto del servidor
        final int PUERTO = 5000;

        try{
            //Se crea el ServerSocket con el puerto indicado
            server = new ServerSocket(PUERTO);
            System.out.println("Servidor concurrente iniciado...");

            //Servido activo indefinidamente
            while(true){
                //Espera a que un cliente se conecte
                socket = server.accept();
                //Creación de objeto Multihilo para atender al cliente
                Multihilo multi = new Multihilo(socket);
                //Lanzamiento de nuevo hilo para ese cliente
                new Thread(multi).start();
            }
        }catch(IOException e){
            //Control de errores
            e.printStackTrace();
        }
    }
}