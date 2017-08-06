package com.example.anurag.myfamily;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;

public class HomePage extends AppCompatActivity {

    // ANURAG EDITED
    Button b;

    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    public static final int RC_SIGN_IN = 1;
    private String mUsername;
    private String phonenumber;
    Toast Welcome;
    public SharedPreferences pref=null;
    public SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        pref=getApplicationContext().getSharedPreferences("MyPref",MODE_PRIVATE);
        editor=pref.edit();
        b = (Button) findViewById(R.id.button2);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(), directory.class);
                startActivity(in);
            }
        });
        mFirebaseAuth= FirebaseAuth.getInstance();
        FirebaseUser user = mFirebaseAuth.getCurrentUser();
        if (user != null) {

            onSignedInInitialize(user.getPhoneNumber());
        } else {
            onSignedOutCleanup();
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setIsSmartLockEnabled(false)
                            .setAvailableProviders(
                                    Arrays.asList(new AuthUI.IdpConfig.Builder(AuthUI.PHONE_VERIFICATION_PROVIDER).build()))
                            .build(),
                    RC_SIGN_IN);


        }


    }
    private void onSignedInInitialize(String phonenum) {
        phonenumber = phonenum;
        editor.putString("Phone Number",phonenumber);
        editor.commit();
    }
    private void onSignedOutCleanup(){

        mUsername = null;
        phonenumber=null;

    }
}
