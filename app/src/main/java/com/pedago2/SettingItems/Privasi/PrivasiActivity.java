package com.pedago2.SettingItems.Privasi;

import android.os.Bundle;
import android.widget.TextView;

import com.pedago2.R;
import com.pedago2.Base.BasesActivity;

import butterknife.BindView;

public class PrivasiActivity extends BasesActivity {

    @BindView(R.id.text_privasi)
    TextView textPrivasi;
    @BindView(R.id.text_privasi1)
    TextView textPrivasi1;
    @BindView(R.id.text_privasi2)
    TextView textPrivasi2;
    @BindView(R.id.text_privasi3)
    TextView textPrivasi3;
    @BindView(R.id.text_privasi4)
    TextView textPrivasi4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind(R.layout.activity_privasi);
        initToolbar("PRIVASI", true);
    }
}
