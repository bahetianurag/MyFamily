package com.example.anurag.myfamily;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FamilyInfo extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    DatabaseReference ind = myRef.child("family");
    DatabaseReference info;

    private ArrayList<String> person = new ArrayList<>();
    private ListView listView;

    int x=0;//String a[]=new String[15];

    String a1[][]=new String[15][2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family_info);

        final String name = getIntent().getStringExtra("name");

        //Toast.makeText(getApplicationContext(),name,Toast.LENGTH_SHORT).show();

        info = ind.child(name);

        Toast.makeText(getApplicationContext(),info.getKey(),Toast.LENGTH_SHORT).show();

        listView = (ListView) findViewById(R.id.listview);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,person);
        listView.setAdapter(arrayAdapter);

        //Toast.makeText(getApplicationContext(),info.toString(),Toast.LENGTH_SHORT).show();

        info.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot child : dataSnapshot.getChildren()){
                    //a[x]=child.getValue().toString()+": "+child.getKey();
                    a1[x][0]=child.getValue().toString();
                    a1[x++][1]=child.getKey();
                    Toast.makeText(getApplicationContext(),a1[x-1][0]+a1[x-1][1],Toast.LENGTH_SHORT).show();
                    //person.add(a[x++]);
                    //arrayAdapter.notifyDataSetChanged();

                }

                for(int i=0;i<x;i++){

                }




            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
