package com.example.view;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.journeyapps.barcodescanner.BarcodeEncoder;


public class ScanFragment extends BaseFragment implements View.OnClickListener {
    Button qrbutton;
    Button camerabutton;
    ImageView ivCode;
    EditText etContent;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_scan,container,false);
        qrbutton=(Button)view.findViewById(R.id.qrbutton);
        qrbutton.setOnClickListener(this);
        camerabutton=(Button)view.findViewById(R.id.camerabutton);
        camerabutton.setOnClickListener(this);
        ivCode=(ImageView)view.findViewById(R.id.qrcode);
        etContent=(EditText)view.findViewById(R.id.edittext);
        return view;
    }
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

        BarcodeEncoder encoder = new BarcodeEncoder();
        try{
            Bitmap bit = encoder.encodeBitmap(etContent.getText().toString()
                    , BarcodeFormat.QR_CODE,250,250);
            ivCode.setImageBitmap(bit);
        }catch (WriterException e){
            e.printStackTrace();
        }
    }
}
