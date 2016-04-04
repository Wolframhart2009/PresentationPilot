/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;
import java.net.*;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.POIOLE2TextExtractor;
import org.apache.poi.POITextExtractor;
import org.apache.poi.extractor.ExtractorFactory;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xslf.extractor.XSLFPowerPointExtractor;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.hslf.extractor.PowerPointExtractor;
import org.apache.poi.hslf.usermodel.HSLFSlideShow;
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
    public static void main(String[] args) throws InvalidFormatException, OpenXML4JException, XmlException {
       try{
           socket = new ServerSocket(port);
           System.out.println("Server Initialized");
           System.out.println("IP Address is "+ InetAddress.getLocalHost().getHostAddress());
           System.out.println("Port Number is "+ port);
           readPowerPoint("test");
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
    public static void readPowerPoint(String pptName) throws IOException, InvalidFormatException, OpenXML4JException, XmlException
    {

        FileInputStream is = new FileInputStream(pptName+".ppt");
        POIFSFileSystem fileSystem = new POIFSFileSystem(is);
        POIOLE2TextExtractor oleTextExtractor = ExtractorFactory.createExtractor(fileSystem);
        PowerPointExtractor ppe = (PowerPointExtractor) oleTextExtractor;
        System.out.println("Text: " + ppe.getText());
         System.out.println("Notes: " + ppe.getNotes());
        System.out.println("opened ppt");

    }
}
