package webcam;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

import javax.imageio.ImageIO;
import com.github.sarxos.webcam.Webcam;

public class Camera {

    ServerConexion serverConexion = new ServerConexion();

    public void startCamera() {
        try {
            
            // Obtén la cámara predeterminada
            Webcam webcam = Webcam.getDefault();

            // Abre la cámara
            webcam.open();
    
    
                while (true) {
                    // capture frame
        
                    // convert frame to buffered image
                    BufferedImage image = webcam.getImage();
        
                    // convert buffered image to byte array
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ImageIO.write(image, "jpg", baos);
                    baos.flush();
                    byte[] imageData = baos.toByteArray();
                    baos.close();
        
                    // send image to server
                    serverConexion.sendImage(imageData);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        
    }
}