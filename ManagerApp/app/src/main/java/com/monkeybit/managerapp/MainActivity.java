package com.monkeybit.managerapp;


import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    List<String> admins = new ArrayList<String>();
    private EditText textIDManager;
    private TextView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textIDManager = findViewById(R.id.textIDManager); //the field to type the id of the manager
        list = findViewById(R.id.list_admin); //the list of admins
        setButtonDelete();
        setButtonAdd();

    }

    private void AddManager(String name){
        if(admins.contains(name) == false)
            admins.add(name);
    }

    private void DeleteManager(String name){
        if(admins.contains(name) == true)
            admins.remove(name);

    }

    private void RefreshList(){
        //change the list
    }

    private void setButtonDelete() {
        // Reference the speak button
        Button speakButton = findViewById(R.id.delete);

        // Set up click listener
        speakButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the text typed in by the user
                String text = textIDManager.getText().toString();
                //call the function
                DeleteManager(text);

            }
        });

    }

    private void setButtonAdd() {
        // Reference the speak button
        Button speakButton = findViewById(R.id.delete);

        // Set up click listener
        speakButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the text typed in by the user
                String text = textIDManager.getText().toString();
                //call the function
                AddManager(text);

            }
        });

    }

}
