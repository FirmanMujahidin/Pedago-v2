package com.pedago2.Components;

import android.app.Dialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pedago2.R;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RecyclerViewAdapterDialogSearching extends RecyclerView.Adapter<RecyclerViewHolders> implements Filterable {

    private Dialog dialogq;
    public static List<ItemObject> itemList;
    public static List<ItemObject> itemListBackup;
    public static TextView tv_pilih_nama_sekolah;

    private LinearLayout layoutView;

    private int foto = 0;

    ListFilter listFilter;
    RecyclerViewAdapterDialogSearching recyclerViewAdapter = this;

    String valueToDialog[];

    public RecyclerViewAdapterDialogSearching() {
    }

    public RecyclerViewAdapterDialogSearching(Dialog dialog, List<ItemObject> itemLists, TextView Tv_pilih_nama_sekolah) {
        dialogq = dialog;
        itemList = itemLists;
        itemListBackup = itemLists;
        tv_pilih_nama_sekolah = Tv_pilih_nama_sekolah;
    }

    public List<ItemObject> getItemList() {
        return itemList;
    }

    @Override
    public int getItemViewType(int position) {
        int viewType = 1;
//        viewType = itemList.get(position).getLayoutType();
        return viewType;
    }

    @Override
    public RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        layoutView = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_list_dialog, null);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutView.setLayoutParams(lp);
        return new RecyclerViewHolders(layoutView);
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolders holder, int position) {//final int position

        try {

            holder.Nama.setText(itemList.get(holder.getAdapterPosition()).getNama());
            holder.Nama.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tv_pilih_nama_sekolah.setText(itemList.get(holder.getAdapterPosition()).getNama());
                    dialogq.dismiss();
                }
            });

        } catch (Exception ex) {
            ex.printStackTrace();
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

    @Override
    public Filter getFilter() {
        if (listFilter == null)
            listFilter = new ListFilter(this, itemListBackup);
        return listFilter;
    }

    private static class ListFilter extends Filter {

        private final RecyclerViewAdapterDialogSearching my_adapter;
        private final List<ItemObject> my_originalList;
        private final List<ItemObject> my_originalListBackup;

        private ListFilter(RecyclerViewAdapterDialogSearching adapter, List<ItemObject> originalList) {
            super();
            my_adapter = adapter;
            my_originalList = new LinkedList<>(originalList);
            my_originalListBackup = new ArrayList<>();
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            my_originalListBackup.clear();

            final FilterResults results = new FilterResults();

            if (constraint.length() == 0) {
                my_originalListBackup.addAll(my_originalList);
            } else {

                String filterPattern = constraint.toString().toLowerCase();

                for (final ItemObject user : my_originalList) {
                    if (user.getNama().toLowerCase().contains(filterPattern)) {
                        my_originalListBackup.add(user);
                    }
                }
            }
            results.values = my_originalListBackup;
            results.count = my_originalListBackup.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            RecyclerViewAdapterDialogSearching.itemListBackup.clear();
            RecyclerViewAdapterDialogSearching.itemListBackup.addAll((ArrayList<ItemObject>) results.values);
            my_adapter.notifyDataSetChanged();
        }
    }
}

class RecyclerViewHolders extends RecyclerView.ViewHolder {

    TextView Nama;

    public RecyclerViewHolders(View itemView) {
        super(itemView);
        Nama = itemView.findViewById(R.id.list_item_nama);
    }
}