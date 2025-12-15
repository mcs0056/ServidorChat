package Fase_4;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

public class Multihilo implements Runnable {

    private Socket socket;

    public Multihilo(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        String nombre = "Desconocido";

        DataInputStream in = null;
        DataOutputStream out = null;

        try {
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            // Mostrar la IP del cliente al conectarse
            System.out.println("Cliente conectado desde: " + socket.getInetAddress());

            nombre = in.readUTF(); // Lee el nombre del cliente
            System.out.println("Cliente conectado: " + nombre);

            boolean salir = false;

            while(!salir){
                String msg = in.readUTF();
                System.out.println(nombre + ": " + msg);

                if(msg.equalsIgnoreCase("FIN")){
                    salir = true;
                } else {
                    out.writeUTF("Servidor > " + msg);
                }
            }
        } catch (EOFException | SocketException e) {
            System.out.println("El cliente se ha desconectado inesperadamente");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (socket != null) socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
