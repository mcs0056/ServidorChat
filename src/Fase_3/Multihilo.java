package Fase_3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Multihilo implements Runnable{

    private Socket socket;

    public Multihilo(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run(){
        String nombre = "Desconocido";

        DataInputStream in = null;
        DataOutputStream out = null;

        try{
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            nombre = in.readUTF(); //Lee el nombre del cliente
            System.out.println("Cliente conectado: " + nombre);

            boolean salir = false;

            while(!salir){
                String msg = in.readUTF();
                System.out.println(nombre + ": " + msg);

                if(msg.equalsIgnoreCase("FIN")){
                    salir = true;
                }else{
                    out.writeUTF("Servidor > " + msg);
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}