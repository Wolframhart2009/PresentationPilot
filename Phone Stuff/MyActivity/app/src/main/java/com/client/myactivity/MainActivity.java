package com.client.myactivity;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity
{
    boolean head = true;
    private String ipAddress;
    private int portNum;
    private TCPClient mTcpClient;
    EditText editText;
    EditText userInput;
    TextView notesView;
    TextView pageView;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        final Button ip = (Button)findViewById(R.id.ip_button);
        final Button port = (Button)findViewById(R.id.port_button);
        final Button connect = (Button)findViewById(R.id.connect_button);
        final Button next = (Button)findViewById(R.id.next_button);
        final Button back = (Button)findViewById(R.id.back_button);
        final Button quit = (Button)findViewById(R.id.quit_button);
        final Button start = (Button)findViewById(R.id.start_button);

        userInput = (EditText) findViewById(R.id.userInput);
        notesView = (TextView) findViewById(R.id.notes_text);
        pageView = (TextView) findViewById(R.id.page_text);


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String message = "next\n";

                //sends the message to the server
                if (mTcpClient != null) {
                    mTcpClient.sendMessage(message);
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String message = "back\n";

                //sends the message to the server
                if (mTcpClient != null) {
                    mTcpClient.sendMessage(message);
                }
            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String message = "first\n";

                //sends the message to the server
                if (mTcpClient != null) {
                    mTcpClient.sendMessage(message);
                }
            }
        });

        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String message = "quit\n";

                //sends the message to the server
                if (mTcpClient != null) {
                    mTcpClient.sendMessage(message);
                }
            }
        });

        ip.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                ipAddress = userInput.getText().toString();
                ip.setText(ipAddress);
                userInput.setText("");
            }
        });

        port.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String temp = userInput.getText().toString();
                portNum = Integer.parseInt(temp);
                port.setText(temp);
                userInput.setText("");
            }
        });

        connect.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if (port != null && ipAddress != null){

                    // connect to the server
                    new connectTask().execute("");
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
                    //this method calls the onProgressUpdate
                    publishProgress(message);
                }
            },ipAddress, portNum);
            mTcpClient.run();

            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            //Add code to update the notes here
            if (head){
                pageView.setText(values[0]);
                head = false;
            }
            else{
                head = true;
                notesView.setText(values[0]);
            }
        }
    }
}