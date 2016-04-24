/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MultiThreads;
import interpreter.Interpreter;
import interpreter.language.VisualBasic;
import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
/**
 *
 * @author Tom
 */
public class MultiThreadedServer implements Runnable {
   Socket server;
   int port = 13453;
   Interpreter intp;
   
   public MultiThreadedServer(Socket server) {
      this.server = server;
   }
    

    @Override
    public void run() {
         try {
             while(true){
                 System.out.println("Listening...");
                 ServerSocket serverSock = new ServerSocket(port);
                 server = serverSock.accept();
                 System.out.println("Connected!");
                 
                 intp = new Interpreter(new VisualBasic());
                    
                     
                 while(true){
                     BufferedInputStream ustream = new BufferedInputStream(server.getInputStream());
                     InputStreamReader sr = new InputStreamReader(ustream);

                     PrintWriter mOut = new PrintWriter(new BufferedWriter(new OutputStreamWriter(server.getOutputStream())), true);
                     
                     String temp[] = intp.getCurrentNotes();
                     if(temp != null){
                         String title = temp[0];
                         String notes  = temp[1];
                         if(title != null){
                            mOut.println(title); //Send Title of slide
                         }

                         if(notes != null){
                            mOut.println(notes); //Send notes about slide
                         }
                     }
                     mOut.flush();
                     
                     
                     int c;
                     StringBuilder input = new StringBuilder();
                     while((c = sr.read() ) != 10 && input.length() < 256){
                         input.append( (char) c);
                     }

                     if(input.length() >= 256){
                         break;
                     }
                     
                     intp.Interpret(input.toString());
                     
                     //System.out.println("Input: " + input.toString());
                     
                     temp = intp.getCurrentNotes();
                     if(temp != null){
                         String title = temp[0];
                         String notes  = temp[1];
                         if(title != null){
                            mOut.println(title); //Send Title of slide
                         }

                         if(notes != null){
                            mOut.println(notes); //Send notes about slide
                         }
                     }
                     mOut.flush();
                  }
                 server.close();
                 serverSock.close();
            }
      }
      catch (IOException e) {
         intp.releaseErrorLog();
      }
    }
}
