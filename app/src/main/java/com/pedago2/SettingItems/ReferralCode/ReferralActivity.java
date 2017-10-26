package com.pedago2.SettingItems.ReferralCode;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.pedago2.R;
import com.pedago2.SettingItems.Fragment.ParentalFragment;
import com.pedago2.SettingItems.Fragment.ReferralFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReferralActivity extends AppCompatActivity {

    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.container)
    ViewPager mViewPager;
    @BindView(R.id.main_content)
    CoordinatorLayout mainContent;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
//    @BindView(R.id.rdgroup_tabs_kode)
//    RadioGroup rdgroupTabsKode;

    private ViewPagerAdapter adpViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_referral);
        ButterKnife.bind(this);
        setTitle("");

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back_long_white);

        tabs.setupWithViewPager(mViewPager);
        setupViewPager(mViewPager);
        tabs.setupWithViewPager(mViewPager);

//        rdgroupTabsKode.setOnCheckedChangeListener((group, checkedId) -> getData(checkedId));
    }

    private void setupViewPager(ViewPager viewPager) {
        adpViewPager = new ViewPagerAdapter(getSupportFragmentManager());
        adpViewPager.addFrag(new ParentalFragment(), "Kode Parental");
        adpViewPager.addFrag(new ReferralFragment(), "Kode Referral");
        viewPager.setAdapter(adpViewPager);
    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {

        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

//    private void getData(int id){
//        switch (id){
//            case R.id.rdb_Parental:
//                Intent inParental = new Intent(getApplicationContext(),ParentalFragment.class);
//                startActivity(inParental);
//                break;
//            case R.id.rdb_referal:
//                Intent inReferral = new Intent(getApplicationContext(),ReferralActivity.class);
//                startActivity(inReferral);
//                break;
//        }
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
