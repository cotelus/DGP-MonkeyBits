package com.monkeybit.routability;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private FirebaseAuth mAuth;
    public BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        LoadNewFragment(new MenuActivity());
        bottomNavigationView =  findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment selectedFragment = null;
        switch (item.getItemId()) {
            case R.id.menu_profile:
                //OnLookProfile(item.getActionView());
                if (mAuth.getCurrentUser() == null) {
                    selectedFragment = new AccountActivity();
                } else {
                    selectedFragment = new UserProfileActivity();
                }
                break;
            case R.id.menu_rutas:
                selectedFragment = new MenuActivity();
                break;
        }
        if (selectedFragment != null) {
            LoadNewFragment(selectedFragment);
        }
        return true;
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

    public void LoadNewFragment(Fragment newFragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, newFragment).commit();
    }
}
