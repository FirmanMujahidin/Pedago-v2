package com.pedago2.PanggilTutor;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.pedago2.Base.BasesActivity;
import com.pedago2.R;
import com.pedago2.adapter.MyRecyclerViewAdapter;

import java.util.ArrayList;

import butterknife.BindView;

public class PanggilTutorActivity extends BasesActivity implements MyRecyclerViewAdapter.ItemClickListener{


    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.view_pager_pgl_tutor)
    ViewPager viewPagerPglTutor;
    @BindView(R.id.tabs_one)
    RadioButton tabsOne;
    @BindView(R.id.tabs_two)
    RadioButton tabsTwo;
    @BindView(R.id.tabs_tree)
    RadioButton tabsTree;
    @BindView(R.id.tabs_group)
    RadioGroup tabsGroup;
    @BindView(R.id.recyclerview_list)
    RecyclerView recyclerviewHeader;

    private MyRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind(R.layout.activity_panggil_tutor);
        initToolbar("Panggilan Tutor", true);

//        tabsLayoutPglTutor.setupWithViewPager(viewPagerPglTutor);
//        setupViewPager(viewPagerPglTutor);
//        tabsLayoutPglTutor.setupWithViewPager(viewPagerPglTutor);


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

        // set up the RecyclerView
        LinearLayoutManager horizontalLayoutManagaer
                = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerviewHeader.setLayoutManager(horizontalLayoutManagaer);
        adapter = new MyRecyclerViewAdapter(getApplicationContext(), viewColoers, animalNames);
        adapter.setClickListener(this);
        recyclerviewHeader.setAdapter(adapter);


//        LinearLayoutManager layoutManager
//                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
//
//        recyclerviewHeader.setLayoutManager(layoutManager);

        setTabsLayoutPglTutor(viewPagerPglTutor);
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
    public void onItemClick(View view, int position) {
        Toast.makeText(getApplicationContext(), "You clicked " + adapter.getItem(position) + " on item position " + position, Toast.LENGTH_SHORT).show();
    }
}
