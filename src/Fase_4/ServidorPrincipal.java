package Fase_4;

import Fase_3.Multihilo;

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
            //Creación del servidor
            server = new ServerSocket(PUERTO);
            System.out.println("Servidor concurrente iniciado...");

            while(true){
                //Espera a un cliente
                socket = server.accept();
                //Objeto Multihilos para atender al cliente
                Multihilos multi = new Multihilos(socket);
                //Hilo
                new Thread(multi).start();
            }
        }catch(IOException e){
            //Control de errores
            e.printStackTrace();
        }
    }
}