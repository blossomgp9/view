package com.example.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {
    protected void start(Class<?> next,Bundle bundle,boolean finished){ // boolean: true/false
        Intent intent = new Intent();
        intent.setClass(this, next);
        if (bundle==null)intent.putExtras(new Bundle());
        else intent.putExtras(bundle);
        startActivity(intent);
        if (finished) this.finish(); // destroy current activity
    }

    protected void start(Class<?> next){
        this.start(next,null,false);
    }

    protected void start(Class<?> next,Bundle bundle){
        this.start(next,bundle,false);
    }

    protected void start(Class<?> next,boolean finished){
        this.start(next,null,finished);
    }
}
