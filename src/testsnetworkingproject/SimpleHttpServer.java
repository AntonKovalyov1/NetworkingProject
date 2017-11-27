/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testsnetworkingproject;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;

/*
 * a simple static http server
*/
public class SimpleHttpServer {
//
//  public static void main(String[] args) throws Exception {
//    HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
//    server.createContext("/test", new MyHandler());
//    server.setExecutor(null); // creates a default executor
//    server.start();
//  }
//
//  static class MyHandler implements HttpHandler {
//    public void handle(HttpExchange t) throws IOException {
//      byte [] response = "Welcome Real's HowTo test page".getBytes();
//      t.sendResponseHeaders(200, response.length);
//      OutputStream os = t.getResponseBody();
//      os.write(response);
//      os.close();
//    }
//  }
// 
//    private static final int fNumberOfThreads = 100;
//    private static final Executor fThreadPool = Executors.newFixedThreadPool(fNumberOfThreads);
// 
//    public static void main(String[] args) throws IOException
//    {
//        ServerSocket socket = new ServerSocket(81);
//        while (true)
//        {
//            final Socket connection = socket.accept();
//            Runnable task = new Runnable()
//            {
//                @Override
//                public void run()
//                {
//                    HandleRequest(connection);
//                }
//            };
//            fThreadPool.execute(task);
//        }
//    }
// 
//    private static void HandleRequest(Socket s)
//    {
//        BufferedReader in;
//        PrintWriter out;
//        String request;
// 
//        try
//        {
//            String webServerAddress = s.getInetAddress().toString();
//            System.out.println("New Connection:" + webServerAddress);
//            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
// 
//            request = in.readLine();
//            System.out.println("--- Client request: " + request);
// 
//            out = new PrintWriter(s.getOutputStream(), true);
//            out.println("HTTP/1.0 200");
//            out.println("Content-type: text/html; image/png");
//            out.println("Server-name: myserver");
//            //String response = "test passed";
//            File response = new File("logo.png");
//            //out.println("Content-length: " + response.);
//            out.println("");
//            out.println(response);
//            out.flush();
//            out.close();
//            s.close();
//        }
//        catch (IOException e)
//        {
//            System.out.println("Failed respond to client request: " + e.getMessage());
//        }
//        finally
//        {
//            if (s != null)
//            {
//                try
//                {
//                    s.close();
//                }
//                catch (IOException e)
//                {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return;
//    }
        static final int responseCode_OK = 200;
        static final int responseCode_Not_Found = 404;
 
    public static void main(String[] args) {
        String ip = "";
        try {
            ip = InetAddress.getLocalHost().getHostAddress();
        }
        catch (Exception ex) {
            //System.out.println(ip);
        }
        InetSocketAddress isa = new InetSocketAddress("192.168.1.72", 8000);
        System.out.println(isa.getHostName());
        try {
            HttpServer httpServer = HttpServer.create(isa, 0);
//            httpServer.createContext("/", new MyHttpHandler());
            httpServer.createContext("/html", new GetHttpHandlerText());
            httpServer.createContext("/", new GetHttpHandlerImage());
            httpServer.setExecutor(null);
            httpServer.start();
            
        } catch (IOException ex) {
            System.out.println("bad input for ip and port");
        }
    }
     
    static class MyHttpHandler implements HttpHandler {
 
        @Override
        public void handle(HttpExchange he) throws IOException {
            System.out.println("test"); 
            String response = "404 ERROR";
            he.sendResponseHeaders(responseCode_Not_Found, response.length());
             
            OutputStream outputStream = he.getResponseBody();
            outputStream.write(response.getBytes());
            outputStream.close();
        }
    }
     
    static class GetHttpHandlerImage implements HttpHandler{
 
        @Override
        public void handle(HttpExchange he) throws IOException {
 
            System.out.println(he.getRequestURI().getPath());
            Headers headers = he.getResponseHeaders();
            headers.add("Content-Type", "image/png");
             
            File file = new File ("logo.png");
            byte[] bytes  = new byte [(int)file.length()];
            System.out.println(file.getAbsolutePath());
            System.out.println("length:" + file.length());
             
            FileInputStream fileInputStream = new FileInputStream(file);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            bufferedInputStream.read(bytes, 0, bytes.length);
 
            he.sendResponseHeaders(responseCode_OK, file.length());
            OutputStream outputStream = he.getResponseBody();
            outputStream.write(bytes, 0, bytes.length);
            outputStream.close();
        }
    }
    
    static class GetHttpHandlerText implements HttpHandler{
 
        @Override
        public void handle(HttpExchange he) throws IOException {
 
            Headers headers = he.getResponseHeaders();
            headers.add("Content-Type", "text/html");
             
            File file = new File ("google.html");
            byte[] bytes  = new byte [(int)file.length()];
            System.out.println(file.getAbsolutePath());
            System.out.println("length:" + file.length());
             
            FileInputStream fileInputStream = new FileInputStream(file);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            bufferedInputStream.read(bytes, 0, bytes.length);
 
            he.sendResponseHeaders(responseCode_OK, file.length());
            OutputStream outputStream = he.getResponseBody();
            outputStream.write(bytes, 0, bytes.length);
            outputStream.close();
        }
    }
}
