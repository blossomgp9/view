package com.example.view;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class RecycleviewActivity extends AppCompatActivity {
    RecycleviewAdapter adapter;
    RecyclerView recyclerView;
    @SuppressLint("WrongViewCast")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycleview);

        ArrayList<String> recycleviewtexts = new ArrayList<>();
        recycleviewtexts.add("賴");
        recycleviewtexts.add("己");
        recycleviewtexts.add("正");
        recycleviewtexts.add("小");
        recycleviewtexts.add("雞");
        recycleviewtexts.add("雞");
        recycleviewtexts.add("雞");
        recycleviewtexts.add("雞");
        recycleviewtexts.add("雞");
        recycleviewtexts.add("雞");
        recycleviewtexts.add("雞");


       recyclerView=findViewById(R.id.recycle);
       recyclerView.setLayoutManager(new LinearLayoutManager(this));
       adapter=new RecycleviewAdapter(this,recycleviewtexts);
       recyclerView.setAdapter(adapter);
    }
}
