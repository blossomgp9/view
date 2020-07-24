package com.example.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

public class BaseFragment extends Fragment {
    protected void start(Class<?> next, Bundle bundle, boolean finished){ // boolean: true/false
        Intent intent = new Intent();
        intent.setClass(getActivity(), next);
        if (bundle==null)intent.putExtras(new Bundle());
        else intent.putExtras(bundle);
        startActivity(intent);
        if (finished) getActivity().finish(); // destroy current activity
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
