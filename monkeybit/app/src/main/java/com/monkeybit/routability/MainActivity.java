package com.monkeybit.routability;

import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, NavigationView.OnNavigationItemSelectedListener, AlertDialogResponseInterface {
    private FirebaseAuth mAuth;
    public BottomNavigationView bottomNavigationView;
    public NavigationView navigationView;
    public DrawerLayout mainDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        LoadNewFragment(new MenuActivity());
        mainDrawerLayout = findViewById(R.id.main_drawer);
        bottomNavigationView =  findViewById(R.id.NavigationViewRutes);
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
                        onLogOut();
                        selectedFragment = new MenuActivity();
                        break;
                    case R.id.nav_accesibility:
                        // @TODO: asignar a selectedFragment el Fragmen de opciones de accesibilidad
                        // selectedFragment = new FavActivity();
                        break;
                }
                if (selectedFragment != null) {
                    mainDrawerLayout.closeDrawer(navigationView);
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
                selectedFragment = new RouteActivity();
                break;
            case R.id.menu_maps:
                // @TODO: asignar a selectedFragment el Fragmen de mapas
                // selectedFragment = new MapsActivity();
                break;
            case R.id.menu_fav:
                // @TODO: asignar a selectedFragment el Fragmen de favoritos
                //@TODO: estoy hay que cambiarlo
                 selectedFragment = new FavActivity();
                break;
        }
        if (selectedFragment != null) {
            LoadNewFragment(selectedFragment);
        }
        return true;
    }


    public void OnShowOptionMenu(View view) {
        if (!(mainDrawerLayout.isDrawerOpen(navigationView))) {
            mainDrawerLayout.openDrawer(navigationView);
        }
    }

    public void LoadNewFragment(Fragment newFragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, newFragment).commit();
    }

    public void onLogOut() {
        if (mAuth.getCurrentUser() != null) {
            this.newAlertDialog(this, AlertID.LOGOUT, getString(R.string.close_session_dialog));
        } else {
            Toast toast = Toast.makeText(this, getString(R.string.logged_out_fail), Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void newAlertDialog(final AlertDialogResponseInterface caller, final AlertID alertID, String dialog) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle(R.string.alert_dialog_tittle);
        alertDialog.setCancelable(false);
        alertDialog.setMessage(getString(R.string.dialog_alert_question_body) + " " + dialog + "?");
        alertDialog.setPositiveButton(R.string.alert_dialog_positive, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                caller.PositiveResponse(alertID);
            }
        });
        alertDialog.setNegativeButton(R.string.alert_dialog_negative, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                caller.NegativeResponse(alertID);
            }
        });
        alertDialog.show();
    }

    @Override
    public void PositiveResponse(AlertID alertID) {
        switch (alertID) {
            case LOGOUT:
                logOut();
                break;
        }
    }

    @Override
    public void NegativeResponse(AlertID alertID) {
        switch (alertID) {
            case LOGOUT:
                break;
        }
    }

    public void logOut() {
        if (mAuth.getCurrentUser() != null) {
            mAuth.signOut();
            Toast toast = Toast.makeText(this, getString(R.string.logged_out), Toast.LENGTH_SHORT);
            toast.show();
        } else {
            Toast toast = Toast.makeText(this, getString(R.string.logged_out_fail), Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}

