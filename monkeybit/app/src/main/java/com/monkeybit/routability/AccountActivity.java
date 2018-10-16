package com.monkeybit.routability;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AccountActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
        if (currentUser != null) {
            LoadActivityWithoutArguments(MainActivity.class);
        }
    }

    public void OnSignIn(android.view.View view) {
        String email = ((EditText) findViewById(R.id.eMailAut)).getText().toString();
        String password = ((EditText) findViewById(R.id.passAut)).getText().toString();

        if (email.isEmpty() || password.isEmpty()) {
            //Show error on screen
            Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.empty_fields), Toast.LENGTH_SHORT);
            toast.show();
        }

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.signed_in_succesfully), Toast.LENGTH_SHORT);
                                toast.show();
                                FirebaseUser user = mAuth.getCurrentUser();
                                //updateUI(user);
                                LoadActivityWithoutArguments(MainActivity.class);
                            } else {
                                // If sign in fails, display a message to the user.
                                Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.signed_in_fail), Toast.LENGTH_SHORT);
                                toast.show();
                                //updateUI(null);
                            }
                        }
                    });
    }

    public void OnNewUser(android.view.View view) {
        LoadActivityWithoutArguments(RegisterAccountActivity.class);
    }

    private void LoadActivityWithoutArguments(Class<?> newActivityName) {
        Intent intent = new Intent(this, newActivityName);
        startActivity(intent);
    }

}
