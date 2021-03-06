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
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class dentist_log_in extends ActionBarActivity {

    public void GoBack(View view){
        Intent i = new Intent(this,LogIn_1st.class);
        startActivity(i);
    }

    private void sendPostRequest(final String acct, final String pwd){
        class HttpSend extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... str) {
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost("http://10.0.2.2:80/androidAppServer/dentistLogin.php");
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("postAcct", acct));
                nameValuePairs.add(new BasicNameValuePair("postPwd", pwd));

                Log.i("debug_yich", acct+pwd);
                try {
                    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    ResponseHandler<String> responseHandler = new BasicResponseHandler();
                    String responseBody = httpclient.execute(httppost,responseHandler);
                    return responseBody;

                    /*
                    HttpResponse response = httpclient.execute(httppost);
                    InputStream inputStream = response.getEntity().getContent();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    StringBuilder stringBuilder = new StringBuilder();
                    String bufferedStrChunk = null;
                    while((bufferedStrChunk = bufferedReader.readLine()) != null)
                        stringBuilder.append(bufferedStrChunk);
                    return stringBuilder.toString();
                    */
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
                if(result.equals("not working")){
                    Toast.makeText(getApplicationContext(), "Log in failed, please try again!", Toast.LENGTH_LONG).show();
                    loginInFail();
                }else{
                    Toast.makeText(getApplicationContext(), "Welcome", Toast.LENGTH_LONG).show();
                    logInSucceed(result);
                    Log.i("debug_yich",result);
                }
            }
        }
        HttpSend httpsd = new HttpSend();
        httpsd.execute(acct, pwd);
    }

    public void logInDentist(View v) {
        EditText send_txt_acct = (EditText) findViewById(R.id.email);
        EditText send_txt_pswd = (EditText) findViewById(R.id.pswd);
        String stracct = send_txt_acct.getText().toString();
        String strpwd = send_txt_pswd.getText().toString();
        Log.i("debug_yich", stracct+strpwd);
        sendPostRequest(stracct, strpwd);
    }

    private void logInSucceed(String jsonRet){
        HashMap<String, String> map = new HashMap<>();
        try {
            JSONObject json = new JSONObject(jsonRet);
            map.put("sid",json.getString("sid"));
            map.put("firstname", json.getString("firstname"));
            map.put("lastname", json.getString("lastname"));
            map.put("info", json.getString("info"));
            map.put("av89", json.getString("av89"));
            map.put("av1011", json.getString("av1011"));
            map.put("av12", json.getString("av12"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Intent i = new Intent(this, DwzmPage.class);
        i.putExtra("sid", map.get("sid"));
        String name = map.get("firstname")+" "+map.get("lastname");
        i.putExtra("name",name);
        i.putExtra("info",map.get("info"));
        i.putExtra("av89",map.get("av89"));
        i.putExtra("av1011",map.get("av1011"));
        i.putExtra("av12",map.get("av12"));
        /*
        switch (name){
            case "Zhenming Wang":
                i.putExtra("info","blabla");
                i.putExtra("av89",true);
                i.putExtra("av1011",true);
                i.putExtra("av12",true);
                break;
            case "Leyong Chen":
                i.putExtra("info","blabla");
                i.putExtra("av89",true);
                i.putExtra("av1011",true);
                i.putExtra("av12",true);
                break;
            // other cases
        }
        */
        startActivity(i);
    }

    private void loginInFail(){
        Intent i = new Intent(this, logInFail.class);
        i.putExtra("id","d");
        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dentist_log_in);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dentist_log_in, menu);
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
