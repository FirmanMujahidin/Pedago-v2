package com.pedago2.PanggilTutor;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import com.pedago2.Base.BasesActivity;
import com.pedago2.Fragment.MaPelSatuFragment;
import com.pedago2.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class PanggilTutorActivity extends BasesActivity {


    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.imageViewPagerHeader)
    ViewPager imageViewPagerHeader;
    @BindView(R.id.tabs_layout_pgl_tutor)
    TabLayout tabsLayoutPglTutor;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.view_pager_pgl_tutor)
    ViewPager viewPagerPglTutor;

    private ViewPagerAdapter adpViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind(R.layout.activity_panggil_tutor);
        initToolbar("Panggilan Tutor", true);

        tabsLayoutPglTutor.setupWithViewPager(viewPagerPglTutor);
        setupViewPager(viewPagerPglTutor);
        tabsLayoutPglTutor.setupWithViewPager(viewPagerPglTutor);

    }


    private void setupViewPager(ViewPager viewPager) {
        adpViewPager = new ViewPagerAdapter(getSupportFragmentManager());
        adpViewPager.addFrag(new MaPelSatuFragment(), "Kelas\n1 SMA");
        adpViewPager.addFrag(new MaPelSatuFragment(), "Kelas\n2 SMA");
        adpViewPager.addFrag(new MaPelSatuFragment(), "Kelas\n3 SMA");
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
}
