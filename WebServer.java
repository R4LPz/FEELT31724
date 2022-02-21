import java.io.*;
import java.net.*;
import java.util.*;

public final class WebServer {
    public static void main(String[] args) throws Exception{
        int port = 3000;
        try{
            ServerSocket server = new ServerSocket(port);
            while (true){
                Socket client = server.accept();
                HttpRequest request = new HttpRequest(client);
                Thread thread = new Thread(request);
                thread.start();
            }
        }catch (Exception e) {
            System.out.println(e);
        }
    }
}

final class HttpRequest implements Runnable{
    final static String CRLF = "\r\n";
    Socket socket;

    public HttpRequest(Socket socket) throws Exception{
        this.socket = socket;
    }

    public void run(){
        try {
            processRequest();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    private void processRequest() throws Exception{
        InputStream is =  socket.getInputStream();
        DataOutputStream os = new DataOutputStream(socket.getOutputStream());

        InputStreamReader ir = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(ir);
        String requestLine = br.readLine();

        System.out.println();
        System.out.println(requestLine);


        String headerLine = null;
        while ((headerLine = br.readLine()).length() != 0){
            System.out.println(headerLine);
        }

        StringTokenizer tokens = new StringTokenizer(requestLine);
        tokens.nextToken();


        String fileName = tokens.nextToken();
        fileName = "./assets" +fileName;

        FileInputStream fis = null;
        Boolean fileExists = true;

        try {
            fis = new FileInputStream(fileName);
        }catch (FileNotFoundException e){
            fileExists = false;
        }

        String statusLine = null;
        String contentTypeLine = null;
        String entityBody = null;

        if (fileExists){
            statusLine = "HTTP/1.0 200 OK"+ CRLF;
            contentTypeLine = "Content-type: " + contentType(fileName) + CRLF;
        }else{
            statusLine = "HTTP/1.0 404 Not Found " + CRLF;
            contentTypeLine = "Content-type: text/html"+ CRLF;
            entityBody = "<HTML>" +
                    "<HEAD><TITLE>not found</TITLE><STYLE>h1,h3{display:flex;justify-content:center;align-items:center;}</STYLE></HEAD>" +
                    "<BODY><h1>not found</h1><div style=\"display:flex; justify-content: center;\"><img src=\"old-man-computer.gif\"/></div></BODY></HTML>";;
        }


        os.writeBytes(statusLine);
        os.writeBytes(contentTypeLine);
        os.writeBytes(CRLF);

        if (fileExists){
            sendBytes(fis,os);
            fis.close();
        }else{
            os.writeBytes(entityBody);
        }

        os.close();
        br.close();
        socket.close();
    }

    private static String contentType(String fileName) {
        if(fileName.endsWith(".htm") || fileName.endsWith(".html")){
            return "text/html";
        }
        if(fileName.endsWith(".jpg")){
            return "image/jpg";
        }
        if(fileName.endsWith(".gif")){
            return "image/gif";
        }
        if(fileName.endsWith(".pdf")){
            return "application.pdf/jpg";
        }
        return "application/ocet-strem";
    }

    private static void sendBytes(FileInputStream fis, DataOutputStream os) throws Exception{
        byte[] buffer = new byte[1024];
        int bytes = 0;
        while ((bytes = fis.read(buffer)) != -1){
            os.write(buffer, 0, bytes);
        }
    }
}
