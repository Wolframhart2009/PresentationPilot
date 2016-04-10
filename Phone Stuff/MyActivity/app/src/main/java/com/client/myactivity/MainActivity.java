/*
 5
down vote


Here's what worked for me - I got the same error: "Internal HTTP server disabled: Cannot start internal HTTP server. Git integration, JavaScript debugger and LiveEdit may operate with errors. Please check your firewall settings and restart Android Studio"

Run command prompt as administrator - type cmd in the 'search programs and files' text box in the Windows Start menu; right-click and select 'Run as administrator'.

At the command prompt, type netsh winsock reset

Restart your PC

This worked for me.

Just to mention: I also added Android Studio and Gradle folders to my virus protection exceptions in case it was being blocked, but that didn't resolve the problem; the problem was only resolved once I followed the steps above.



*
*
*/
package com.client.myactivity;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity
{
    private String ipTemp;
    private TCPClient mTcpClient;
    private String ip;
    private int port;
    public static final String PREFS_NAME = "MyPrefsFile";
    public void setPort(int port){
        this.port = port;
    }

    public void setIP(String ip){
        this.ip = ip;
    }

    public int getPort(){
        return this.port;
    }

    public String getIP(){
        return this.ip;
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

        // connect to the server

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
                    int temp = Integer.valueOf(portText.getText().toString());

                    editText.setText(editText.getText() + "\n\nConnecting To Server");

                    new connectTask().execute("");
                    String message = "Client Connected";
                    message.concat("\n");
                    //sends the message to the server
                    if (mTcpClient != null) {
                        mTcpClient.sendMessage(message);
                    }
                    editText.setText(editText.getText() + "\n\nConnected To Server");
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
                }
            });
            mTcpClient.run(getIP(), getPort());

            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);

        }
    }
}