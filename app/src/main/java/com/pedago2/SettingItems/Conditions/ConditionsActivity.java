package com.pedago2.SettingItems.Conditions;

import android.os.Bundle;

import com.pedago2.R;
import com.pedago2.Base.BasesActivity;

public class ConditionsActivity extends BasesActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind(R.layout.activity_kondisi_ketentuan);
        initToolbar("KONDISI DAN KETENTUAN",true);
    }
}
