package com.example.wangyicheng.myapplication;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class patient_log_in extends ActionBarActivity {

    public void GoBack(View view){
        Intent i = new Intent(this,LogIn_1st.class);
        startActivity(i);
    }

    private void sendPostRequest(String acct, String pwd){
        class HttpSend extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... str) {
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost("http://10.0.2.2:80/androidAppServer/patientLogin.php");
                String Acct = str[0]; String Pwd = str[1];
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("postAcct", Acct));
                nameValuePairs.add(new BasicNameValuePair("postPwd", Pwd));

                Log.i("debug_yich", Acct + Pwd);
                try {
                    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    HttpResponse response = httpclient.execute(httppost);
                    InputStream inputStream = response.getEntity().getContent();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    StringBuilder stringBuilder = new StringBuilder();
                    String bufferedStrChunk = null;
                    while((bufferedStrChunk = bufferedReader.readLine()) != null)
                        stringBuilder.append(bufferedStrChunk);
                    return stringBuilder.toString();
                } catch (ClientProtocolException e) {
                    System.out.println(e);
                } catch (IOException e) {
                    System.out.println(e);
                }

                return "Connection failed";
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                if(result.equals("working")){
                    Toast.makeText(getApplicationContext(), "HTTP POST is working...", Toast.LENGTH_LONG).show();
                    logInSucceed();
                }else{
                    Toast.makeText(getApplicationContext(), "Invalid POST req...", Toast.LENGTH_LONG).show();
                    loginInFail();
                }
            }
        }
        HttpSend httpsd = new HttpSend();
        httpsd.execute(acct, pwd);
    }

    private void logInSucceed(){
        Intent i = new Intent(this, dentistList.class);
        startActivity(i);
    }

    private void loginInFail(){
        Intent i = new Intent(this, logInFail.class);
        startActivity(i);
    }

    public void logInPatient(View v) {
        EditText send_txt_acct = (EditText) findViewById(R.id.account);
        EditText send_txt_pswd = (EditText) findViewById(R.id.password);
        String stracct = send_txt_acct.getText().toString();
        String strpwd = send_txt_pswd.getText().toString();
        Log.i("debug_yich", stracct+strpwd);
        sendPostRequest(stracct, strpwd);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_log_in);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_patient_log_in, menu);
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
