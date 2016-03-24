/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentationpilotserver;
import java.net.*;
import java.io.*;
import java.util.*;
/**
 *
 * @author Tom
 */
public class PresentationPilotServer {

    /**
     * @param args the command line arguments
     */
    static ServerSocket socket;
    protected final static int port = 13453;
    static Socket connection;
    
    static boolean first;
    static StringBuffer process;
    static String TimeStamp;
    public static void main(String[] args) {
        /*String host = "localhost";
        int port = 19876;
        
        StringBuffer instr = new StringBuffer();
        String TimeStamp;
        try{
            InetAddress address = InetAddress.getByName(host);
            Socket connection = new Socket(address,port);
        }
        catch(IOException e){
            System.err.println("IOException: "+e.getMessage());
        }*/
       try{
           socket = new ServerSocket(port);
           System.out.println("Server Initialized");
           int character;
       while(true){
           connection = socket.accept();
           BufferedInputStream inputStream = new BufferedInputStream(connection.getInputStream());
           InputStreamReader streamReader = new InputStreamReader(inputStream);
           process = new StringBuffer();
           while((character = streamReader.read())!=13){
               process.append((char)character);
           }
           System.out.println(process);
           //If you needed to wait for the app to do something after connection
           //we could put a wait block here
           TimeStamp = new java.util.Date().toString();
           String returnCode = "Server responded at"+ TimeStamp + (char)13;
           BufferedOutputStream outputStream = new BufferedOutputStream(connection.getOutputStream());
           OutputStreamWriter streamWriter = new OutputStreamWriter(outputStream,"US-ASCII");
           streamWriter.write(returnCode);
           streamWriter.flush();
        }
       }
       catch(IOException e){
           System.err.printf("IOException: "+e.getMessage());
       }
       try{
           connection.close();
       }
       catch(IOException e)
       {
        System.err.printf("IOException closing: "+e.getMessage());   
       }
    }
}
