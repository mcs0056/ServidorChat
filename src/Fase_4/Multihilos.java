package Fase_4;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

public class Multihilos implements Runnable {

    private Socket socket;

    //Constructor que recibe el socket del cliente
    public Multihilos(Socket socket){
        this.socket = socket;
    }

    //Cuando se inicia el hilo:
    @Override
    public void run() {
        //Nombre del cliente
        String nombre = "Desconocido";

        //Entrada, salida
        DataInputStream in = null;
        DataOutputStream out = null;

        try {
            //Inicia los flujos
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            // Lee el nombre del cliente
            nombre = in.readUTF();

            // Mostrar la IP del cliente al conectarse
            System.out.println("Cliente conectado: " + nombre + " desde " + socket.getInetAddress());

            //Variable para salir
            boolean salir = false;

            while(!salir){
                //Lee el mensaje del cliente
                String msg = in.readUTF();
                System.out.println(nombre + ": " + msg);

                //Si el mensaje es FIN, termina la conexión
                if(msg.equalsIgnoreCase("FIN")){
                    salir = true;
                } else {
                    //Respuesta del servidor
                    out.writeUTF("Servidor > " + msg);
                }
            }
        } catch (EOFException | SocketException e) {
            //Control de desconexiones inesperadas
            System.out.println("El cliente se ha desconectado inesperadamente");
        } catch (IOException e) {
            //Control errores
            e.printStackTrace();
        } finally {
            //Cierra el socket
            try {
                if (socket != null) socket.close();
            } catch (IOException e) {
                //Control errores
                e.printStackTrace();
            }
        }
    }
}
