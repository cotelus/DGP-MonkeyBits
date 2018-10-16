package com.monkeybit.routability;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class RegisterAccountActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_account);

        mAuth = FirebaseAuth.getInstance();
    }

    public void OnCreateAccount (android.view.View view) {
        String email = ((EditText) findViewById(R.id.eMailAut)).getText().toString();
        String password = ((EditText) findViewById(R.id.passAut)).getText().toString();
        String passwordRepeat = ((EditText) findViewById(R.id.passAutRepeat)).getText().toString();
        final String user_name = ((EditText) findViewById(R.id.UserName)).getText().toString();

        if (email.isEmpty() || password.isEmpty() || passwordRepeat.isEmpty() || user_name.isEmpty()) {
            Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.empty_fields), Toast.LENGTH_SHORT);
            toast.show();
            return;
        }

        if (password.equals(passwordRepeat)) {
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                FirebaseUser user = mAuth.getCurrentUser();
                                Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.create_account_succesfully), Toast.LENGTH_SHORT);
                                toast.show();
                                UpdateUserProfile(user_name);
                                LoadActivityWithoutArguments(MainActivity.class);
                                //updateUI(user);
                            } else {
                                // If sign in fails, display a message to the user.
                                Toast toast = Toast.makeText(getApplicationContext(), "No se han podido recuperar los datos del usuario.", Toast.LENGTH_SHORT);
                                toast.show();
                                //updateUI(null);
                            }

                            // ...
                        }
                    });
        } else {
            //Show error on screen
            Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.passwords_not_equal), Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
    }

    private void UpdateUserProfile(String userName) {
        FirebaseUser user = mAuth.getCurrentUser();

        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(userName)
                .build();

        user.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    private static final String TAG = "Debug:";

                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "User profile updated.");
                            Toast toast = Toast.makeText(getApplicationContext(), "User profile updated.", Toast.LENGTH_SHORT);
                            toast.show();
                        } else {
                            Log.d(TAG, "Something fail when updating user profile.");
                        }
                    }
                });
    }

    private void LoadActivityWithoutArguments(Class<?> newActivityName) {
        Intent intent = new Intent(this, newActivityName);
        startActivity(intent);
    }
}
