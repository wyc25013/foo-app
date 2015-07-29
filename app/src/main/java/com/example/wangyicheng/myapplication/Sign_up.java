package com.example.wangyicheng.myapplication;

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
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class Sign_up extends ActionBarActivity {

    private void sendPostRequest(String acct, String pwd, String fn, String ln){
        class HttpSend extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... str) {
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost("http://10.0.2.2:80/androidAppServer/signup.php");
                String Acct = str[0]; String Pwd = str[1];
                String Fn = str[2]; String Ln = str[3];
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("postAcct", Acct));
                nameValuePairs.add(new BasicNameValuePair("postPwd", Pwd));
                nameValuePairs.add(new BasicNameValuePair("postFn", Fn));
                nameValuePairs.add(new BasicNameValuePair("postLn", Ln));

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
                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
            }
        }
        HttpSend httpsd = new HttpSend();
        httpsd.execute(acct, pwd, fn, ln);
    }

    public void submit(View v) {
        EditText send_txt_acct = (EditText) findViewById(R.id.login_email);
        EditText send_txt_pswd = (EditText) findViewById(R.id.login_password);
        EditText send_txt_fn = (EditText) findViewById(R.id.login_fn);
        EditText send_txt_ln = (EditText) findViewById(R.id.login_ln);
        String stracct = send_txt_acct.getText().toString();
        String strpwd = send_txt_pswd.getText().toString();
        String strfn = send_txt_fn.getText().toString();
        String strln = send_txt_ln.getText().toString();
        Log.i("debug_yich", stracct+strpwd);
        sendPostRequest(stracct, strpwd, strfn, strln);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sign_up, menu);
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
