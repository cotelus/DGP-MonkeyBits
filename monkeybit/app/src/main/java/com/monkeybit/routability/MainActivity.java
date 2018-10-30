package com.monkeybit.routability;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, NavigationView.OnNavigationItemSelectedListener {
    private FirebaseAuth mAuth;
    public BottomNavigationView bottomNavigationView;
    public NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        LoadNewFragment(new MenuActivity());
        bottomNavigationView =  findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(final MenuItem menuItem) {
                Fragment selectedFragment = null;
                switch (menuItem.getItemId()) {
                    case R.id.nav_profile:
                        if (mAuth.getCurrentUser() == null) {
                            selectedFragment = new AccountActivity();
                        } else {
                            selectedFragment = new UserProfileActivity();
                        }
                        break;
                    case R.id.nav_logout:
                        mAuth.signOut();
                        selectedFragment = new MenuActivity();
                        break;
                    case R.id.nav_accesibility:
                        // @TODO: asignar a selectedFragment el Fragmen de opciones de accesibilidad
                        // selectedFragment = new FavActivity();
                        break;
                }
                if (selectedFragment != null) {
                    LoadNewFragment(selectedFragment);
                }
                return true;
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment selectedFragment = null;
        switch (item.getItemId()) {
            case R.id.menu_rutas:
                selectedFragment = new MenuActivity();
                break;
            case R.id.menu_maps:
                // @TODO: asignar a selectedFragment el Fragmen de mapas
                // selectedFragment = new MapsActivity();
                break;
            case R.id.menu_fav:
                // @TODO: asignar a selectedFragment el Fragmen de favoritos
                // selectedFragment = new FavActivity();
                break;
        }
        if (selectedFragment != null) {
            LoadNewFragment(selectedFragment);
        }
        return true;
    }

    public void LoadNewFragment(Fragment newFragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, newFragment).commit();
    }
}

/*
     if (mAuth.getCurrentUser() == null) {
                    selectedFragment = new AccountActivity();
     } else {
                    selectedFragment = new UserProfileActivity();
                }

                */
