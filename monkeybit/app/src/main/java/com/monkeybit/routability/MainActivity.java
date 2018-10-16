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
            //@TODO: show user"s profile
            Toast toast = Toast.makeText(getApplicationContext(), "Show your profile.", Toast.LENGTH_SHORT);
            toast.show();
        } else {
            LoadActivityWithoutArguments(AccountActivity.class);
        }
    }

    public void OnLogOut(android.view.View view) {
        if (mAuth.getCurrentUser() != null) {
            mAuth.signOut();
            Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.logedout), Toast.LENGTH_SHORT);
            toast.show();
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.logedoutfail), Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    private void LoadActivityWithoutArguments(Class<?> newActivityName) {
        Intent intent = new Intent(this, newActivityName);
        startActivity(intent);
    }
}
