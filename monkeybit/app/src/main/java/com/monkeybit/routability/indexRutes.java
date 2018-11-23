package com.monkeybit.routability;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

public class indexRutes extends Fragment {
    ListView list;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_rute, container, false);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            Fragment selectedFragment = null;
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               // ItemClicked item = parent.getItemAtPosition(position);
                selectedFragment = new RuteView();
            }

        });


        return view;
        // return super.onCreateView(inflater, container, savedInstanceState);
    }

}
