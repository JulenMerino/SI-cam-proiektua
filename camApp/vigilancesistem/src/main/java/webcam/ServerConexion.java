package webcam;

import java.io.OutputStream;
import java.net.Socket;

public class ServerConexion {

    final String SERVER_IP = "127.0.0.1";
    final int VIDEO_SERVER_PORT = 8888;
    final int DATA_SERVER_PORT = 8889;

    Socket socket;

    /**
     * Metodo honek irudi bat tcp bidez bidaltzen du
     * 
     * @param imageData irudia byte array bezala formateatuta
     */
    public void sendImage(byte[] imageData) {
        try {

            socket = new Socket(SERVER_IP, VIDEO_SERVER_PORT);
            // Datuak bidaltzeko OutputStream motako aldagaila sortu
            OutputStream outputStream = socket.getOutputStream();

            // Irudia byte moduan bidali
            outputStream.write(imageData);

            // OutputStream itxi
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendFirstData() {
        try {
            socket = new Socket(SERVER_IP, DATA_SERVER_PORT);
            // Datuak bidaltzeko OutputStream motako aldagaila sortu
            OutputStream outputStream = socket.getOutputStream();

            // Irudia byte moduan bidali
            outputStream.write(PcData.getInstance().toJson().getBytes());

            // OutputStream itxi
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}