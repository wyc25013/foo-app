package com.example.wangyicheng.myapplication;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;


public class CommunicateWithServer extends ActionBarActivity {

    private class HttpSend extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... str) {
            // from avd, you can always access local machine with 10.0.2.2
            String get_url = "http://10.0.2.2:80/androidAppServer/appserver.php?q=" + str[0].replace(" ", "%20");
            try {
            //    Log.i("debug_yich", "h1");
                HttpClient Client = new DefaultHttpClient();
            //    Log.i("debug_yich", "h2");
                HttpGet httpget = new HttpGet(get_url);
            //    Log.i("debug_yich", "h3");
                ResponseHandler<String> responseHandler = new BasicResponseHandler();
            //    Log.i("debug_yich", "h4");
                String content = Client.execute(httpget, responseHandler);
                Log.i("debug_yich", content);
                return content;
            } catch(Exception e) {
                System.out.println(e);
            }
            return "Cannot Connect to: " + get_url;
        }

        @Override
        protected void onPostExecute(String result) {
            TextView tv = (TextView) findViewById(R.id.show_text);
            tv.setText(result);
        }
    }

    public void on_send_click(View v) {
        EditText send_txt = (EditText) findViewById(R.id.senddata_txt);
        HttpSend httas = new HttpSend();
        httas.execute(send_txt.getText().toString());

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_communicate_with_server);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_communicate_with_server, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
