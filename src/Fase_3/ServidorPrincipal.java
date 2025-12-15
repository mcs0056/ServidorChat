package Fase_3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorPrincipal {
    static void main() {
        ServerSocket server = null;
        Socket socket = null;
        DataInputStream in = null;
        DataOutputStream out = null;

        final int PUERTO = 5000;

        try{
            server = new ServerSocket(PUERTO);
            System.out.println("Servidor concurrente iniciado...");

            while(true){
                socket = server.accept();
                Multihilo multi = new Multihilo(socket);
                new Thread(multi).start();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}