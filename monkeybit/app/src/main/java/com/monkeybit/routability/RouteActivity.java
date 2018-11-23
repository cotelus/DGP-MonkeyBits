package com.monkeybit.routability;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

//@TODO : no crea bien
public class RouteActivity extends Fragment implements BottomNavigationView.OnNavigationItemSelectedListener {
    public BottomNavigationView menuRutes;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_route, container, false);

        menuRutes = view.findViewById(R.id.NavViewRutePlace); //the fragment
        menuRutes.setOnNavigationItemSelectedListener(this); //listener, when click an option, the listener is called

        return view;
        // return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment selectedFragment = null;
        switch (item.getItemId()) {
            case R.id.menu_rutas:
                selectedFragment = new RuteView();
                break;
            case R.id.menu_places:
                // @TODO: asignar a selectedFragment el Fragmen de opciones de lugares
                // selectedFragment = new MenuActivity();
                break;
            case R.id.menu_search:
                // @TODO: asignar a selectedFragment el Fragmen de opciones de busqueda

                break;
        }
        if (selectedFragment != null) {
            getFragmentManager().beginTransaction().replace(R.id.frame_rp_view, selectedFragment).commit();
        }
        return true;
    }

}
