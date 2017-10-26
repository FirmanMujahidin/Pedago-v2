package com.pedago2.Profil;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.pedago2.Database.DatabaseHelper;
import com.pedago2.R;
import com.pedago2.Components.Instant;

public class LengkapiProfilActivity extends AppCompatActivity implements View.OnClickListener {

    ViewPager viewPager;

    Button btn1, btn2, btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lengkapi_profil);

        Instant.YouthmanualDialog1(this).show();

//        ((Button)findViewById(R.id.btn_ok)).setOnClickListener(this);

        viewPager = (ViewPager) findViewById(R.id.viewpager);

        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);

        (findViewById(R.id.ll_logout)).setOnClickListener(this);
        (findViewById(R.id.tv_logout)).setOnClickListener(this);
        (findViewById(R.id.btn_logout)).setOnClickListener(this);

        Instant.setupEventViewPager(viewPager, btn1, btn2, btn3);

        try {
            Instant.setupViewPager(getSupportFragmentManager(), viewPager,
                    new String[]{"1", "2", "3"},
                    new Fragment[]{
                            new LengkapiProfil1Fragment(),
                            new LengkapiProfil2Fragment(),
                            LengkapiProfil3Fragment.newInstance(this)
                    });
        } catch (Exception Oom) {
        }

    }

    @Override
    public void onBackPressed() {
        Instant.minimizeActivity(getBaseContext());
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.ll_logout ||
                view.getId() == R.id.btn_logout ||
                view.getId() == R.id.tv_logout)
        {
            DatabaseHelper db = new DatabaseHelper(view.getContext());
            String mail = db.getAllDataLogin()[0];
            Log.d("isi db login", "" + mail);
            db.deleteDataLogin(mail);
            finish();
        }
    }
}
