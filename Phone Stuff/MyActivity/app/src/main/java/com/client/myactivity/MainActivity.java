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

        final Button send = (Button)findViewById(R.id.send_button);
        final Button ip = (Button)findViewById(R.id.ip_button);
        final Button port = (Button)findViewById(R.id.port_button);
        final Button connect = (Button)findViewById(R.id.connect_button);

        editText = (EditText) findViewById(R.id.editText);
        userInput = (EditText) findViewById(R.id.userInput);
        notesView = (TextView) findViewById(R.id.notes_text);
        pageView = (TextView) findViewById(R.id.page_text);


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String message = editText.getText().toString();

                //sends the message to the server
                if (mTcpClient != null) {
                    mTcpClient.sendMessage(message);
                }

                //clear user textataSetChanged();
                editText.setText("");
            }
        });

        ip.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                ipAddress = userInput.getText().toString();
                userInput.setText("");
            }
        });

        port.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                portNum = Integer.parseInt(userInput.getText().toString());
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
                notesView.setText(values[0]);
            }
        }
    }
}