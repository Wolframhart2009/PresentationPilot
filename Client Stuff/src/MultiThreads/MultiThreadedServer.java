/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MultiThreads;
import interpreter.Interpreter;
import interpreter.language.VisualBasic;
import java.io.IOException;
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
         serverSock.accept();
         System.out.println("Connected!");
         Interpreter intp = new Interpreter(new VisualBasic());    
         PrintStream pstream = new PrintStream(server.getOutputStream());
         intp.Interpret(pstream.toString());
         pstream.close();
         server.close();
      }
      catch (IOException e) {
         System.out.println(e);
      }
    }
}
