package com.example.contactsdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<String> contactsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.results_listView);

        Log.d("Adding", "DB Start");
        DatabaseHandler db = new DatabaseHandler(this);

        Log.d("Insert", "Inserting...");

        db.addContact(new Contact("Jan Kowalski", "0739555555"));
        db.addContact(new Contact("Johan Svensson", "0737222333"));
        db.addContact(new Contact("Anna Nowak", "0736050505"));
        db.addContact(new Contact("Pamela Andersson", "0739112233"));
        db.addContact(new Contact("William Shakespeare", "0738123456"));
        db.addContact(new Contact("James Bond", "0735666333"));
        db.addContact(new Contact("Julia Romeo", "0737737737"));

        contactsList = new ArrayList<>();

        Log.d("Reading", "Reading all contacts...");
        List<Contact> contacts = db.getAllContacts();

        for (Contact c :contacts) {
            String log = "Id: " + c.getId() + ", Name: " + c.getName() + ", Phone: " + c.getPhone_number() + "\n";
            Log.d("Names", log);

            contactsList.add(c.getName() + " (" + c.getPhone_number() + ")");
        }

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contactsList);
        listView.setAdapter(arrayAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String value = arrayAdapter.getItem(position);
                Toast.makeText(getApplicationContext(),value, Toast.LENGTH_SHORT).show();
            }
        });

    }
}
