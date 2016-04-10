import javax.swing.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * The class extends the Thread class so we can receive and send messages at the same time
 */
public class TCPServer extends Thread {


public static void main(String[] args) {
System.out.println("Server Started");
    TCPServer server = new TCPServer();
    server.start();
}

public void start(){
boolean done = false;    
ServerSocket SRVSOCK = null;  
Socket SOCK = null;
InputStreamReader ir = null;
BufferedReader bf = null;

try {
    SRVSOCK = new ServerSocket(4444);
    SOCK = SRVSOCK.accept();
    ir = new InputStreamReader(SOCK.getInputStream());
    bf = new BufferedReader(ir);
}
catch(Exception e){
	System.out.println("ERROR: Connection failed\n");
}
while (!done){

if (bf == null)
done = true;
else{
try  {
  String MESSAGE = bf.readLine();
if (MESSAGE.equals("quit")){
System.out.println("Placeholder for server action: Quit");
done = true;
}
if (MESSAGE.equals("next"))
 System.out.println("Placeholder for server action: Next");
if (MESSAGE.equals("back"))
 System.out.println("Placeholder for server action: Back");
if (MESSAGE.equals("first"))
 System.out.println("Placeholder for server action: First");
if (MESSAGE.equals("last"))
 System.out.println("Placeholder for server action: Last");

    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();    
    }
}
}   
}
}