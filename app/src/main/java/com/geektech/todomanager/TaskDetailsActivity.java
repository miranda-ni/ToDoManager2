package com.geektech.todomanager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_details);
        Intent intent = getIntent();
        if (intent == null) {
            Toast.makeText(this, "Task not found", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            title = findViewById(R.id.details_title);
            description = findViewById(R.id.details_description);
            startDate = findViewById(R.id.details_start_date);
            deadline = findViewById(R.id.details_deadline);
            checkBox = findViewById(R.id.details_checkbox);

            task = (Task) intent.getSerializableExtra("task");
            setViewsContent(task);

        }
    }
    private void setViewsContent(Task task){
        title.setText(task.title);
        description.setText(task.description);
        checkBox.setChecked(task.isDone);

        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        startDate.setText(format.format(task.startDate));
        deadline.setText(format.format(task.deadline));
    }
    public void onEditClick(View view) {
        Intent intent = new Intent(this, EditTaskActivity.class);
        intent.putExtra("task", task);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 1 && data != null){
            task = (Task) data.getSerializableExtra("editTask");
            setViewsContent(task);
        }
    }

    public void onEditSave(View view) {
        Intent intent = new Intent();
        intent.putExtra("editedTask", task);
        setResult(RESULT_OK, intent);
        finish();
    }
}
