package com.monkeybit.routability;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class RouteActivity extends Fragment implements BottomNavigationView.OnNavigationItemSelectedListener {
    public BottomNavigationView menuRutes;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_route, container, false);
        getFragmentManager().beginTransaction().replace(R.id.frame_rp_view, new ListRouteActivity()).commit(); //by default
        menuRutes = view.findViewById(R.id.NavViewRutePlace); //the fragment
        menuRutes.setOnNavigationItemSelectedListener(this); //listener, when click an option, the listener is called

        return view;
        // return super.onCreateView(inflater, container, savedInstanceState);
    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment selectedFragment = new RuteView();
        switch (item.getItemId()) {
            case R.id.menu_rutas:
                //@TODO selectedFragment = new ListRouteActivity();
                selectedFragment = new RuteView();
                //to send the id
                Bundle bundleRoute = new Bundle();
                bundleRoute.putString("routeId", "8");
                selectedFragment.setArguments(bundleRoute);

                break;
            case R.id.menu_places:
                // @TODO: asignar a selectedFragment el Fragmen de opciones de lugares
                 selectedFragment = new PlaceView();
                 Bundle bundle = new Bundle();
                 bundle.putString("placeId", "1");
                 selectedFragment.setArguments(bundle);
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
