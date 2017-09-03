package com.example.anurag.myfamily;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class personInfo extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    DatabaseReference ind = myRef.child("individual");
    DatabaseReference info;

    private ArrayList<String> person = new ArrayList<>();
    private ListView listView;

    int x=0;String a[]=new String[15];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_info);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        final String name = getIntent().getStringExtra("name");
        info = ind.child(name);

        listView = (ListView) findViewById(R.id.listview);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,person);
        listView.setAdapter(arrayAdapter);

        info.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot child : dataSnapshot.getChildren() ){
                    a[x++]=child.getValue().toString();
                }


                person.add("Name: "+name);arrayAdapter.notifyDataSetChanged();
                person.add("Gender: "+a[5]);arrayAdapter.notifyDataSetChanged();
                person.add("Date of birth: "+a[2]);arrayAdapter.notifyDataSetChanged();
                person.add("Education: "+a[3]);arrayAdapter.notifyDataSetChanged();
                person.add("Married to: "+a[10]);arrayAdapter.notifyDataSetChanged();
                person.add("Marriage Anniversary: "+a[9]);arrayAdapter.notifyDataSetChanged();
                person.add("Address: "+a[0]);arrayAdapter.notifyDataSetChanged();
                person.add("Landline: "+a[8]);arrayAdapter.notifyDataSetChanged();
                person.add("Mobile: "+a[11]);arrayAdapter.notifyDataSetChanged();
                person.add("Email: "+a[4]);arrayAdapter.notifyDataSetChanged();
                person.add("Blood Group: "+a[1]);arrayAdapter.notifyDataSetChanged();
                person.add("Occupation: "+a[12]);arrayAdapter.notifyDataSetChanged();
                person.add("About Occupation: "+a[13]);arrayAdapter.notifyDataSetChanged();
                person.add("Hobbies: "+a[7]);arrayAdapter.notifyDataSetChanged();
                person.add("Family Name: "+a[6]+" and Family");arrayAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (position == 4 || position == 14) {

                    String item = (String) listView.getItemAtPosition(position);
                    if (position == 4 && !(item.equals("Married to: NA"))) {
                        Intent i = new Intent(getApplication(), personInfo.class);
                        item = item.substring(item.indexOf(":") + 2);
                        //Toast.makeText(getApplicationContext(), item, Toast.LENGTH_SHORT).show();
                        i.putExtra("name", item);
                        startActivity(i);
                    } else if (position == 14) {
                        Intent i = new Intent(getApplication(), FamilyInfo  .class);
                        item = a[6];
                        //Toast.makeText(getApplicationContext(), item, Toast.LENGTH_SHORT).show();
                        i.putExtra("name", item);
                        i.putExtra("address",a[0]);
                        i.putExtra("landline",a[8]);
                        startActivity(i);
                    }
                }
            }
        });
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}