package com.monkeybit.routability;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
    }

    public void OnLookProfile(android.view.View view) {
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser != null) {
            LoadActivityWithoutArguments(UserProfileActivity.class);
        } else {
            LoadActivityWithoutArguments(AccountActivity.class);
        }
    }

    private void LoadActivityWithoutArguments(Class<?> newActivityName) {
        Intent intent = new Intent(this, newActivityName);
        startActivity(intent);
    }
}
