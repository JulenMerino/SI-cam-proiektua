import webcam.Camera;
import webcam.PcData;
import webcam.ServerConexion;

import java.awt.EventQueue;

public class Main {
    
    public static void main(String[] args) {  
            ServerConexion server = new ServerConexion();
            server.sendFirstData();
        try {
        EventQueue.invokeLater(new Runnable() {
            
            @Override
            public void run() {
                Camera camera = new Camera();
                // iniciar camara en un nuevo hilo
                new Thread(() -> {
                    camera.startCamera();
                }).start();
            }
        });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
