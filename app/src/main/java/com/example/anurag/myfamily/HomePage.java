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
    //Button b1;
    int backButtonCount=0;
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
        //b1 = (Button) findViewById(R.id.button2);

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

    public void callDir(View view) {
        // Do something in response to button
        Toast.makeText(getApplicationContext(),"Button pressed",Toast.LENGTH_SHORT).show();
        Intent in = new Intent(getApplicationContext(), directory.class);
        startActivity(in);
    }

    public void callProfile(View view) {
        // Do something in response to button
        Toast.makeText(getApplicationContext(),"Button pressed",Toast.LENGTH_SHORT).show();
        Intent in = new Intent(getApplicationContext(), profile.class);
        startActivity(in);
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


    @Override
    public void onBackPressed() {
        //super.onBackPressed();

        if(backButtonCount >= 1)
        {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(this, "Press the back button once again to close the application.", Toast.LENGTH_SHORT).show();
            backButtonCount++;
        }
    }
}
