package com.example.groupevent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    public EditText eventEditTask;
    public EditText eventDesc;
    public Button addButton;
    public ListView eventListView;

    public ArrayList<String> events;
    public ArrayList<String> desc;
    public ArrayAdapter<String> adapter;
    public ArrayAdapter<String> adapterDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eventEditTask = findViewById(R.id.enterTask);
        eventDesc = findViewById(R.id.enterDesc);
        addButton = findViewById(R.id.addButton);
        eventListView = findViewById(R.id.myListView);

        events = FileHelper.readData(this);
        desc = FileHelper.readData(this);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, events);
        adapterDesc = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, desc);
        eventListView.setAdapter(adapter);

        addButton.setOnClickListener(this);
        eventListView.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.addButton:
                String eventEntered = eventEditTask.getText().toString();
                String descEntered = eventDesc.getText().toString();
                adapter.add(eventEntered);
                adapterDesc.add(descEntered);
                eventEditTask.setText("");
                eventDesc.setText("");

                FileHelper.writeData(events, this);

                Toast.makeText(this, "Event Added!", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String eventEntered = adapter.getItem(position);
        String descEntered = adapterDesc.getItem(position);
        Intent showTaskActivity = new Intent(this, TaskActivity.class);
        showTaskActivity.putExtra("event-entered", eventEntered);
        showTaskActivity.putExtra("desc-entered", descEntered);
        startActivity(showTaskActivity);

        //events.remove(position);
        //adapter.notifyDataSetChanged();
        //Toast.makeText(this, "Event Deleted!", Toast.LENGTH_SHORT).show();
    }
}

