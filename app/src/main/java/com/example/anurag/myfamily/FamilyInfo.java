package com.example.anurag.myfamily;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

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

    int x=0;String a[]=new String[15];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family_info);

        final String name = getIntent().getStringExtra("name");
        info = ind.child("Amarchand Baheti");


        Toast.makeText(getApplicationContext(),info.getKey(),Toast.LENGTH_SHORT).show();
        listView = (ListView) findViewById(R.id.listview);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,person);
        listView.setAdapter(arrayAdapter);

        info.addValueEventListener(new ValueEventListener() {        // NOT GOING INSIDE THIS THING
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot child : dataSnapshot.getChildren() ){
                    Toast.makeText(getApplicationContext(),x,Toast.LENGTH_SHORT).show();
                    //a[x]= child.getKey()+": "+child.getValue().toString();
                    //person.add(a[x++]);
                    //arrayAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

    }
}
