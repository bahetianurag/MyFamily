package com.example.anurag.myfamily;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class test1 extends AppCompatActivity {

    EditText firstname;
    EditText middlename;
    EditText lastname;
    public SharedPreferences pref=null;
    public SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test1);
        pref=getApplicationContext().getSharedPreferences("MyPref",MODE_PRIVATE);
        editor=pref.edit();

        String fn = pref.getString("First Name","");
        String mn = pref.getString("Middle Name","");
        String ln = pref.getString("Last Name","");

        if(!(fn.equals("") && mn.equals("") && ln.equals("")))
        {
            Intent submitIntent = new Intent(this, HomePage.class);
            startActivity(submitIntent);
        }
        firstname = (EditText) findViewById(R.id.fn);
        middlename = (EditText) findViewById(R.id.mn);
        lastname = (EditText) findViewById(R.id.ln);
    }
    public void onClick(View view){
        switch (view.getId()){
            case R.id.submitButton:
            {
                String fn= firstname.getText().toString();
                String mn= middlename.getText().toString();
                String ln= lastname.getText().toString();
                editor.putString("First Name", fn);
                editor.putString("Middle Name", mn);
                editor.putString("Last Name", ln);
                editor.commit();
                Intent submitIntent = new Intent(this, HomePage.class);
                startActivity(submitIntent);
                break;
            }
        }

    }
}
