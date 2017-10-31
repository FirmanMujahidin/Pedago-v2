package com.pedago2.MateriPelajaran;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.pedago2.Base.BasesActivity;
import com.pedago2.Fragment.MaPelSatuFragment;
import com.pedago2.R;
import com.pedago2.adapter.MyRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MateriPelajaranActivity extends BasesActivity implements MyRecyclerViewAdapter.ItemClickListener {

//    @BindView(R.id.tabs_layout_materi)
//    TabLayout tabsLayoutMateri;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.view_pager_materi)
    ViewPager viewPagerMateri;
    @BindView(R.id.recyclerViewPagerHeader)
    RecyclerView recyclerViewPagerHeader;
    @BindView(R.id.tabs_one)
    RadioButton tabsOne;
    @BindView(R.id.tabs_two)
    RadioButton tabsTwo;
    @BindView(R.id.tabs_tree)
    RadioButton tabsTree;
    @BindView(R.id.tabs_group)
    RadioGroup tabsGroup;

    private MyRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind(R.layout.activity_materi_pelajaran);
        initToolbar("", true);

// data to populate the RecyclerView with
        ArrayList<Integer> viewColoers = new ArrayList<>();
        viewColoers.add(Color.BLUE);
        viewColoers.add(Color.YELLOW);
        viewColoers.add(Color.MAGENTA);
        viewColoers.add(Color.RED);
        viewColoers.add(Color.BLACK);

        ArrayList<String> animalNames = new ArrayList<>();
        animalNames.add("Horse");
        animalNames.add("Cow");
        animalNames.add("Camel");
        animalNames.add("Sheep");
        animalNames.add("Goat");


//            List<Data> data = new ArrayList<>();
//
//            data.add(new Data( R.drawable.ic_galery, "Image 1"));
//            data.add(new Data( R.drawable.ic_galery, "Image 2"));
//            data.add(new Data( R.drawable.ic_galery, "Image 3"));
//            data.add(new Data( R.drawable.ic_galery, "Image 1"));
//            data.add(new Data( R.drawable.ic_galery, "Image 2"));
//            data.add(new Data( R.drawable.ic_galery, "Image 3"));
//            data.add(new Data( R.drawable.ic_galery, "Image 1"));
//            data.add(new Data( R.drawable.ic_galery, "Image 2"));
//            data.add(new Data( R.drawable.ic_galery, "Image 3"));


        // set up the RecyclerView
        LinearLayoutManager horizontalLayoutManagaer
                = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewPagerHeader.setLayoutManager(horizontalLayoutManagaer);
        adapter = new MyRecyclerViewAdapter(getApplicationContext(), viewColoers, animalNames);
        adapter.setClickListener(this);
        recyclerViewPagerHeader.setAdapter(adapter);

//        tabsLayoutMateri.setupWithViewPager(viewPagerMateri);
        setTabsLayoutPglTutor(viewPagerMateri);
//        tabsLayoutMateri.setupWithViewPager(viewPagerMateri);
    }

    public void setTabsLayoutPglTutor(ViewPager viewPager){
        tabsGroup.setOnCheckedChangeListener((radioGroup, i) -> {
            if (!tabsOne.isChecked()) {
                tabsOne.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.cardview_light_background));
            }else
                tabsOne.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.cardview_light_background));
            if (!tabsTwo.isChecked()) {
                tabsTwo.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.cardview_light_background));
            }else
                tabsTwo.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.cardview_light_background));
            if (!tabsTree.isChecked()) {
                tabsTree.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.cardview_light_background));
            }else
                tabsTree.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.cardview_light_background));
        });
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


    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(getApplicationContext(), "You clicked " + adapter.getItem(position) + " on item position " + position, Toast.LENGTH_SHORT).show();
    }


}
