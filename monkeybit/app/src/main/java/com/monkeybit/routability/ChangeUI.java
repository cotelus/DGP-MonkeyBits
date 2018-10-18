package com.monkeybit.routability;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.UserProfileChangeRequest;

class ChangeUI extends UserProfileActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        OnChangeUI();
    }

    @Override
    protected void OnChangeUI() {
        //to edit the name
        final EditText userNameTextView = findViewById(R.id.newName);

        final Button butUpdate = findViewById(R.id.Update);
        if (userNameTextView != null){
            butUpdate.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    UserProfileChangeRequest.Builder builder = new UserProfileChangeRequest.Builder();
                    builder.setDisplayName(String.valueOf(userNameTextView));
                    UserProfileChangeRequest request = builder.build();
                    currentUser.updateProfile(request)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(getApplicationContext(), "profile update success = " + task.isSuccessful(),
                                            Toast.LENGTH_SHORT)
                                            .show();
                                    Intent intent = new Intent(getApplicationContext(),UserProfileActivity.class);
                                    startActivity(intent);
                                }
                            });
                }


            });
        }
        //go to the profile


    }


}