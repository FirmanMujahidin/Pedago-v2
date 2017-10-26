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

public class LengkapiProfil2Fragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    static List<ItemObject> rowListItem = new ArrayList<>();

    public LengkapiProfil2Fragment() {

    }

    public static LengkapiProfil2Fragment newInstance(String param1, String param2) {
        LengkapiProfil2Fragment fragment = new LengkapiProfil2Fragment();
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

        rowListItem = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_lengkapi_profile2, container, false);

        TextView tv_pilih_nama_sekolah = view.findViewById(R.id.tv_pilih_nama_sekolah);
        tv_pilih_nama_sekolah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rowListItem = new ArrayList<>();
                rowListItem.add(new ItemObject("SMAN 1 Karawang"));
                rowListItem.add(new ItemObject("SMAN 2 Karawang"));
                rowListItem.add(new ItemObject("SMAN 3 Karawang"));
                rowListItem.add(new ItemObject("SMAN 4 Karawang"));
                rowListItem.add(new ItemObject("SMAN 5 Karawang"));
                rowListItem.add(new ItemObject("SMAN 1 Bekasi"));
                rowListItem.add(new ItemObject("SMAN 2 Bekasi"));
                rowListItem.add(new ItemObject("SMAN 3 Bekasi"));
                rowListItem.add(new ItemObject("SMAN 4 Bekasi"));
                rowListItem.add(new ItemObject("SMAN 5 Bekasi"));
                rowListItem.add(new ItemObject("MA Al Muddatsiriyah"));
                rowListItem.add(new ItemObject("MA Al Qalam"));
                rowListItem.add(new ItemObject("MA JAMIAT KHAEIR"));
                rowListItem.add(new ItemObject("MA JAMIAT KHEIR"));
                Instant.dialog_searching(view.getContext(), "SMA", tv_pilih_nama_sekolah, rowListItem).show();
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
