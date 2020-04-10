package com.geektech.todomanager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TaskDetailsActivity extends AppCompatActivity {

    TextView title, description, startDate, deadline;
    CheckBox checkBox;
    Task task;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_details);
        final Intent intent = getIntent();
        if (intent == null) {
            Toast.makeText(this, "Task not found", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            title = findViewById(R.id.details_title);
            description = findViewById(R.id.details_description);
            startDate = findViewById(R.id.details_start_date);
            deadline = findViewById(R.id.details_deadline);
            checkBox = findViewById(R.id.details_checkbox);

            position = intent.getIntExtra("task", 42);


            Button edit = findViewById(R.id.details_edit_button);
            Button save = findViewById(R.id.details_save_button);
            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent1 = new Intent(TaskDetailsActivity.this, CreateTaskActivity.class);
                    intent1.putExtra("task", position);
                    startActivity(intent1);
                }
            });
            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        task = TaskHolder.tasks.get(position);
        title.setText(task.title);
        description.setText(task.description);
        checkBox.setChecked(task.isDone);
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        startDate.setText(format.format(task.startDate));
        deadline.setText(format.format(task.deadline));
    }
}