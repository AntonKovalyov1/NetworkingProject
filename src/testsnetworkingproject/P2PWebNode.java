package testsnetworkingproject;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Anton
 */
public class P2PWebNode {
    
    private final ContentProvider contentProvider = new ContentProvider();
    private ContentTracker contentTracker;
    private ClientGateway clientGateway;

    public P2PWebNode(String ip, int port) {
        
    }
    
    public class ContentTracker {
   
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
        
        private IPEndpoint getIPEndpoint(String messageDigest) {
            Metadata local = contentProvider.getMetadata(messageDigest);
            if (local == null) {
                
            }
            //TODO
            return null;
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
        
        public ClientGateway(final String hostname, final int port) {
            init(hostname, port);
        }
        
        private void init(final String hostname, final int port) {
            InetSocketAddress isa = new InetSocketAddress(hostname, port);
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
                String s = he.getRequestURI().getPath().substring(1).
                        toLowerCase();
                
            }

            public void notFound(HttpExchange he) throws IOException {
                String response = "404 ERROR";
                he.sendResponseHeaders(404, response.length());
                try (OutputStream outputStream = he.getResponseBody()) {
                    outputStream.write(response.getBytes());
                    outputStream.close();
                }
            }
        }
    }
}
