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

    int x=0;String a[]={"Wife","Son","Daughter in law","Daughter","Grandson","Granddaughter"};

    String a1[][]=new String[15][2];
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family_info);

        name = getIntent().getStringExtra("name");

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
                    a1[x][0]=child.getValue().toString();
                    a1[x++][1]=child.getKey();
                    //Toast.makeText(getApplicationContext(),x,Toast.LENGTH_SHORT).show();
                    //person.add(x);
                    //arrayAdapter.notifyDataSetChanged();
                }


                person.add("Self: "+name);arrayAdapter.notifyDataSetChanged();
                for(int i=0;i<a.length;i++){
                    for(int j=0;j<x;j++){
                        //if(j==0)
                        //{person.add("Self: "+name);arrayAdapter.notifyDataSetChanged();}
                        if(a[i].equals(a1[j][0]))
                        {
                            person.add(a1[j][0]+": "+a1[j][1]);
                            arrayAdapter.notifyDataSetChanged();
                        }
                    }


                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}