package com.example.view;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.prefs.Preferences;

public class RegisterActivity extends BaseActivity {

    private ImageView identifyingCode;
    private String realCode;
    EditText    usercell;
    EditText    userpass;
    EditText    userpassword;
    EditText    userbirthday;
    EditText    usercode;
    EditText    useremail;
    String      password,email,cellphone;
    String      check=null;
    // Java 日歷類別
    final Calendar myCalendar = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener DataSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        usercell=(EditText)findViewById(R.id.usercell);
        useremail=(EditText)findViewById(R.id.useremail);
        userpass=(EditText)findViewById(R.id.userpass);
        userpassword=(EditText)findViewById(R.id.userpassword);
        userbirthday=(EditText)findViewById(R.id.userbirthday);
        usercode=(EditText)findViewById(R.id.usercode);
        identifyingCode=(ImageView)findViewById(R.id.identifyingcode_image);
        identifyingCode.setImageBitmap(IdentifyingCode.getInstance().createBitmap());
        realCode=IdentifyingCode.getInstance().getCode().toLowerCase(); // 轉小寫 大寫: toUpperCase()
        getPermissionsCamera();
        check=getSharedPreferences("string",MODE_PRIVATE).getString("check","");
        if (check.equals("1")) {
            start(LoginActivity.class,true);
        }
            Spinner spinner = (Spinner) findViewById(R.id.spinner);
            final String[] country = {"台灣", "中國", "美國", "日本", "韓國", "加拿大", "法國", "英國", "泰國", "印度", "巴西", "巴拉圭", "馬來西亞", "澳洲", "德國"};
            //final String[] country = getResources().getStringArray(R.array.aray);
            ArrayAdapter<String> lunchList = new ArrayAdapter<>(RegisterActivity.this, android.R.layout.simple_spinner_dropdown_item, country);
            spinner.setAdapter(lunchList);

    }

    public void botton1(View view) {
        identifyingCode.setImageBitmap(IdentifyingCode.getInstance().createBitmap());
        realCode = IdentifyingCode.getInstance().getCode().toLowerCase();
    }


    public void bottom2(View view) {
        List<EditText> e = checkIfEmptyOrNull(usercell, useremail, userpass, userpassword, userbirthday, usercode);
        if (e.size() == 0){
            // 物件對物件比對 => equals
            // 值對值比對 => '=='
            if (userpass.getText().toString().equals(userpassword.getText().toString()) &&
                userpass.getText().toString().length() > 5 &&
                usercode.getText().toString().toLowerCase().equals(realCode)){
                    cellphone=usercell.getText().toString();
                    email=useremail.getText().toString();
                    password=userpassword.getText().toString();
                    SharedPreferences string=getSharedPreferences("string",MODE_PRIVATE);
                    string.edit().putString("userpassword",password).putString("useremail",email).putString("usercellphone",cellphone).putString("check","1").commit();

                    start(LoginActivity.class,true);
            }
            else{
                Toast.makeText(this, "密碼輸入錯誤", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            for (int i=0; i < e.size(); i++){
                e.get(i).setError("沒有輸入啦");
            }
        }
    }

    public List<EditText> checkIfEmptyOrNull(EditText... editTextArray){
        List<EditText> result = new ArrayList<EditText>();
        for(int i=0; i < editTextArray.length; i++){
            String text = editTextArray[i].getText().toString();
            if(TextUtils.isEmpty(text))
                result.add(editTextArray[i]);
        }
        return result;
    }

    private void updateLabel() {
        // 把calendar物件的時間轉成字串(依照myFormat的格式)
        String myFormat = "yyyy/MM/dd";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.TAIWAN);
        userbirthday.setText(sdf.format(myCalendar.getTime()));
    }

    public void onDatePick(View view) {
        new DatePickerDialog(this, DataSetListener,
                myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)
        ).show();
    }
    private void getPermissionsCamera(){
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA},1);
        }
    }
}