package webcam;

import java.net.InetAddress;
import java.net.NetworkInterface;

public class PcData {

    private static PcData instace;

    private String id;
    private String ip;
    private String liseningPort;
    private String sendingPort;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getIp() {
        return ip;
    }
    public void setIp(String ip) {
        this.ip = ip;
    }
    public String getLiseningPort() {
        return liseningPort;
    }
    public void setLiseningPort(String liseningPort) {
        this.liseningPort = liseningPort;
    }
    public String getSendingPort() {
        return sendingPort;
    }
    public void setSendingPort(String sendingPort) {
        this.sendingPort = sendingPort;
    }

    public String toJson () {
        return "{ \"id\": \"" + id + "\", \"ip\": \"" + ip + "\", \"liseningPort\": \"" + liseningPort + "\", \"sendingPort\": \"" + sendingPort + "\" }";
    }
    
    private PcData() {
        try {
        
        InetAddress temp = InetAddress.getLocalHost();
        NetworkInterface network = NetworkInterface.getByInetAddress(temp);

        byte[] mac = network.getHardwareAddress();

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < mac.length; i++) {
                sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
            }
            System.out.println("Dirección MAC de tu PC es : " + sb.toString());

            this.ip = InetAddress.getLocalHost().getHostAddress();
            this.id = sb.toString();
            this.liseningPort = "6969";
            this.sendingPort = "8888";

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Singleton patroia erabilita pcData instazia bakarra egoteko metodoa da hau
     * @return pcData instazia
     */
    public static PcData getInstance() {
        if (instace == null) {
            instace = new PcData();
        }
        return instace;
    }
}
