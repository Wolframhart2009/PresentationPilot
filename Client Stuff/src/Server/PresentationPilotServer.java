/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import GUI.ServerGUI;
import MultiThreads.MultiThreadedServer;
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
import javax.swing.SwingUtilities;

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
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ServerGUI().setVisible(true);
            }
        });
    }

    public static void startSocketServer() throws IOException {
        Socket temp = new Socket();
        System.out.println("Server Initialized");
        System.out.println("IP Address is " + InetAddress.getLocalHost().getHostAddress());
        System.out.println("Port Number is " + port);
        new Thread(new MultiThreadedServer(temp)).start();

    }

    public static void startBluetoothServer() {
        Thread waitThread = new Thread(new WaitThread());
        waitThread.start();
    }

    public static String[] getNotes(String pptName) throws IOException, InvalidFormatException, OpenXML4JException, XmlException {

        FileInputStream is = new FileInputStream(pptName + ".ppt");
        POIFSFileSystem fileSystem = new POIFSFileSystem(is);
        POIOLE2TextExtractor oleTextExtractor = ExtractorFactory.createExtractor(fileSystem);
        PowerPointExtractor ppe = (PowerPointExtractor) oleTextExtractor;
        String ppeNotes = ppe.getNotes();
        String[] notes = new String[1024];
        int k = 0;
        int j = 0;
        for (int i = 0; i < ppe.getNotes().length() - 2; i++) {
            if (ppeNotes.charAt(i) == '\n') {
                if (ppeNotes.charAt(i + 1) == '*') {
                    if (ppeNotes.charAt(i + 2) == '\n') {
                        notes[k] = ppeNotes.substring(j, i);
                        notes[k] = notes[k].trim();
                        k++;
                        j = i + 2;
                        i += 1;
                    }
                }
            }
        }
        for (int i = 0; i < notes.length; i++) {
            if (notes[i] == null) {
                i = notes.length;
            } else {
                System.out.println(notes[i]);
            }
        }
        return notes;
    }
}
