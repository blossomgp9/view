package com.example.view;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private ImageView identifyingCode;
    private String realCode;
    String usercellphone;
    String useremail;
    String userpassword;
    EditText password;
    EditText account;
    EditText usercode;
    LinearLayout visible;
    LinearLayout code;
    String remaccount,rempassword;
    String acc=null;
    String pass=null;
    CheckBox checkbox;
    Boolean ischeck;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usercellphone=getSharedPreferences("string",MODE_PRIVATE).getString("usercellphone","");
        useremail=getSharedPreferences("string",MODE_PRIVATE).getString("useremail","");
        userpassword=getSharedPreferences("string",MODE_PRIVATE).getString("userpassword","");
        password=(EditText)findViewById(R.id.password);
        rempassword=getSharedPreferences("remember",MODE_PRIVATE).getString("password","");
        password.setText(rempassword);
        account=(EditText)findViewById(R.id.account);
        remaccount=getSharedPreferences("remember",MODE_PRIVATE).getString("account","");
        account.setText(remaccount);
        checkbox=findViewById(R.id.chk);
        ischeck=getSharedPreferences("remember",MODE_PRIVATE).getBoolean("ischeck",false);
        checkbox.setChecked(ischeck);
        visible=(LinearLayout)findViewById(R.id.visible);
        code=(LinearLayout)findViewById(R.id.code);
        identifyingCode=(ImageView)findViewById(R.id.identifyingcode_image);
        identifyingCode.setImageBitmap(IdentifyingCode.getInstance().createBitmap());
        identifyingCode.setOnClickListener(this);
        realCode=IdentifyingCode.getInstance().getCode().toLowerCase();
        usercode=(EditText)findViewById(R.id.usercode);
    }
    public void botton10 (View view) {

    }
    public void botton20 (View view) {
        acc=account.getText().toString();
        pass=password.getText().toString();
        if (password.getText().toString().equals(userpassword) &&
            (account.getText().toString().equals(usercellphone) ||
            account.getText().toString().equals(useremail))){
            if(code.getVisibility()==View.VISIBLE) {
                if (usercode.getText().toString().toLowerCase().equals(realCode)) {
                    if(checkbox.isChecked()){
                        SharedPreferences remember = getSharedPreferences("remember", MODE_PRIVATE);
                        remember.edit().putString("account", acc).putString("password", pass).putBoolean("ischeck",true).commit();
                        Bundle bundle = new Bundle();
                        bundle.putString("page", "1");
                        start(FragmentActivity.class, bundle, true);
                    }
                    else{
                        SharedPreferences remember=getSharedPreferences("remember",MODE_PRIVATE);
                        remember.edit().clear().commit();
                        Bundle bundle=new Bundle();
                        bundle.putString("page","1");
                        start(FragmentActivity.class,bundle,true);
                    }
                }
                else{
                    Toast.makeText(this,"驗證碼錯誤",Toast.LENGTH_SHORT).show();
                }
            }
            else{
                if (checkbox.isChecked()) {
                    SharedPreferences remember = getSharedPreferences("remember", MODE_PRIVATE);
                    remember.edit().putString("account", acc).putString("password", pass).putBoolean("ischeck",true).commit();
                    Bundle bundle = new Bundle();
                    bundle.putString("page", "1");
                    start(FragmentActivity.class, bundle, true);
                }
                else{
                    SharedPreferences remember = getSharedPreferences("remember", MODE_PRIVATE);
                    remember.edit().clear().commit();
                    Bundle bundle = new Bundle();
                    bundle.putString("page", "1");
                    start(FragmentActivity.class, bundle, true);
                }
            }
        }
        else{
            code.setVisibility(View.VISIBLE);
            visible.setVisibility(View.VISIBLE);
            Toast.makeText(this, "帳號密碼錯誤", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onClick(View view) {
        identifyingCode.setImageBitmap(IdentifyingCode.getInstance().createBitmap());
        realCode = IdentifyingCode.getInstance().getCode().toLowerCase();
    }
}