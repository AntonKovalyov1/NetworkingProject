package testsnetworkingproject;

import java.io.File;
import java.io.IOException;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author Anton
 */
public class TestsNetworkingProject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SocketException {
//        Enumeration e = NetworkInterface.getNetworkInterfaces();
//        while (e.hasMoreElements()) {
//            NetworkInterface n = (NetworkInterface) e.nextElement();
//            Enumeration ee = n.getInetAddresses();
//            while (ee.hasMoreElements()) {
//                InetAddress i = (InetAddress) ee.nextElement();
////                if (i.isSiteLocalAddress())
//                    System.out.println(i.getHostAddress());
//            }
//        }
        try {
            String messageDigest = new DigestUtils("SHA1").digestAsHex(new File("WR.png"));
            System.out.println(messageDigest);
            
        } catch (IOException ex) {
            Logger.getLogger(TestsNetworkingProject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
