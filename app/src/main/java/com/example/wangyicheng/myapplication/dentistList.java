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


public class dentistList extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dentist_list);
        Bundle bundle = getIntent().getExtras();
        final String sid = bundle.getString("sid");

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
                    //    String dentist = String.valueOf(parent.getItemAtPosition(position));
                    //    Toast.makeText(dentistList.this, dentist, Toast.LENGTH_LONG).show();
                        String eid = "";
                        switch (position){
                            case 0:
                                eid += "ZhenmingWang@kangjie.com";
                                break;
                            case 1:
                                eid += "leyong@kangjie.com";
                                break;
                            // other cases
                        }
                        goToPage(eid,sid);
                    }
                }
        );
    }

    private void goToPage(final String eid, final String sid){
        class HttpSend extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... str) {
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost("http://10.0.2.2:80/androidAppServer/getDenList.php");
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("sid", sid));
                nameValuePairs.add(new BasicNameValuePair("eid", eid));

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
                    Log.i("debug_yich", stringBuilder.toString());
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
                Log.i("debug_yich","res: "+result);
                if(result.equals("please log in!")){
                    Toast.makeText(getApplicationContext(), "please log in first!", Toast.LENGTH_LONG).show();
                    getListFail();
                }else{
                    Toast.makeText(getApplicationContext(), "welcome!", Toast.LENGTH_LONG).show();
                    getListSucceed(result);
                }
            }
        }
        HttpSend httpsd = new HttpSend();
        httpsd.execute(eid,sid);
    }

    public void getListSucceed(String jsonRet){
        Intent i = new Intent(this, PwzmPage.class);
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
        i.putExtra("sid", map.get("sid"));
        i.putExtra("name",map.get("firstname")+" "+map.get("lastname"));
        i.putExtra("info",map.get("info"));
        i.putExtra("av89",map.get("av89"));
        i.putExtra("av1011",map.get("av1011"));
        i.putExtra("av12",map.get("av12"));
        startActivity(i);
    }

    public void getListFail(){
        Intent i = new Intent(this, patient_log_in.class);
        startActivity(i);
    }
}
