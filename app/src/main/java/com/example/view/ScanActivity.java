package com.example.view;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class ScanActivity extends BaseActivity implements View.OnClickListener{
    Button qrbutton;
    Button camerabutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        qrbutton=(Button)findViewById(R.id.qrbutton);
        qrbutton.setOnClickListener(this);
        camerabutton=(Button)findViewById(R.id.camerabutton);
        camerabutton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.qrbutton:
                getCode();
                break;
            case R.id.camerabutton:
                start(CameraActivity.class);
                break;
        }
    }
    public void getCode(){
        ImageView ivCode=(ImageView)findViewById(R.id.qrcode);
        EditText etContent=(EditText)findViewById(R.id.edittext);
        BarcodeEncoder encoder = new BarcodeEncoder();
        try{
            Bitmap bit = encoder.encodeBitmap(etContent.getText().toString()
                    ,BarcodeFormat.QR_CODE,250,250);
            ivCode.setImageBitmap(bit);
        }catch (WriterException e){
            e.printStackTrace();
        }
    }
}