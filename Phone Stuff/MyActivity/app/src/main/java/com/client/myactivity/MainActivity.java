/*
*
*
*/
package com.client.myactivity;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class MainActivity extends Activity
{
    String test = "Before Calling";

    private TCPClient mTcpClient;
    private static String ip;
    private static String port;

    public static void setPort(String temp){
        port = temp;
    }

    public static void setIP(String temp){
        ip = temp;
    }

    public static String getPort(){
        return port;
    }

    public static String getIP(){
        return ip;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        final EditText editText = (EditText) findViewById(R.id.editText);
        editText.setText("Beginning Client");

        Button connectB = (Button)findViewById(R.id.connect_button);
        Button nextB = (Button)findViewById(R.id.next_button);
        Button backB = (Button)findViewById(R.id.back_button);
        Button lastB = (Button)findViewById(R.id.last_button);
        Button firstB = (Button)findViewById(R.id.first_button);
        Button quitB = (Button)findViewById(R.id.quit_button);

        //Bunch of buttons
        connectB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    //Get the IP from the EditText box

                    EditText ipText = (EditText) findViewById(R.id.ip_text);
                    setIP(ipText.getText().toString());

                    //Get the Port from the EditText box
                    final EditText portText = (EditText) findViewById(R.id.port_text);
                    setPort(portText.getText().toString());
                    editText.setText(editText.getText() + "\n\nTest 15");

                    // connect to the server
                    new connectTask().execute("");

                    String message = "Client Connected";
                    message.concat("\n");
                    //sends the message to the server
                    if (mTcpClient != null) {
                        mTcpClient.sendMessage(message);
                    }
                } catch (Exception e) {
                    editText.setText(editText.getText() + "\n\nFailed to connect to server");
                }
            }
        });
        nextB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String message = "next";
                message.concat("\n");

                //sends the message to the server
                if (mTcpClient != null) {
                    mTcpClient.sendMessage(message);
                }
            }
        });
        backB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String message = "back";
                message.concat("\n");

                //sends the message to the server
                if (mTcpClient != null) {
                    mTcpClient.sendMessage(message);
                }
            }
        });
        firstB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String message = "first";
                message.concat("\n");

                //sends the message to the server
                if (mTcpClient != null) {
                    mTcpClient.sendMessage(message);
                }
            }
        });
        lastB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String message = "last";
                message.concat("\n");

                //sends the message to the server
                if (mTcpClient != null) {
                    mTcpClient.sendMessage(message);
                }
            }
        });
        quitB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String message = "quit";
                message.concat("\n");

                //sends the message to the server
                if (mTcpClient != null) {
                    mTcpClient.sendMessage(message);
                    mTcpClient.stopClient();
                }

            }
        });
    }

    public class connectTask extends AsyncTask<String,String,TCPClient> {
        @Override
        protected TCPClient doInBackground(String... message) {

            //we create a TCPClient object and
            mTcpClient = new TCPClient(new TCPClient.OnMessageReceived() {
                @Override
                //here the messageReceived method is implemented
                public void messageReceived(String message) {
                    updateScreen(message);
                }
            });
            mTcpClient.run();

            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);

        }
    }

    public static class TCPClient {

        public String SERVERIP;
        public int SERVERPORT;
        private String serverMessage;
        private OnMessageReceived mMessageListener = null;
        private boolean mRun = false;

        PrintWriter out;
        BufferedReader in;

        /**
         *  Constructor of the class. OnMessagedReceived listens for the messages received from server
         */
        public TCPClient(OnMessageReceived listener) {
            mMessageListener = listener;
        }

        /**
         * Sends the message entered by client to the server
         * @param message text entered by client
         */
        public void sendMessage(String message){
            if (out != null && !out.checkError()) {
                out.println(message);
                out.flush();
            }
        }

        public void stopClient(){
            mRun = false;
        }


        public void run() {
            SERVERIP = String.valueOf(getIP());
            SERVERPORT = Integer.parseInt(getPort());
            mRun = true;

            try {
                //create a socket to make the connection with the server
                Socket socket = new Socket(SERVERIP, SERVERPORT);

                try {

                    //send the message to the server
                    out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);

                    //receive the message which the server sends back
                    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                    //in this while the client listens for the messages sent by the server
                    while (mRun) {
                        serverMessage = in.readLine();

                        if (serverMessage != null && mMessageListener != null) {
                            //call the method messageReceived from MyActivity class
                            mMessageListener.messageReceived(serverMessage);
                        }
                        serverMessage = null;
                    }

                    Log.e("RESPONSE FROM SERVER", "S: Received Message: '" + serverMessage + "'");

                } catch (Exception e) {

                } finally {
                    //the socket must be closed. It is not possible to reconnect to this socket
                    // after it is closed, which means a new socket instance has to be created.
                    socket.close();
                }

            } catch (Exception e) {

                Log.e("TCP", "C: Error", e);

            }

        }

        //The method messageReceived(String message) must be implemented in the MyActivity
        //class in an asynckTask doInBackground
        public interface OnMessageReceived {
            public void messageReceived(String message);
        }
    }

    public void updateScreen(String message){
        setContentView(R.layout.main);
        final EditText editText = (EditText) findViewById(R.id.editText);

        editText.setText(editText.getText() + "From update Screen: " + message);
    }
}