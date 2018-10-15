package com.monkeybit.routability;

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

public class AccountActivity extends AppCompatActivity {

    private static final Object TAG = "Debug";
    private FirebaseAuth mAuth;
    public String email;
    public String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        mAuth = FirebaseAuth.getInstance();
        email = null;
        password = null;
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
        if (currentUser != null) {
            Toast toast = Toast.makeText(getApplicationContext(), "Currently signed in!", Toast.LENGTH_SHORT);
            toast.show();
        } else {
            Toast toast = Toast.makeText(getApplicationContext(),"You should sign in", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void onSignIn(android.view.View view) {

        //@TODO: Si no tiene los valores válidos, la aplicación crashea...
        email = ((EditText) findViewById(R.id.eMailAut)).getText().toString();
        password = ((EditText) findViewById(R.id.passAut)).getText().toString();

        if (email == null || password == null) {
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast toast = Toast.makeText(getApplicationContext(), "SignIn succesfully!", Toast.LENGTH_SHORT);
                            toast.show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast toast = Toast.makeText(getApplicationContext(), "Authentication fail!", Toast.LENGTH_SHORT);
                            toast.show();
                            //updateUI(null);
                        }
                    }
                });
    }

    public void onLogOut(android.view.View view) {
        if (mAuth.getCurrentUser() != null) {
            mAuth.signOut();
            Toast toast = Toast.makeText(getApplicationContext(), "LogOut!", Toast.LENGTH_SHORT);
            toast.show();
        }
        Toast toast = Toast.makeText(getApplicationContext(), "You are ready LogOut!", Toast.LENGTH_SHORT);
        toast.show();
    }

}
