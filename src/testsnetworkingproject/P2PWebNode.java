package testsnetworkingproject;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Anton
 */
public class P2PWebNode {
    
    private final IPEndpoint ipEndpoint;
    private final ContentProvider contentProvider;
    private final ContentTracker contentTracker;
    private final ClientGateway clientGateway;
    Lock lock = new ReentrantLock(true);

    public P2PWebNode(String ip, int port) {
        this.ipEndpoint = new IPEndpoint(ip, port);
        this.clientGateway = new ClientGateway();
        this.contentProvider = new ContentProvider();
        this.contentTracker = new ContentTracker();
    }
    
    public void publish(String fileName) {
        
    }
    
    public class ContentTracker {
        
        private final HashMap<String, Providers> providersMap = new HashMap<>();
   
        public boolean peer(String hostname, int port) {
            //TODO
            return false;
        }

        public void publish(String filename) {
            //TODO
        }

        public boolean unpublish() {
            //TODO
            return false;
        }

        public String showPeers() {
            //TODO
            return null;
        }

        public String showMetadata() {
            //TODO
            return null;
        }

        public String showPublished() {
            //TODO
            return null;
        }
        
        public ReachInfo getReachInfo(String messageDigest) {
            Providers providers = providersMap.get(messageDigest);
            if (providers == null)
                return null;
            return providers.getReachInfo(ipEndpoint);
        }
    }
    
    public class ContentProvider {
    
        private final Map<String, Content> contents = new HashMap();

        public void publish(final File file) {
            Content content = new Content(file);
            contents.put(content.getMetadata().getMessageDigest(), content);
        }

        public void unpublish(final String messageDigest) {
            contents.remove(messageDigest);
        }
        
        public File getFile(final String messageDigest) {
            return contents.get(messageDigest).getFile();
        }
        
        public Metadata getMetadata(final String messageDigest) {
            return contents.get(messageDigest).getMetadata();
        }

        /**
         * @return the contents
         */
        public Map<String, Content> getContents() {
            return contents;
        }        
    }
    
    public class ClientGateway {
        
        public static final int OK = 200;
        public static final int NOT_FOUND = 404;
        
        public ClientGateway() {
            init();
        }
        
        private void init() {
            InetSocketAddress isa = new InetSocketAddress(ipEndpoint.getIp(), 
                                                          ipEndpoint.getPort());
            try {
                HttpServer httpServer = HttpServer.create(isa, 0);
                httpServer.createContext("/", new HttpRequestHandler());
                httpServer.setExecutor(null);
                httpServer.start();
            }
            catch (IOException ex) {
                System.out.println("Bad input for hostname and/or port.");
            }
        }        
        
        private class HttpRequestHandler implements HttpHandler {

            @Override
            public void handle(HttpExchange he) throws IOException {
                String messageDigest = he.getRequestURI().getPath().
                        substring(1).toLowerCase();
                ReachInfo reachInfo = contentTracker.getReachInfo(
                        messageDigest);
                if (reachInfo == null) {
                    notFound(he);
                    return;
                }
                if (reachInfo.getIpEndPoint() == ipEndpoint) {
                    File file = contentProvider.getFile(messageDigest);
                    FileInputStream fis = new FileInputStream(file);
                    post(he, fis, reachInfo.getMetadata());
                }
                connectToNodeAndPost(he, reachInfo);
            }
            
            private void post(final HttpExchange he, 
                             final InputStream is, 
                             final Metadata metadata) 
                    throws IOException {
                byte[] bytes = new byte[(int)metadata.getContentLength()];
                BufferedInputStream bis = new BufferedInputStream(is);
                bis.read(bytes, 0, bytes.length);
                Headers headers = he.getResponseHeaders();
                headers.add("Content-Type", metadata.getContentType());
                he.sendResponseHeaders(OK, metadata.getContentLength());
                try (OutputStream os = he.getResponseBody()) {
                    os.write(bytes, 0, bytes.length);
                    os.close();   
                }
            }
            
            private void connectToNodeAndPost(final HttpExchange he, 
                                              final ReachInfo reachInfo) 
                    throws IOException {
                IPEndpoint endPoint = reachInfo.getIpEndPoint();
                Metadata metadata = reachInfo.getMetadata();
                String url = "http://" + endPoint.getIp() + ":" + 
                        endPoint.getPort() + "/" + metadata.getMessageDigest();
                URL obj = new URL(url);
                HttpURLConnection con = (HttpURLConnection)obj.openConnection();
                if (con.getResponseCode() != OK) {
                    notFound(he);
                    return;
                }
                post(he, con.getInputStream(), metadata);
            }

            public void notFound(HttpExchange he) throws IOException {
                String response = NOT_FOUND + " ERROR";
                he.sendResponseHeaders(NOT_FOUND, response.length());
                try (OutputStream outputStream = he.getResponseBody()) {
                    outputStream.write(response.getBytes());
                    outputStream.close();
                }
            }
            
            
        }
    }
}
