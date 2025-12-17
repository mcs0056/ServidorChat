package Fase_4;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    static void main() {
        //Host del servidor
        final String HOST = "localhost";
        //Puerto del servidor
        final int PUERTO = 5000;
        //Declaración de variables
        DataInputStream in = null;
        DataOutputStream out = null;

        try{
            //Crea socket
            Socket socket = new Socket(HOST, PUERTO);

            //Entrada, salida, sc
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            Scanner s = new Scanner(System.in);

            //Solicita un nombre
            System.out.println("Introduce tu nombre: ");
            String nombre = s.nextLine();
            out.writeUTF(nombre);

            //Variable para salir
            boolean salir = false;

            while(!salir){
                //Nombre de usuario como prefijo
                System.out.println(nombre + ": ");
                //Lee el mensaje escrito
                String msg = s.nextLine();
                //Envía el mensaje
                out.writeUTF(msg);

                //Si el mensaje es FIN, termina la conexión
                if (msg.equalsIgnoreCase("FIN")){
                    salir = true;
                }else{
                    String respuesta = in.readUTF();
                    System.out.println(respuesta);
                }
            }
        } catch (IOException e) {
            //Control de errores
            System.out.println("Error en el cliente.");
        }
    }
}