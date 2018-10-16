package com.monkeybit.managerapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<String> admins = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void AddManager(String name){
        if(admins.contains(name) == false)
            admins.add(name);
    }

    public void DeleteManager(String name){
        if(admins.contains(name) == true)
            admins.remove(name);

    }

}
