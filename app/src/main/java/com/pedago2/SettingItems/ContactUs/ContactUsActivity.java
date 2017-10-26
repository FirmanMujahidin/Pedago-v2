package com.pedago2.SettingItems.ContactUs;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.pedago2.Base.BasesActivity;
import com.pedago2.R;

import butterknife.BindView;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;

public class ContactUsActivity extends BasesActivity {

    private static final String TAG = "ContactUsActivity";


    @BindView(R.id.iv_facebook)
    ImageView ivFacebook;
    @BindView(R.id.bt_facebook)
    Button btFacebook;
    @BindView(R.id.iv_whatsapp)
    ImageView ivWhatsapp;
    @BindView(R.id.bt_whatsapp)
    Button btWhatsapp;
    @BindView(R.id.iv_line)
    ImageView ivLine;
    @BindView(R.id.bt_line)
    Button btLine;
    @BindView(R.id.iv_instagram)
    ImageView ivInstagram;
    @BindView(R.id.bt_instagram)
    Button btInstagram;
    @BindView(R.id.iv_mail)
    ImageView ivMail;
    @BindView(R.id.bt_mail)
    Button btMail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind(R.layout.activity_hubungi);
        initToolbar("HUBUNGI KAMI", true);


    }
    @OnClick(R.id.bt_facebook)//"com.facebook.katana"
    public void OnClick_bt_facebook(){
        PackageManager pm = getPackageManager();
        try {
            Intent in;
            new Intent(Intent.ACTION_MAIN);
            PackageManager managerclock = getPackageManager();
            in = managerclock.getLaunchIntentForPackage("com.facebook.katana");
            PackageInfo info = pm.getPackageInfo("com.facebook.katana", PackageManager.GET_META_DATA);
            in.addCategory(Intent.CATEGORY_LAUNCHER);
            startActivity(in);
        } catch (PackageManager.NameNotFoundException e) {
            Toasty.warning(this, "Facebook not Installed", Toast.LENGTH_SHORT,true).show();
        }
        /*ini fungsi untuk membuka lewat browser*/
//        Intent in = new Intent(getApplicationContext(), WebViewActivity.class);
//        in.putExtra("url","https://www.facebook.com/pedago.id/");
//        startActivity(in);
    }




    @OnClick(R.id.bt_whatsapp) //com.whatsapp
    public void OnClick_bt_whatsapp(){
        PackageManager pm = getPackageManager();
            try {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Demo Test");
                PackageInfo info = pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
                sendIntent.setPackage("com.whatsapp");
                sendIntent.setType("text/plain");
                startActivity(sendIntent);

            } catch (PackageManager.NameNotFoundException e) {
                Toasty.warning(this, "WhatsApp not Installed", Toast.LENGTH_SHORT,true).show();
            }

        /*ini fungsi untuk mengirim massages ke whatsapp*/
//        Intent sendIntent = new Intent();
//        sendIntent.setAction(Intent.ACTION_SEND);
//        sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
//        sendIntent.setType("text/plain");
//        sendIntent.setPackage("com.whatsapp");
//        startActivity(sendIntent);

        /*ini fungsi untuk membuka aplikasi*/
//        Intent in;
//        new Intent(Intent.ACTION_MAIN);
//        PackageManager managerclock = getPackageManager();
//        in = managerclock.getLaunchIntentForPackage("com.whatsapp");
//        in.addCategory(Intent.CATEGORY_LAUNCHER);
//        startActivity(in);
    }

    @OnClick(R.id.bt_line)
    public void OnClick_bt_line(){
        PackageManager pm = getPackageManager();
        try {
            Intent in;
            new Intent(Intent.ACTION_MAIN);
            PackageManager managerclock = getPackageManager();
            in = managerclock.getLaunchIntentForPackage("jp.naver.line.android");
            PackageInfo info = pm.getPackageInfo("jp.naver.line.android", PackageManager.GET_META_DATA);
            in.addCategory(Intent.CATEGORY_LAUNCHER);
            startActivity(in);
        }catch (PackageManager.NameNotFoundException e){
            Toasty.warning(this, "Line not Installed", Toast.LENGTH_SHORT,true).show();
        }
    }

    @OnClick(R.id.bt_instagram)
    public void OnClick_bt_instagram(){
        PackageManager pm = getPackageManager();
        try {
            Intent in;
            new Intent(Intent.ACTION_MAIN);
            PackageManager managerclock = getPackageManager();
            in = managerclock.getLaunchIntentForPackage("com.instagram.android");
            PackageInfo info = pm.getPackageInfo("com.instagram.android", PackageManager.GET_META_DATA);
            in.addCategory(Intent.CATEGORY_LAUNCHER);
            startActivity(in);
        }catch (PackageManager.NameNotFoundException e){
            Toasty.warning(this, "Instagram not Installed", Toast.LENGTH_SHORT,true).show();
        }

    }

    @OnClick(R.id.bt_mail)
    public void OnClick_bt_mail(){
        String to = "hotline@pedago.id";
        Intent in = new Intent(Intent.ACTION_SEND);
        in.putExtra(Intent.EXTRA_EMAIL, new String[]{ to});
        in.setPackage("com.google.android.gm");
        in.setType("message/rfc822");
        startActivity(in.createChooser(in, "Kirim email dengan"));
    }



}
