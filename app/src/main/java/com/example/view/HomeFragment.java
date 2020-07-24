package com.example.view;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import static android.content.Context.MODE_PRIVATE;

public class HomeFragment extends BaseFragment implements View.OnClickListener {
    ImageView scan,point,setting,recycle;
    View viewdialog,viewdialog2;
    EditText account,password;
    TextView remain,remainpoint;
    Button ok,ok2,cancle,cancle2;
    Dialog dialog,dialog2;
    String usercellphone,useremail,userpassword;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_home,container,false);
        usercellphone=this.getActivity().getSharedPreferences("string", MODE_PRIVATE).getString("usercellphone","");
        userpassword=this.getActivity().getSharedPreferences("string",MODE_PRIVATE).getString("userpassword","");
        useremail=this.getActivity().getSharedPreferences("string",MODE_PRIVATE).getString("useremail","");
        account=view.findViewById(R.id.account);
        password=view.findViewById(R.id.password);
        scan =view.findViewById(R.id.imageView3);
        scan.setOnClickListener(this);
        point=view.findViewById(R.id.imageView5);
        point.setOnClickListener(this);
        setting=view.findViewById(R.id.imageView7);
        setting.setOnClickListener(this);
        recycle=view.findViewById(R.id.imageView9);
        recycle.setOnClickListener(this);

        return view;
    }
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageView3:
                start(ScanActivity.class);
                Log.v("aaaaa","1");
                break;
            case R.id.imageView9:
                Log.v("aaaaa","2");
                start(RecycleviewActivity.class);
                break;
            case R.id.imageView5:
                Log.v("aaaaa","4");
                calldialog();
                break;
            case R.id.imageView7:
                Log.v("aaaaa","3");
                start(SettingActivity.class);
                break;
        }
    }
    public void calldialog(){
        dialog= new Dialog(getActivity(),R.style.dialognsq);
        viewdialog= getLayoutInflater().inflate(R.layout.dialog_layout, null);
        dialog.setContentView(viewdialog);
        dialog.setCancelable(false);
        account =viewdialog.findViewById(R.id.account);
        password =viewdialog.findViewById(R.id.password);
        ok=viewdialog.findViewById(R.id.ok);
        cancle=viewdialog.findViewById(R.id.cancle);
        dialog.show();
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (password.getText().toString().equals(userpassword) &&
                    (account.getText().toString().equals(usercellphone) ||
                    account.getText().toString().equals(useremail))){
                    calldialog2();
                }
                else {
                    Toast.makeText(getActivity(), "帳號密碼錯誤", Toast.LENGTH_SHORT).show();
                }
            }

        });

    }
    public void calldialog2() {
        dialog2=new Dialog(getActivity(),R.style.dialognsq);
        dialog2.setCancelable(false);
        viewdialog2=getLayoutInflater().inflate(R.layout.dialog2_layout,null);
        dialog2.setContentView(viewdialog2);
        remain=dialog2.findViewById(R.id.remain);
        remainpoint=dialog2.findViewById(R.id.remainpoint);
        ok2=dialog2.findViewById(R.id.ok2);
        cancle2=dialog2.findViewById(R.id.cancle2);
        dialog2.show();
        ok2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                dialog2.dismiss();
            }
        });
        cancle2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog2.dismiss();
            }
        });

    }
}
