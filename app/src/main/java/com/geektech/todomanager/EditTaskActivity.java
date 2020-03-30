package com.geektech.todomanager;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class EditTaskActivity extends AppCompatActivity {
    Task task;
    EditText title,description;
    TextView startDate,deadlineDate;
    CheckBox checkBox;
    Calendar calendar = Calendar.getInstance();
    DatePickerDialog picker;
    SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
    Date date,editDeadline,editStartDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);
        Intent intent = getIntent();
        task = (Task) intent.getSerializableExtra("task");
        initViews();
        setViewsText();
    }

    private void initViews(){
        title = findViewById(R.id.edit_task_title);
        description = findViewById(R.id.edit_task_description);
        startDate = findViewById(R.id.edit_task_start_date_text);
        deadlineDate = findViewById(R.id.edit_task_deadline_text);
        checkBox = findViewById(R.id.edit_checkBox);
    }

    private void setViewsText(){
        title.setText(task.title);
        description.setText(task.description);
        startDate.setText(format.format(task.startDate));
        deadlineDate.setText(format.format(task.deadline));
        checkBox.setChecked(task.isDone);
    }

    public void onChangeDeadlineDate(View view) {
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        picker = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                deadlineDate.setText(dayOfMonth + "." + (month + 1) + "." + year);
                editDeadline = new GregorianCalendar(year, month, dayOfMonth).getTime();
            }
        }, year, month, day);
        picker.show();
    }

    public void onChangeStartDate(View view) {
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        picker = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                startDate.setText(dayOfMonth + "." + (month + 1) + "." + year);
                editStartDate = new GregorianCalendar(year, month, dayOfMonth).getTime();
            }
        }, year, month, day);
        picker.show();
    }

    public void onSaveEditTask(View view) {
        Task task = new Task();
        task.deadline = editDeadline;
        task.title = title.getText().toString();
        task.description = description.getText().toString();
        task.startDate = editStartDate;
        task.isDone = checkBox.isChecked();

        Intent intent = new Intent();
        intent.putExtra("editTask",task);
        setResult(RESULT_OK,intent);
        finish();
    }

    public void onCancelEditActivity(View view) {
    }
}
