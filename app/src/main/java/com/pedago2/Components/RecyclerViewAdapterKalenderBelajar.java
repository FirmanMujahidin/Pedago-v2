package com.pedago2.Components;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.pedago2.KalenderBelajar.DetailKalenderBelajarActivity;
import com.pedago2.R;

import java.util.List;

public class RecyclerViewAdapterKalenderBelajar extends RecyclerView.Adapter<RecyclerViewHoldersKalenderBelajar> {

    public static List<ItemObject> itemList;

    private View layoutView;

    public RecyclerViewAdapterKalenderBelajar() {
    }

    public RecyclerViewAdapterKalenderBelajar(List<ItemObject> itemLists) {
        itemList = itemLists;
    }

    public List<ItemObject> getItemList() {
        return itemList;
    }

    @Override
    public int getItemViewType(int position) {
        int viewType = 1;
        viewType = itemList.get(position).getLayoutType();
        return viewType;
    }

    @Override
    public RecyclerViewHoldersKalenderBelajar onCreateViewHolder(ViewGroup parent, int viewType) {

//        if (viewType == 1)
            layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_list_calendar, null);

        return new RecyclerViewHoldersKalenderBelajar(layoutView);
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHoldersKalenderBelajar holder, int position) {//final int position

        try {
            holder.No.setText(itemList.get(holder.getAdapterPosition()).getNo());
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }

        try {
            holder.Nama.setText(itemList.get(holder.getAdapterPosition()).getNama());
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }

        try {
            holder.Pelajaran.setText(itemList.get(holder.getAdapterPosition()).getPelajaran());
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }

        try {
            holder.Jam.setText(itemList.get(holder.getAdapterPosition()).getJam());
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }

        try {
// Bitmap bitmap = ((BitmapDrawable)holder.Foto.getDrawable()).getBitmap();
// ini berhasil   Instant.getMyImage(holder.Foto.getContext(), "http://192.168.20.20/images/produk/medium/ABS-JG-AA%2001-58539433b03f9.jpg");
// Bitmap bitmap = Ion.with(layoutView.getContext()).load(itemList.get(holder.getAdapterPosition()).getUrl_foto()).withBitmap().asBitmap().get();
//.setImageBitmap(bitmap);
//          holder.Foto = itemList.get(holder.getAdapterPosition()).getUrl_foto();
            holder.Next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundleq = new Bundle();
                    bundleq.putString("no", itemList.get(holder.getAdapterPosition()).getNo());//"ABS2017001035"
                    bundleq.putString("nama", itemList.get(holder.getAdapterPosition()).getNama());//"ABS2017001035"
                    v.getContext().startActivity(new Intent(v.getContext(), DetailKalenderBelajarActivity.class).putExtras(bundleq));
                }
            });
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        try {
            return itemList.size();
        } catch (NullPointerException e) {

        }
        return 0;
    }
}

class RecyclerViewHoldersKalenderBelajar extends RecyclerView.ViewHolder {

    TextView No;
    TextView Nama;
    TextView Pelajaran;
    TextView Jam;
    Button Next;

    RecyclerViewHoldersKalenderBelajar(View itemView) {
        super(itemView);

        No = (TextView) itemView.findViewById(R.id.list_item_no);
        Nama = (TextView) itemView.findViewById(R.id.list_item_nama);
        Jam = (TextView) itemView.findViewById(R.id.list_item_jam);
        Pelajaran = (TextView) itemView.findViewById(R.id.list_item_pelajaran);
        Next = (Button) itemView.findViewById(R.id.list_item_next);
    }
}