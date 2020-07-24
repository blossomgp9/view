package com.example.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecycleviewAdapter extends RecyclerView.Adapter<RecycleviewAdapter.ViewHolder> {

    private List<String> mData;
    private LayoutInflater mInflater;

    // 剛剛context跟list就是透過這個傳進來 然後我們設mData=data
    // 所以mData=Horse，Cow，Camel，Sheep，Goat
    // 這裡的inflater是要可以載入黃色那塊 就是recyclerview_row.xml
    RecycleviewAdapter(Context context, List<String> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    //創造viewHolder

    // 用inflater載入黃色這塊，並傳入viewHolder
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_row, parent, false);
        return new ViewHolder(view);
    }

    // 這裡是黃色那塊尋找textView Id的，不找就不知道在哪
    public class ViewHolder extends RecyclerView.ViewHolder  {
        TextView myTextView;
        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.recycletext);
        }


    }
    // 用viewHolder綁定資料
    //position是mData的第幾個 向是第零個是Horse 第二個是Camel
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String  textposition= mData.get(position);
        holder.myTextView.setText(textposition);
    }

    // 確定mData有幾個 ， 可以試試看return1的話就只會有一個資料出來喔
    @Override
    public int getItemCount() {
        return mData.size();
    }





}
