package com.pedago2.Fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.pedago2.Moel.MapelModel;
import com.pedago2.R;
import com.pedago2.adapter.MaPelAdapter;
import com.pedago2.adapter.MyRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class MaPelSatuFragment extends Fragment{


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    Unbinder unbinder;

    public MaPelAdapter mAdapter;
    public RecyclerView.LayoutManager layoutManager;
    public List<MapelModel> data_model;
    public Context mContext;



    public MaPelSatuFragment() {
        // Required empty public constructor
    }


    public static MaPelSatuFragment newInstance(String param1, String param2) {
        MaPelSatuFragment fragment = new MaPelSatuFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        mAdapter = new MaPelAdapter(data_model, mContext);
//        recyclerView.setAdapter(mAdapter);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        mAdapter.notifyDataSetChanged();
//        getVideo();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mapel_satu, container, false);
        unbinder = ButterKnife.bind(this, view);



        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    public void getVideo(){
//            mAdapter = new MaPelAdapter(data_model,getApplicationContext());
//            layoutManager = new LinearLayoutManager(getApplicationContext());
//            recyclerView.setLayoutManager(layoutManager);
//            recyclerView.setAdapter(mAdapter);
    }
}
