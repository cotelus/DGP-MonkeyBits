package com.monkeybit.routability;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class ChangeUI extends Fragment {
    private FirebaseAuth mAuth;
    FirebaseUser currentUser;

    EditText userNameTextView;
    Button butUpdate;


   /* @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        OnChangeUI();

    }*/


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.change_ui, container, false);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        if (currentUser == null) {
            Log.e("Debug:", "Error, no debería haber aparecido esta pantalla porque el usuario no ha iniciado sesión");
            ((MainActivity)getActivity()).LoadNewFragment(new MenuActivity());
        }

        userNameTextView = view.findViewById(R.id.newName);
        butUpdate = view.findViewById(R.id.Update);

        butUpdate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                OnChangeUI(v);
            }
        });

        return view;
        // return super.onCreateView(inflater, container, savedInstanceState);
    }


    protected void OnChangeUI(View v) {
        //to edit the name
            String aux = userNameTextView.getText().toString();
            if (!aux.isEmpty()){
                    UserProfileChangeRequest.Builder builder = new UserProfileChangeRequest.Builder();
                    builder.setDisplayName(String.valueOf(aux));
                    UserProfileChangeRequest request = builder.build();
                    currentUser.updateProfile(request)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(getActivity(), "profile update success = " + task.isSuccessful(),
                                            Toast.LENGTH_SHORT)
                                            .show();
                                    ((MainActivity)getActivity()).LoadNewFragment(new UserProfileActivity());
                                }
                            });
            }
            else
            ((MainActivity)getActivity()).LoadNewFragment(new UserProfileActivity());
    }





}