package com.example.wangyicheng.myapplication;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;


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
            new AdapterView.OnItemClickListener(){
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String dentist = String.valueOf(parent.getItemAtPosition(position));
                //    Toast.makeText(dentistList.this, dentist, Toast.LENGTH_LONG).show();
                    goToPage(dentist);
                }
            }
        );
    }

    public void goToPage(String s){
        Intent i;
        switch (s){
            case "Zhengming Wang":
                i = new Intent(this, PwzmPage.class);
                startActivity(i);
                break;
            // other cases;
        }
    }
}
