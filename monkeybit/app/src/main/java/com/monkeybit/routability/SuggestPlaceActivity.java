package com.monkeybit.routability;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class SuggestPlaceActivity extends Fragment {

    private TextInputEditText newName;
    private TextInputEditText newDescription;
    private TextInputEditText newLocalization;
    private Button suggestButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_suggest_place, container, false);

        newName = (TextInputEditText) view.findViewById(R.id.new_name);
        newDescription = (TextInputEditText) view.findViewById(R.id.description);
        newLocalization = (TextInputEditText) view.findViewById(R.id.localization);
        suggestButton = view.findViewById(R.id.suggestButton);
        suggestButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                OnSuggest(v);
            }
        });

        return view;
    }

    protected void OnSuggest(android.view.View view) {
        String name = newName.getText().toString();
        String description = newDescription.getText().toString();
        String localization = newLocalization.getText().toString();
        if (!(name.isEmpty() || description.isEmpty() || localization.isEmpty())) {

            //Para probar a ver el nuevo lugar.
            String newPlace = name + "\n" + description + "\n" + localization + "\n";
            Toast.makeText(getActivity(), newPlace, Toast.LENGTH_SHORT).show();

            // @TODO: Acceder a BD para enviar los datos
            Toast.makeText(getActivity(), "Se enviarán los datos a la base de datos...", Toast.LENGTH_SHORT).show();
        } else {
            Toast toast = Toast.makeText(getActivity(), getString(R.string.empty_fields), Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
