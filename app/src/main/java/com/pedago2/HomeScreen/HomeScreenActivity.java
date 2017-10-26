package com.pedago2.HomeScreen;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import com.pedago2.ChatTutor.ChatTutorActivity;
import com.pedago2.KalenderBelajar.KalenderBelajarActivity;
import com.pedago2.LatihanSoal.LatihanSoalActivity;
import com.pedago2.MateriPelajaran.MateriPelajaranActivity;
import com.pedago2.SettingItems.AboutUs.AboutUsActivity;
import com.pedago2.SettingItems.ContactUs.ContactUsActivity;
import com.pedago2.PanggilTutor.PanggilTutorActivity;
import com.pedago2.R;
import com.pedago2.Components.Instant;
import com.pedago2.Database.DatabaseHelper;
import com.pedago2.SettingItems.Conditions.ConditionsActivity;
import com.pedago2.SettingItems.Privasi.PrivasiActivity;
import com.pedago2.SettingItems.ReferralCode.ReferralActivity;
import com.pedago2.SettingItems.Setting.SettingActivity;

import java.util.ArrayList;

public class HomeScreenActivity extends AppCompatActivity implements View.OnClickListener {

    ViewPager viewPager;

    Instant instant;

    Toolbar toolbar;
    Button btn_profil, btn_roadmap, btn_raport, btn_suggest, btn_school;

    //start banner
    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private static final Integer[] IMAGES = {R.drawable.banner1, R.drawable.banner1, R.drawable.banner1, R.drawable.banner1};
    private ArrayList<Integer> ImagesArray = new ArrayList<>();

    boolean mDialogShown = false;
    PopupWindow popupwindow_obj;

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home_screen);
//        setResult(2);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");

        setSupportActionBar(toolbar);

        instant = new Instant();

        btn_profil = (Button) findViewById(R.id.btn_profil);
        btn_roadmap = (Button) findViewById(R.id.btn_roadmap);
        btn_raport = (Button) findViewById(R.id.btn_raport);
        btn_suggest = (Button) findViewById(R.id.btn_suggest);
        btn_school = (Button) findViewById(R.id.btn_school);

//        ActionBar.LayoutParams layoutParams = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
//        layoutParams.gravity = Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL;
//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        params.weight = 1.0f;
//        params.gravity = Gravity.START;
//        params.setMargins(0, 0, 0, 0);

        viewPager = (ViewPager) findViewById(R.id.viewpager);

        instant.setupEventViewPager(viewPager, btn_profil, btn_roadmap, btn_raport, btn_suggest, btn_school);

        ImageView btn_materi_pelajaran = (ImageView) findViewById(R.id.btn_materi_pelajaran);
        ImageView btn_latihan_soal = (ImageView) findViewById(R.id.btn_latihan_soal);
        ImageView btn_panggil_tutor = (ImageView) findViewById(R.id.btn_panggil_tutor);
        ImageView btn_chat_tutor = (ImageView) findViewById(R.id.btn_chat_tutor);

        btn_materi_pelajaran.setOnClickListener(view -> startActivity(new Intent(view.getContext(), MateriPelajaranActivity.class)));

        btn_latihan_soal.setOnClickListener(view -> startActivity(new Intent(view.getContext(), LatihanSoalActivity.class)));

        btn_panggil_tutor.setOnClickListener(view -> startActivity(new Intent(view.getContext(), PanggilTutorActivity.class)));

        btn_chat_tutor.setOnClickListener(view -> startActivity(new Intent(view.getContext(), ChatTutorActivity.class)));

//        Typeface font = Typeface.createFromAsset(tabLayout.getContext().getAssets(), "RobotoCondensed/Roboto-Medium.ttf");
//        Typeface font2 = Typeface.createFromAsset(tabLayout.getContext().getAssets(), "RobotoCondensed/Roboto-Bold.ttf");

        try {
            Instant.setupViewPager(getSupportFragmentManager(), viewPager,
                    new String[]{"Profil", "Roadmap", "Overview Raport", "Suggest Latihan", "School Activity"},
                    new Fragment[]{
                            new ProfileFragment(),
                            new RoadmapFragment(),
                            new Overview(),
                            new SuggestFragment(),
                            new SchoolFragment()
                    });
        } catch (OutOfMemoryError Oom) {
        }

        btn_profil.setOnClickListener(this);
        btn_roadmap.setOnClickListener(this);
        btn_raport.setOnClickListener(this);
        btn_suggest.setOnClickListener(this);
        btn_school.setOnClickListener(this);

        viewPager.setCurrentItem(0, true);
        viewPager.setOffscreenPageLimit(4);

//        getParent().finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;

//        getMenuInflater().inflate(R.menu.sample_actions, menu);
//        getMenuInflater().inflate(R.menu.activity_main_drawer, menu);

        // Associate searchable configuration with the SearchView
//        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
//        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
//        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
//        searchView.setEnabled(true);
        //return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.activity_main_update_menu_item:
                Log.d("diluar cek", "cek: " + mDialogShown);

                popupwindow_obj = popupDisplay(this);

                if (!popupwindow_obj.isShowing()) {//mDialogShown
                    popupwindow_obj.showAsDropDown(toolbar, (toolbar.getWidth()), ((int) toolbar.getY())); // where u want show on view click event popupwindow.showAsDropDown(view, x, y);

                    mDialogShown = popupwindow_obj.isShowing();

                    Log.d("cek", "cek: " + mDialogShown);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
                }
                return super.onOptionsItemSelected(item);

            default:
                return true;//super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1 || requestCode == 1) {
            mDialogShown = false;
        }
        Log.d("resultCode", "resultCode : " + resultCode);
    }

    PopupWindow popupDisplay(Context context) {

        final PopupWindow popupWindow = new PopupWindow(context);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        try {
            View view;
            view = inflater.inflate(R.layout.my_layout, null);

//        btn_kalender btn_referral btn_ketentuan btn_call_us btn_privasi btn_about_us btn_pengaturan btn_logout

            (view.findViewById(R.id.btn_kalender)).setOnClickListener(this);
            (view.findViewById(R.id.btn_referral)).setOnClickListener(v -> {
                Intent in = new Intent(this, ReferralActivity.class);
                startActivity(in);
            });
            (view.findViewById(R.id.btn_ketentuan)).setOnClickListener(v -> {
                Intent in = new Intent(this, ConditionsActivity.class);
                startActivity(in);
            });
            (view.findViewById(R.id.btn_call_us)).setOnClickListener(v -> {
                Intent in = new Intent(this, ContactUsActivity.class);
                startActivity(in);
            });
            (view.findViewById(R.id.btn_privasi)).setOnClickListener(v -> {
                Intent in = new Intent(this, PrivasiActivity.class);
                startActivity(in);
            });
            (view.findViewById(R.id.btn_about_us)).setOnClickListener(v -> {
                Intent in = new Intent(this, AboutUsActivity.class);
                startActivity(in);
            });
            (view.findViewById(R.id.btn_pengaturan)).setOnClickListener(v -> {
                Intent in = new Intent(this, SettingActivity.class);
                startActivity(in);
            });
            (view.findViewById(R.id.btn_logout)).setOnClickListener(this);

            popupWindow.setFocusable(true);
            popupWindow.setWidth(toolbar.getWidth() / 3);
            popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
            popupWindow.setContentView(view);
        } catch (OutOfMemoryError er) {
            er.printStackTrace();
        }
        return popupWindow;
    }

    @Override
    public void onClick(View v) {
        Button b = (Button) v;
        switch (b.getId()) {
            case R.id.btn_kalender:
                startActivity(new Intent(HomeScreenActivity.this, KalenderBelajarActivity.class));
                break;
            case R.id.btn_referral:
                break;
            case R.id.btn_ketentuan:
                break;
            case R.id.btn_call_us:
                break;
            case R.id.btn_privasi:
                break;
            case R.id.btn_about_us:
                break;
            case R.id.btn_pengaturan:
                break;
            case R.id.btn_logout:
                DatabaseHelper db = new DatabaseHelper(this);
                String mail = db.getAllDataLogin()[0];
                Log.d("isi db login", "" + mail);
                db.deleteDataLogin(mail);
                finish();
                break;
            case R.id.btn_profil:
                viewPager.setCurrentItem(0, true);
                break;
            case R.id.btn_roadmap:
                viewPager.setCurrentItem(1, true);
                break;
            case R.id.btn_raport:
                viewPager.setCurrentItem(2, true);
                break;
            case R.id.btn_suggest:
                viewPager.setCurrentItem(3, true);
                break;
            case R.id.btn_school:
                viewPager.setCurrentItem(4, true);
                break;
        }
    }
}
