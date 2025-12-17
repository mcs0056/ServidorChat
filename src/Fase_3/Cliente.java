package Fase_3;

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
            //Se establece conexión con el servidor
            Socket socket = new Socket(HOST, PUERTO);

            //Inicialización de flujos de comunicación
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            //Scanner para leer datos introducidos por el cliente
            Scanner s = new Scanner(System.in);

            //Solicitud de nombre del cliente
            System.out.println("Introduce tu nombre: ");
            String nombre = s.nextLine();
            //Se envía el nombre al servidor
            out.writeUTF(nombre);

            //Variable de control para salir del bucle
            boolean salir = false;

            //Bucle principal de comunicación con el servidor
            while(!salir){
                //Se muestra el nombre del cliente registrado anteriormente
                System.out.println(nombre + ": ");
                //Se lee el mensaje del cliente
                String msg = s.nextLine();
                //Se envía el mensaje al servidor
                out.writeUTF(msg);

                //Si el cliente escribe FIN, se termina la comunicación
                if (msg.equalsIgnoreCase("FIN")){
                    salir = true;
                }else{
                    //Se espera y se muestra la respuesta del servidor
                    String respuesta = in.readUTF();
                    System.out.println(respuesta);
                }
            }

            //Cierre de socket y flujos junto a él
            socket.close();

        } catch (IOException e) {
            //Control errores
            System.out.println("Error en el cliente.");
        }
    }
}