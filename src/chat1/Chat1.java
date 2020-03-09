package chat1;

import static chat1.JFrame1.is;
import static chat1.JFrame1.nick;
import static chat1.JFrame1.os;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author cliente
 */
public class Chat1 {
    
    public static void main(String args[]) {
        try {
            nick = JOptionPane.showInputDialog("Escriba su nombre de usuario:");
            System.out.println("Creando socket cliente");
            Socket clienteSocket = new Socket();
            System.out.println("Estableciendo la conexión");
            
            InetSocketAddress addr = new InetSocketAddress("192.168.0.1", 5555);
            clienteSocket.connect(addr);
            
            JFrame1 chat = new JFrame1();
            chat.setVisible(true);
            chat.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            
            is = clienteSocket.getInputStream();
            os = clienteSocket.getOutputStream();
            
            os.write(nick.getBytes());
            
            do {
                chat.recibirMensaje();
            } while(chat.recibirMensaje()!="1");
            
            chat.setVisible(false);
            System.exit(0);
            
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
