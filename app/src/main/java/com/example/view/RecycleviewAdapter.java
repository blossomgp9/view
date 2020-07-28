package com.example.view;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.chauthai.swipereveallayout.ViewBinderHelper;
import java.util.ArrayList;

public class RecycleviewAdapter extends RecyclerView.Adapter<RecycleviewAdapter.ViewHolder> {
    private final ViewBinderHelper viewBinderHelper = new ViewBinderHelper();
    private ArrayList<Post> mData;
    private LayoutInflater mInflater;


    // 剛剛context跟list就是透過這個傳進來 然後我們設mData=data
    // 所以mData=Horse，Cow，Camel，Sheep，Goat
    // 這裡的inflater是要可以載入黃色那塊 就是recyclerview_row.xml
    RecycleviewAdapter(Context context, ArrayList<Post> data) {
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
        SwipeRevealLayout swipeRevealLayout;
        TextView username;
        TextView usertext;
        Button delete,info;
        Context context;
        ViewHolder(View itemView) {
            super(itemView);
            context=itemView.getContext();
            delete=itemView.findViewById(R.id.button_Delete);
            info=itemView.findViewById(R.id.button_Show);
            username = itemView.findViewById(R.id.recycletext);
            usertext = itemView.findViewById(R.id.recycletext1);
            swipeRevealLayout = itemView.findViewById(R.id.swipeLayout);
        }
    }
    // 用viewHolder綁定資料
    //position是mData的第幾個 向是第零個是Horse 第二個是Camel
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        viewBinderHelper.setOpenOnlyOne(true);//設置swipe只能有一個item被拉出
        viewBinderHelper.bind(holder.swipeRevealLayout, String.valueOf(position));//綁定Layout (第三步)
        final Post post= mData.get(position);
        holder.username.setText(post.username);
        holder.usertext.setText(post.usertext);
        holder.delete.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.swipeRevealLayout.close(true);
                mData.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, mData.size());
            }
        }));//holder.btDelete
    }

    public void additem(Post post){
        mData.add(0,post);
        notifyItemInserted(0);
        notifyDataSetChanged();
    }
    // 確定mData有幾個 ， 可以試試看return1的話就只會有一個資料出來喔
    @Override
    public int getItemCount() {
        return mData.size();
    }
}
