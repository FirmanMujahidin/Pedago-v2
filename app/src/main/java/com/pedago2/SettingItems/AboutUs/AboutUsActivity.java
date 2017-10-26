package com.pedago2.SettingItems.AboutUs;

import android.os.Bundle;
import android.widget.ImageView;

import com.pedago2.R;
import com.pedago2.Base.BasesActivity;

import butterknife.BindView;
import me.biubiubiu.justifytext.library.JustifyTextView;


public class AboutUsActivity extends BasesActivity {

    @BindView(R.id.iv_logo_pedago)
    ImageView ivLogoPedago;
    @BindView(R.id.jf_text1)
    JustifyTextView jfText1;
    @BindView(R.id.jf_text2)
    JustifyTextView jfText2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind(R.layout.activity_tentang_kami);
        initToolbar("TENTANG KAMI", true);
    }
}
