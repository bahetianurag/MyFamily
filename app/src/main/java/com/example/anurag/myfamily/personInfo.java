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
import java.util.Map;

public class personInfo extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    DatabaseReference ind = myRef.child("individual");
    DatabaseReference info;

    private ArrayList<String> person = new ArrayList<>();
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_info);

        final String name = getIntent().getStringExtra("name");
        info = ind.child(name);

        listView = (ListView) findViewById(R.id.listview);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,person);
        listView.setAdapter(arrayAdapter);

        String x = info.child("dob").getKey().toString();
        String y = info.child("gender").getKey().toString();

        Toast.makeText(getApplicationContext(),"You selected : " + name,Toast.LENGTH_SHORT).show();
        Toast.makeText(getApplicationContext(),"You selected : " + x,Toast.LENGTH_SHORT).show();
        Toast.makeText(getApplicationContext(),"You selected : " + y,Toast.LENGTH_LONG).show();

        info.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot child : dataSnapshot.getChildren() ){

                    String name = dataSnapshot.getValue().toString();
                    person.add(name);
                    arrayAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

       /* listView = (ListView) findViewById(R.id.listview);

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,person);

        listView.setAdapter(arrayAdapter);

        info.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Map<String,String> map = dataSnapshot.getValue(Map.class);
                String address = map.get("address");
                String bg = map.get("blood_group");
                String dob = map.get("dob");
                String education = map.get("education");
                String email = map.get("email");
                String gender = map.get("gender");
                String head = map.get("head");
                String hobbies = map.get("hobbies");
                String landline = map.get("landline");
                String anni = map.get("marriage_anni");
                String marriedto = map.get("married_to");
                String mobile = map.get("mobile");
                String occupation = map.get("occupation");
                String writeup = map.get("writeup");


                person.add("Name: "+name);
                person.add("Gender: "+gender);
                person.add("Date of birth: "+dob);
                person.add("Education: "+education);
                person.add("Marriage Anniversary: "+anni);
                person.add("Married to: "+marriedto);
                person.add("Address: "+address);
                person.add("Family Head: "+head);
                person.add("Landline: "+landline);
                person.add("Mobile: "+mobile);
                person.add("Email: "+email);
                person.add("Blood Group: "+bg);
                person.add("Occupation: "+occupation);
                person.add("Description: "+writeup);
                person.add("Hobbies: "+hobbies);
                arrayAdapter.notifyDataSetChanged();

                Toast.makeText(getApplicationContext(),"You selected : " + name,Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(),"You selected : " + address,Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(),"You selected : " + head,Toast.LENGTH_LONG).show();



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/

        Toast.makeText(getApplicationContext(),"You selected : " + name,Toast.LENGTH_SHORT).show();



    }
}
