package com.monkeybit.routability;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DBTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dbtest);

        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.scenario, new DBTestFragment()).commit();
    }
}
