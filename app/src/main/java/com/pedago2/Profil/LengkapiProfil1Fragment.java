package com.pedago2.Profil;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pedago2.Components.Instant;
import com.pedago2.Components.ItemObject;
import com.pedago2.R;

import java.util.ArrayList;
import java.util.List;

public class LengkapiProfil1Fragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    static List<ItemObject> rowListItem = new ArrayList<>();

    public LengkapiProfil1Fragment() {

    }

    public static LengkapiProfil1Fragment newInstance(String param1, String param2) {
        LengkapiProfil1Fragment fragment = new LengkapiProfil1Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lengkapi_profil1, container, false);
        TextView tv_provinsi = view.findViewById(R.id.tv_provinsi);
        tv_provinsi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rowListItem = new ArrayList<>();
                rowListItem.add(new ItemObject("Aceh"));
                rowListItem.add(new ItemObject("Bali"));
                rowListItem.add(new ItemObject("Banten"));
                rowListItem.add(new ItemObject("Bengkulu"));
                rowListItem.add(new ItemObject("Gorontalo"));
                rowListItem.add(new ItemObject("Jakarta"));
                rowListItem.add(new ItemObject("Jambi"));
                rowListItem.add(new ItemObject("Jawa Barat"));
                rowListItem.add(new ItemObject("Jawa Tengah"));
                rowListItem.add(new ItemObject("Jawa Timur"));
                rowListItem.add(new ItemObject("Kalimantan Barat"));
                rowListItem.add(new ItemObject("Kalimantan Selatan"));
                rowListItem.add(new ItemObject("Kalimantan Tengah"));
                rowListItem.add(new ItemObject("Kalimantan Timur"));
                rowListItem.add(new ItemObject("Kalimantan Utara"));
                rowListItem.add(new ItemObject("Kepulauan Bangka Belitung"));
                rowListItem.add(new ItemObject("Kepulauan Riau"));
                rowListItem.add(new ItemObject("Lampung"));
                rowListItem.add(new ItemObject("Maluku"));
                rowListItem.add(new ItemObject("Maluku Utara"));
                rowListItem.add(new ItemObject("Nusa Tenggara Barat"));
                rowListItem.add(new ItemObject("Nusa Tenggara Timur"));
                rowListItem.add(new ItemObject("Papua"));
                rowListItem.add(new ItemObject("Papua Barat"));
                rowListItem.add(new ItemObject("Riau"));
                rowListItem.add(new ItemObject("Sulawesi Barat"));
                rowListItem.add(new ItemObject("Sulawesi Selatan"));
                rowListItem.add(new ItemObject("Sulawesi Tengah"));
                rowListItem.add(new ItemObject("Sulawesi Tenggara"));
                rowListItem.add(new ItemObject("Sulawesi Utara"));
                rowListItem.add(new ItemObject("Sumatera Barat"));
                rowListItem.add(new ItemObject("Sumatera Selatan"));
                rowListItem.add(new ItemObject("Sumatera Utara"));
                rowListItem.add(new ItemObject("Yogyakarta"));
                Instant.dialog_searching(view.getContext(), "Provinsi", tv_provinsi, rowListItem).show();
            }
        });

        return view;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {

        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {

        void onFragmentInteraction(Uri uri);
    }
}
