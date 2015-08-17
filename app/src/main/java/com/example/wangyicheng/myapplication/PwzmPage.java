package com.example.wangyicheng.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class PwzmPage extends ActionBarActivity {
    String sid = "";

    public void goBack(View v){
        Intent i = new Intent(this, dentistList.class);
        i.putExtra("sid", sid);
        startActivity(i);
    }

    private void sendPostRequest(final String sid){
        class HttpSend extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... str) {
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost("http://10.0.2.2:80/androidAppServer/logout.php");
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("sid", sid));

                Log.i("debug_yich", sid);
                try {
                    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    httpclient.execute(httppost);
                } catch (IOException e) {
                    System.out.println(e);
                }
                return "logout";
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                if(result.equals("logout")) {
                    Toast.makeText(getApplicationContext(), "You've logged out!", Toast.LENGTH_LONG).show();
                    loggedout();
                }
            }
        }
        HttpSend httpsd = new HttpSend();
        httpsd.execute(sid);
    }

    private void loggedout(){
        Intent i = new Intent(this, patient_log_in.class);
        startActivity(i);
    }

    public void logout(View v) {
        sendPostRequest(sid);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        sid += bundle.getString("sid");
        setContentView(R.layout.activity_pwzm_page);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_pwzm_page, menu);
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
