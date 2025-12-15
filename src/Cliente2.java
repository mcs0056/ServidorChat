import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Cliente2 {
    static void main() {
        //Host del servidor
        final String HOST = "127.0.0.2";
        //Puerto del servidor
        final int PUERTO = 5000;
        DataInputStream in = null;
        DataOutputStream out = null;

        try{
            Socket socket = new Socket(HOST, PUERTO);

            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            Scanner s = new Scanner(System.in);

            boolean salir = false;

            while(!salir){
                System.out.println("Cliente: ");
                String msg = s.nextLine();
                out.writeUTF(msg);

                if (msg.equalsIgnoreCase("FIN")){
                    salir = true;
                }else{
                    String respuesta = in.readUTF();
                    System.out.println("Servidor: " + respuesta);
                }
            }
        } catch (IOException e) {
            System.out.println("Error en el cliente.");
        }
    }
}