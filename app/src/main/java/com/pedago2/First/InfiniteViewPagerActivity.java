package com.pedago2.First;

import android.content.Context;
import android.content.Intent;
import android.database.CursorIndexOutOfBoundsException;
import android.os.Bundle;

import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.pedago2.HomeScreen.HomeScreenActivity;
import com.pedago2.LoginAndRegistration.LoginActivity;
import com.pedago2.R;
import com.pedago2.Database.DatabaseHelper;

public class InfiniteViewPagerActivity extends AppCompatActivity {

    private InfiniteViewPager mViewPager;
    private InfinitePagerAdapter mPagerAdapter;
    private InfiniteCirclePageIndicator mPageIndicator;
    private final int JUMLAH_PAGE = 4;
    static Bundle bundleq;
    static int jml;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.infinite_pager_layout);

        bundleq = new Bundle();
        jml = new DatabaseHelper(getBaseContext()).getDataLoginCount();
        Log.d("jml login di infinite", "" + jml);
        try {
            if (jml != -1 || jml == 0) {
                mPagerAdapter = new InfinitePagerAdapter(new NumbersAdapter(this));

                mViewPager = (InfiniteViewPager) findViewById(R.id.sample_infinite_pager);
                mViewPager.setOffscreenPageLimit(JUMLAH_PAGE);
                mViewPager.setAdapter(mPagerAdapter);

                mPageIndicator = (InfiniteCirclePageIndicator) findViewById(R.id.sample_infinite_page_indicator);
                mPageIndicator.setViewPager(mViewPager);
                mPageIndicator.setCurrentItem(0);

                if (jml > 0) {
                    startActivity(new Intent(getBaseContext(), HomeScreenActivity.class));
                    finish();
                }
            }
        } catch (CursorIndexOutOfBoundsException e) {
            mPagerAdapter = new InfinitePagerAdapter(new NumbersAdapter(this));

            mViewPager = (InfiniteViewPager) findViewById(R.id.sample_infinite_pager);
            mViewPager.setOffscreenPageLimit(JUMLAH_PAGE);
            mViewPager.setAdapter(mPagerAdapter);

            mPageIndicator = (InfiniteCirclePageIndicator) findViewById(R.id.sample_infinite_page_indicator);
            mPageIndicator.setViewPager(mViewPager);
            mPageIndicator.setCurrentItem(0);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (jml > 0) {
            finish();
        }
    }

    private class NumbersAdapter extends PagerAdapter {

        private LayoutInflater mInflater;

        NumbersAdapter(Context context) {
            mInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return JUMLAH_PAGE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = getView(position);
            container.addView(view);

            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        private View getView(int position) {

            View pageView = mInflater.inflate(R.layout.layout_1, null);

            Button btn_login;
            Button btn_reg;

            try {
                switch (position) {
                    case 0:
                        pageView = mInflater.inflate(R.layout.layout_1, null);
                        break;
                    case 1:
                        pageView = mInflater.inflate(R.layout.layout_2, null);
                        break;
                    case 2:
                        pageView = mInflater.inflate(R.layout.layout_3, null);
                        break;
                    case 3:
                        pageView = mInflater.inflate(R.layout.layout_4, null);
                        btn_login = (Button) pageView.findViewById(R.id.btn_login);
                        btn_login.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //bundleq.putString("jenis_masuk", "login");
                                try {
                                    startActivity(new Intent(v.getContext(), LoginActivity.class).putExtra("jenis_masuk", "login"));
//                                    startActivityForResult(new Intent(v.getContext(), LoginActivity.class).putExtra("jenis_masuk", "login"), 1);
//                                    LoginActivity loginActivity = new LoginActivity();
//                                    LoginActivity.setActivity(InfiniteViewPagerActivity.this);
//                                    Intent i = new Intent(v.getContext(), loginActivity.getClass()).putExtra("jenis_masuk", "login");
//                                    startActivityFromChild(LoginActivity.activity, i, 1);

                                } catch (CursorIndexOutOfBoundsException e) {
//                                    LoginActivity login = new LoginActivity(InfiniteViewPagerActivity.this);
//                                    startActivityFromChild(new LoginActivity(),new Intent(v.getContext(), LoginActivity.class).putExtra("jenis_masuk", "login"),0);
                                }
                            }
                        });

                        btn_reg = (Button) pageView.findViewById(R.id.btn_register);
                        btn_reg.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //bundleq.putString("jenis_masuk", "login");
                                try {
                                    startActivity(new Intent(v.getContext(), LoginActivity.class).putExtra("jenis_masuk", "registrasi"));
//                                    startActivityForResult(new Intent(v.getContext(), LoginActivity.class).putExtra("jenis_masuk", "login"), 1);

//                                    LoginActivity loginActivity = new LoginActivity();
//                                    LoginActivity.setActivity(InfiniteViewPagerActivity.this);
//                                    Intent i = new Intent(v.getContext(), loginActivity.getClass()).putExtra("jenis_masuk", "registrasi");
//                                    startActivityFromChild(LoginActivity.activity, i, 1);

                                } catch (CursorIndexOutOfBoundsException e) {
//                                    LoginActivity login = new LoginActivity(InfiniteViewPagerActivity.this);
//                                    startActivityFromChild(new LoginActivity(),new Intent(v.getContext(), LoginActivity.class).putExtra("jenis_masuk", "login"),0);
                                }

                            }
                        });
                        break;
                }

                mPageIndicator.setCurrentItem(mViewPager.getCurrentItem());

            } catch (RuntimeException e) {
                e.printStackTrace();
            }

            return pageView;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 2 || requestCode == 2) {
            InfiniteViewPagerActivity.this.finish();
            Log.d("requestCode ", "invinite onActivityResult: " + requestCode);
            Log.d("resultCode ", "infinite onActivityResult: " + resultCode);
        }

        Log.d("requestCode ", "invinite onActivityResult: " + requestCode);
        Log.d("resultCode ", "infinite onActivityResult: " + resultCode);
    }
}