package com.pedago2.MateriPelajaran;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;

import com.pedago2.Base.BasesActivity;
import com.pedago2.Fragment.MaPelSatuFragment;
import com.pedago2.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MateriPelajaranActivity extends BasesActivity {

    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.tabs_layout_materi)
    TabLayout tabsLayoutMateri;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.view_pager_materi)
    ViewPager viewPagerMateri;
    @BindView(R.id.imageViewPagerHeader)
    ViewPager imageViewPagerHeader;

    private ViewPagerAdapter adpViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind(R.layout.activity_materi_pelajaran);
        initToolbar("",true);

        tabsLayoutMateri.setupWithViewPager(viewPagerMateri);
        setupViewPager(viewPagerMateri);
        tabsLayoutMateri.setupWithViewPager(viewPagerMateri);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mapel_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
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
