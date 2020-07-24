package com.example.view;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class FragmentActivity extends BaseActivity {
    ViewPager viewPager;
    TabLayout tabLayout;
    MainPagerAdapter mainPagerAdapter;
    String page;
    Dialog dialog;
    View viewdialog;
    Button ok,cancle;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        Bundle bundle=getIntent().getExtras();
        page=bundle.getString("page");
        viewPager=findViewById(R.id.viewpager);
        tabLayout=findViewById(R.id.tablayout);
        mainPagerAdapter=new MainPagerAdapter(getSupportFragmentManager());
        mainPagerAdapter.addFragment(new ScanFragment(),"QRCODE");
        mainPagerAdapter.addFragment(new HomeFragment(),"主頁");
        viewPager.setAdapter(mainPagerAdapter);
        switch (page){
            case "0":
                viewPager.setCurrentItem(0);
                break;
            case "1":
                viewPager.setCurrentItem(1);
                break;

        }
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        viewPager.setCurrentItem(0);
                        break;
                    case 1:
                        viewPager.setCurrentItem(1);
                        break;
                    case 2:
                        dialogexit();
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public void dialogexit() {
        dialog=new Dialog(this,R.style.dialognsq);
        dialog.setCancelable(false);
        viewdialog=getLayoutInflater().inflate(R.layout.dialogexit_layout,null);
        dialog.setContentView(viewdialog);
        ok=dialog.findViewById(R.id.ok);
        cancle=dialog.findViewById(R.id.cancle);
        dialog.show();
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();

            }
        });

    }


}




