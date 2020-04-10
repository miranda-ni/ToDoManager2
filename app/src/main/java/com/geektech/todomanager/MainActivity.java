package com.geektech.todomanager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements TaskClickListener {

    RecyclerView recyclerView;
    MainAdapter adapter;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_view);
        adapter = new MainAdapter();
        adapter.listener = this;
        recyclerView.setAdapter(adapter);
    }

    public void onCreateTask(View view) {
        Intent intent = new Intent(this, CreateTaskActivity.class);
        startActivity(intent);
    }


    @Override
    public void onTaskClick(int position) {
        this.position = position;
        Intent intent = new Intent(this, TaskDetailsActivity.class);
        intent.putExtra("task", position);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }
}
