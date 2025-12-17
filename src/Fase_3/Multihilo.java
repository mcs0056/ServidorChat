package Fase_3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Multihilo implements Runnable{

    //Socket asociado a un cliente concreto
    private Socket socket;

    //Recibe el socket del cliente
    public Multihilo(Socket socket){
        this.socket = socket;
    }

    //Se ejecuta cuando el hilo comience
    @Override
    public void run(){
        //Nombre del cliente (por defecto)
        String nombre = "Desconocido";

        //Declaración de variables
        DataInputStream in = null;
        DataOutputStream out = null;

        try{
            //Inicialización de flujos de comunicación
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            //Se lee el nombre del cliente
            nombre = in.readUTF(); //Lee el nombre del cliente
            System.out.println("Cliente conectado: " + nombre);

            //Variable de control para salir del bucle
            boolean salir = false;

            //Bucle principal de comunicación con el servidor
            while(!salir){
                //Lectura del mensaje enviado por el cliente
                String msg = in.readUTF();
                System.out.println(nombre + ": " + msg);

                //Si el cliente escribe FIN, se termina la conexión
                if(msg.equalsIgnoreCase("FIN")){
                    salir = true;
                }else{
                    //Se envía la respuesta del servidor
                    out.writeUTF("Servidor > " + msg);
                }
            }

            //Cierre de socket y flujos junto a él
            socket.close();

        }catch (IOException e){
            //Control errores
            e.printStackTrace();
        }
    }
}