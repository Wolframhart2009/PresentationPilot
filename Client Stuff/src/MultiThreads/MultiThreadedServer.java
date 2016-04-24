/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MultiThreads;
import interpreter.Interpreter;
import interpreter.language.VisualBasic;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
/**
 *
 * @author Tom
 */
public class MultiThreadedServer implements Runnable {
   Socket server;
   int port = 13453;
   
   public MultiThreadedServer(Socket server) {
      this.server = server;
   }
    

    @Override
    public void run() {
         try {
         System.out.println("Listening...");
         ServerSocket serverSock = new ServerSocket(port);
         server = serverSock.accept();
         System.out.println("Connected!");
         Interpreter intp = new Interpreter(new VisualBasic());
         BufferedInputStream ustream = new BufferedInputStream(server.getInputStream());
         InputStreamReader sr = new InputStreamReader(ustream);
         
         int c;
         StringBuilder input = new StringBuilder();
         while((c = sr.read() ) != 10){
             input.append( (char) c);
         }
                 
         intp.Interpret(input.toString());
         ustream.close();
         server.close();
      }
      catch (IOException e) {
         System.out.println(e);
      }
    }
}
