package com.example.wangyicheng.myapplication;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;


public class MyActivity extends ActionBarActivity {

    private static final String TAG = "YICHWANG'S MSG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        Log.i(TAG, "onCreate");

        Button signIn = (Button) findViewById(R.id.sign_in);
        Button signUp = (Button) findViewById(R.id.sign_up);
        Button guest = (Button) findViewById(R.id.guest);

        signIn.setOnClickListener(
            new Button.OnClickListener(){
                public void onClick(View v){
                    TextView logIn = (TextView) findViewById(R.id.log_in);
                    logIn.setText("change!");
                }
            }
        );

        signIn.setOnLongClickListener(
            new Button.OnLongClickListener() {
                public boolean onLongClick(View v) {
                    TextView logIn = (TextView) findViewById(R.id.log_in);
                    logIn.setText("so long!");
                    return true;
                }
            }
        );

        signUp.setOnClickListener(
            new Button.OnClickListener() {
                public void onClick(View v) {
                    sendOutBroadcast(v);
                }
            }
        );

        guest.setOnClickListener(
            new Button.OnClickListener(){
                public void onClick(View v){
                    // to do
                }
            }
        );
    }

    public void sendOutBroadcast(View view){
        Intent i = new Intent();
        i.setAction("com.example.wangyicheng.myapplication");
        i.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        sendBroadcast(i);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
    }


    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstanceState");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(TAG, "onRestoreInstanceState");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my, menu);
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
