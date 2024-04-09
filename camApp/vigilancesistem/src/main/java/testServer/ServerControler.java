package testServer;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ServerControler {

    final int SERVER_PORT = 8889;

    private byte[] buffer = new byte[65508];

    private ServerSocket serverSocket;

    private JFrame frame = new JFrame("Server Controler");

    private JLabel cameraScreen;

    public ServerControler() {

        frame.setLayout(null);

        cameraScreen = new JLabel();
        cameraScreen.setBounds(0, 0, 640, 480);
        frame.add(cameraScreen);


        frame.setSize(640, 560);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

    public void startSockert() {
        try {
            serverSocket = new ServerSocket(SERVER_PORT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * camarak jsona hondo bidali duen testeatzeko metodoa
     * @return json
     */
    public String lortuJson() {
        try {
            Socket socket = serverSocket.accept();
            InputStream inputStream = socket.getInputStream();

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] tempBuffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(tempBuffer)) != -1) {
                baos.write(tempBuffer, 0, bytesRead);
            }
            return new String(baos.toByteArray());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * camarak irudia hondo bidali duen testeatzeko metodoa
     * @return irudia
     */
    public ImageIcon lortuIrudia() {
        try {
            Socket socket = serverSocket.accept();
            InputStream inputStream = socket.getInputStream();

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] tempBuffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(tempBuffer)) != -1) {
                baos.write(tempBuffer, 0, bytesRead);
            }
            buffer = baos.toByteArray();

            ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
            BufferedImage imagen = ImageIO.read(bais);

            if (imagen != null) {
                return new ImageIcon(imagen);
            } else {
                System.err.println("La imagen recibida es nula");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        ServerControler irudiak = new ServerControler();
        
        irudiak.startSockert();
        while (true) {
            String a = irudiak.lortuJson();
            if ( a != null) {
               System.out.println(a); 
            }
        }

        
        // while (true) {
        //     ImageIcon icon = irudiak.lortuIrudia();
        //     irudiak.cameraScreen.setIcon(icon);
        // }
    }
}