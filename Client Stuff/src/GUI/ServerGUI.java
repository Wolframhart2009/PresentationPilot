package GUI;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import server.PresentationPilotServer;

public class ServerGUI extends JFrame {
	/**
	 * The text area which is used for displaying logging information.
	 */
	private JTextArea textArea;
	
	private JButton buttonSocket = new JButton("Java Socket");
	private JButton buttonBluetooth = new JButton("Bluetooth");
	
	private PrintStream standardOut;
	
	public ServerGUI() {
		super("Presentation Pilot Server");
		
		textArea = new JTextArea(50, 10);
		textArea.setEditable(false);
		PrintStream printStream = new PrintStream(new CustomOutputStream(textArea));
		
		// keeps reference of standard output stream
		standardOut = System.out;
		
		// re-assigns standard output stream and error output stream
		System.setOut(printStream);
		System.setErr(printStream);

		// creates the GUI
		setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.insets = new Insets(10, 10, 10, 10);
		constraints.anchor = GridBagConstraints.WEST;
		
		add(buttonSocket, constraints);
		
		constraints.gridx = 1;
		add(buttonBluetooth, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 2;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 1.0;
		constraints.weighty = 1.0;
		
		add(new JScrollPane(textArea), constraints);
		
		buttonSocket.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
                            try {
                                PresentationPilotServer.startSocketServer();
                            } catch (IOException ex) {
                                Logger.getLogger(ServerGUI.class.getName()).log(Level.SEVERE, null, ex);
                            }
			}
		});
		
		buttonBluetooth.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
					PresentationPilotServer.startBluetoothServer();	
			}
		});
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(480, 320);
		setLocationRelativeTo(null);	// centers on screen
	}
	
	/**
	 * Prints log statements for testing in a thread
	 */
	private void printLog() {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException ex) {
						ex.printStackTrace();
					}
				}
			}
		});
		thread.start();
	}
}