package com.example.wangyicheng.myapplication;

import android.content.Intent;
import android.net.http.HttpResponseCache;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
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


public class dentistList extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dentist_list);

        String[] dentists = {"Zhengming Wang", "Leyong Chen", "Xiaoyan Wu", "Jianguo Chen",
                             "Qianhong Xu", "Haiyan Gu", "Chenhua Xu", "Jingyue Liu"};

        ListAdapter denListAdapter = new CustomRow(this, dentists);
    //    ListAdapter denListAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dentists);
        final ListView denList = (ListView) findViewById(R.id.denListView);
        denList.setAdapter(denListAdapter);

        denList.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String dentist = String.valueOf(parent.getItemAtPosition(position));
                        //    Toast.makeText(dentistList.this, dentist, Toast.LENGTH_LONG).show();
                        goToPage(dentist);
                    }
                }
        );
    }

    private void goToPage(final String s){
        class HttpSend extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... str) {
                HttpClient httpclient = new DefaultHttpClient();
                HttpGet httppost = new HttpGet("http://10.0.2.2:80/androidAppServer/getDenList.php");

                try {
                    HttpResponse response = httpclient.execute(httppost);
                    InputStream inputStream = response.getEntity().getContent();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    StringBuilder stringBuilder = new StringBuilder();
                    String bufferedStrChunk = null;
                    while((bufferedStrChunk = bufferedReader.readLine()) != null)
                        stringBuilder.append(bufferedStrChunk);
                    Log.i("debug_yich", stringBuilder.toString());
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
                Log.i("debug_yich","res: "+result);
                if(result.equals("welcome")){
                    Toast.makeText(getApplicationContext(), "welcome to "+s, Toast.LENGTH_LONG).show();
                    getListSucceed(s);
                }else{
                    Toast.makeText(getApplicationContext(), "please log in first!", Toast.LENGTH_LONG).show();
                    getListFail();
                }
            }
        }
        HttpSend httpsd = new HttpSend();
        httpsd.execute(s);
    }

    public void getListSucceed(String s){
        switch (s){
            case "Zhengming Wang":
                Intent i = new Intent(this, PwzmPage.class);
                startActivity(i);
                break;
            // other cases;
        }
    }

    public void getListFail(){
        Intent i = new Intent(this, patient_log_in.class);
        startActivity(i);
    }
}
