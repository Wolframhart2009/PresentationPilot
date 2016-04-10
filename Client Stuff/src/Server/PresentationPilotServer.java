/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;
import interpreter.Interpreter;
import interpreter.language.VisualBasic;
import java.net.*;
import java.io.*;
import org.apache.poi.POIOLE2TextExtractor;
import org.apache.poi.extractor.ExtractorFactory;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.hslf.extractor.PowerPointExtractor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.xmlbeans.XmlException;
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
    static Interpreter intp;
    
    public static void main(String[] args) throws InvalidFormatException, OpenXML4JException, XmlException, IOException {
       //String[] notes = getNotes("test");
       try{
           intp = new Interpreter(new VisualBasic());
           socket = new ServerSocket(port);
           System.out.println("Server Initialized");
           System.out.println("IP Address is "+ InetAddress.getLocalHost().getHostAddress());
           System.out.println("Port Number is "+ port);
           
           connection = socket.accept();
           System.out.println("Hello!");
           int character;
           while(true){
           BufferedInputStream inputStream = new BufferedInputStream(connection.getInputStream());
           InputStreamReader streamReader = new InputStreamReader(inputStream);
           process = new StringBuffer();
           while((character = streamReader.read())!=10){
               process.append((char)character);
           }
           System.out.println(process);
           //If you needed to wait for the app to do something after connection
           //we could put a wait block here
//           TimeStamp = new java.util.Date().toString();
//           String returnCode = "Server responded at"+ TimeStamp + (char)13;
//           BufferedOutputStream outputStream = new BufferedOutputStream(connection.getOutputStream());
//           OutputStreamWriter streamWriter = new OutputStreamWriter(outputStream,"US-ASCII");
//           streamWriter.write(returnCode);
//           streamWriter.flush();
           
           intp.Interpret(process.toString());
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
    public static String[] getNotes(String pptName) throws IOException, InvalidFormatException, OpenXML4JException, XmlException
    {

        FileInputStream is = new FileInputStream(pptName+".ppt");
        POIFSFileSystem fileSystem = new POIFSFileSystem(is);
        POIOLE2TextExtractor oleTextExtractor = ExtractorFactory.createExtractor(fileSystem);
        PowerPointExtractor ppe = (PowerPointExtractor) oleTextExtractor;
        String ppeNotes = ppe.getNotes();
        String[] notes = new String[1024];
        int k = 0;
        int j = 0;
        for(int i = 0;i<ppe.getNotes().length()-2;i++)
        {
            if(ppeNotes.charAt(i)=='\n')
            {
               if(ppeNotes.charAt(i+1)=='*')
               {
                   if(ppeNotes.charAt(i+2)=='\n')
                   {
                      notes[k]= ppeNotes.substring(j, i);
                      k++;
                      j=i+3;
                      i+=3;
                   }
               }
            }
        }
        for(int i = 0; i<notes.length;i++)
        {
            if(notes[i]==null)
            {
              i=notes.length;   
            }
            else{
            System.out.println(notes[i]);}
        }
        return notes;
    }
}
