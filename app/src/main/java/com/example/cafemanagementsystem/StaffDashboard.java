package com.example.cafemanagementsystem;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import helper.MyHelper;
import model.KOT;

public class StaffDashboard extends AppCompatActivity {

    private ListView kotItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_dashboard);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        LoadKot();

        kotItemList = findViewById(R.id.kotItemList);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(StaffDashboard.this, MainActivity.class);//keep add activity class in mainactivity
                StaffDashboard.this.startActivity(i);
            }
        });
    }

    private void LoadKot() {
        final MyHelper myHelper = new MyHelper(this);
        final SQLiteDatabase sqLiteDatabase = myHelper.getWritableDatabase();;

        List<KOT> kotList = new ArrayList<>();

        kotList = myHelper.GetAllKot(sqLiteDatabase);
        HashMap<String, String> kot = new HashMap<>();
        for(int i = 0; i< kotList.size(); i++){
            kot.put(Integer.toString(kotList.get(i).getKotId()), kotList.get(i).getTable());
        }
        ArrayAdapter<String> kotArrayAdapter = new ArrayAdapter<>(
                this,android.R.layout.simple_list_item_1,
                new ArrayList<String>(kot.values())
        );
        kotItemList.setAdapter(kotArrayAdapter);
        //only one value can be accessible.
    }

}
