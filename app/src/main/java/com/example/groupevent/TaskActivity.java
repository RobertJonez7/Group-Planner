package com.example.groupevent;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TaskActivity extends AppCompatActivity {
    public TextView TaskTitle;
    public TextView DescTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        TaskTitle = findViewById(R.id.HeaderText);
        DescTitle = findViewById(R.id.descTextView);
        Bundle extras = getIntent().getExtras();
        String title = extras.getString("event-entered");
        String desc = extras.getString("desc-entered");
        TaskTitle.setText(title);
        DescTitle.setText(desc);
    }
}
