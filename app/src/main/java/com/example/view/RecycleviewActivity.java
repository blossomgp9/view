package com.example.view;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecycleviewActivity extends AppCompatActivity  {
    RecycleviewAdapter adapter;
    RecyclerView recyclerView;
    Button newitem,ok,cancle;
    Dialog dialog;
    View viewdialog;
    EditText username,usertext;
    ArrayList<Post> mData = new ArrayList<>();
    @SuppressLint("WrongViewCast")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycleview);
        mData.add(new Post("賴己正1","剪頭髮1"));
        mData.add(new Post("賴己正2","剪頭髮2"));
        mData.add(new Post("賴己正3","剪頭髮3"));
        mData.add(new Post("賴己正4","剪頭髮4"));
        mData.add(new Post("賴己正5","剪頭髮5"));
        mData.add(new Post("賴己正6","剪頭髮6"));
        mData.add(new Post("賴己正7","剪頭髮7"));
        mData.add(new Post("賴己正8","剪頭髮8"));
        mData.add(new Post("賴己正9","剪頭髮9"));
        mData.add(new Post("賴己正10","剪頭髮10"));
        newitem=findViewById(R.id.newitem);
        newitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog=new Dialog(RecycleviewActivity.this,R.style.dialognsq);
                viewdialog=getLayoutInflater().inflate(R.layout.dialog_recyclerview,null);
                dialog.setContentView(viewdialog);
                dialog.setCancelable(false);
                username=viewdialog.findViewById(R.id.username);
                usertext=viewdialog.findViewById(R.id.usertext);
                dialog.show();
                ok=viewdialog.findViewById(R.id.ok);
                cancle=viewdialog.findViewById(R.id.cancle);
                cancle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        adapter.additem(new Post(username.getText().toString(),usertext.getText().toString()));
                        dialog.dismiss();
                    }
                });

            }
        });
        recyclerView=findViewById(R.id.recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter=new RecycleviewAdapter(this,mData);
        recyclerView.setAdapter(adapter);
    }
}
