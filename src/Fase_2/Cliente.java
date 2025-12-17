package Fase_2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {

        // Host del servidor
        final String HOST = "LocalHost";
        // Puerto del servidor
        final int PORT = 5000;

        //Declaración de variables
        Scanner sc = null;
        Socket socket = null;
        DataInputStream in = null;
        DataOutputStream out = null;

        try {
            //Crear y conectar socket al sevidor
            socket = new Socket(HOST, PORT);

            //Entrada, salida y sc
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            sc = new Scanner(System.in);

            //Variable para salir
            boolean salir = false;

            while (!salir) {
                //Solicitar escribir mensaje
                System.out.print("Escribe un mensaje: ");
                String mensaje = sc.nextLine();

                //Envía mensaje al servidor
                out.writeUTF(mensaje);

                //Recibe respuesta del servidor
                String respuesta = in.readUTF();
                System.out.println(respuesta);

                //Caso de que mensaje sea FIN, finaliza
                if (mensaje.equalsIgnoreCase("FIN")) {
                    salir = true;
                }
            }

            System.out.println("Conexion del cliente terminada");

        } catch (IOException e) {
            //Control errores
                e.printStackTrace();
        }
    }
}

