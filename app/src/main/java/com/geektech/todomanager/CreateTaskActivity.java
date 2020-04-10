package com.geektech.todomanager;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CreateTaskActivity extends AppCompatActivity {

    EditText title, description;
    Button deadline, save, cancel;
    TextView deadlineText;

    Date taskDeadline;

    Calendar calendar = Calendar.getInstance();
    DatePickerDialog picker;
    Task task;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);

        title = findViewById(R.id.edit_task_title);
        description = findViewById(R.id.edit_task_description);
        deadline = findViewById(R.id.edit_task_deadline_button);
        save = findViewById(R.id.edit_task_save);
        cancel = findViewById(R.id.edit_task_cancel);
        deadlineText = findViewById(R.id.edit_task_deadline_text);


        Intent intent = getIntent();
        if (intent !=null){
            position = intent.getIntExtra("task",42);
            if (position != 42){
                task = TaskHolder.tasks.get(position);
                taskDeadline = task.deadline;
                title.setText(task.title);
                description.setText(task.description);
                SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
                deadlineText.setText(format.format(task.deadline));
            }else {
                createTask();
            }
        }else {
            createTask();
        }




    }
    void createTask(){
        task = new Task();
        task.startDate = new Date();
        task.isDone = false;
    }

    public void onChooseDate(View view) {
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        picker = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                deadlineText.setText(dayOfMonth + "." + (month + 1) + "." + year);
                taskDeadline = new GregorianCalendar(year, month, dayOfMonth).getTime();
            }
        }, year, month, day);
        picker.show();
    }

    public void onSave(View view) {

        String taskTitle = title.getText().toString();
        if (taskTitle.equals("")) {
            Toast.makeText(this, "Enter task title", Toast.LENGTH_SHORT).show();
            return;
        }

        String taskDescription = description.getText().toString();
        if (taskDescription.equals("")) {
            Toast.makeText(this, "Enter task description", Toast.LENGTH_SHORT).show();
            return;
        }

        task.deadline = taskDeadline;
        task.title = taskTitle;
        task.description = taskDescription;

        if (position == 42){
            TaskHolder.tasks.add(task);

        }else {
            TaskHolder.tasks.remove(position);
            TaskHolder.tasks.add(position,task);
        }finish();



    }

    public void onCancel(View view) {
        setResult(RESULT_CANCELED);
        finish();
    }
}
