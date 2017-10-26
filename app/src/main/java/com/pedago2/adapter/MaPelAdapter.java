package com.pedago2.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pedago2.Moel.MapelModel;
import com.pedago2.R;

import java.util.List;

/**
 * Created by firma on 23-Oct-17.
 */

public class MaPelAdapter extends RecyclerView.Adapter<MaPelAdapter.ViewHolder>{

    List<MapelModel> data_model;
    private Context mContext;

    public MaPelAdapter(List<MapelModel> data_model, Context mContext) {
        this.data_model = data_model;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_video_mapel, null);
        ViewHolder viewHoldere = new ViewHolder(view);
        return viewHoldere;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.title_judul.setText("" + data_model.get(position).getTitle_judl());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView title_judul;

        public ViewHolder(View view) {
            super(view);
            title_judul = view.findViewById(R.id.title_judul);
        }
    }
}
