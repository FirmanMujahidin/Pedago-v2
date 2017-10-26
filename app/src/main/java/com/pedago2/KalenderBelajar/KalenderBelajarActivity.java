package com.pedago2.KalenderBelajar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.pedago2.R;
import com.pedago2.Components.ExpandableHeightGridView;
import com.pedago2.Components.GridCellCalendarAdapter;
import com.pedago2.Components.ItemObject;
import com.pedago2.Components.RecyclerViewAdapter1;

public class KalenderBelajarActivity extends AppCompatActivity implements View.OnClickListener {

    RecyclerView recyclerView;

    static List<ItemObject> rowListItem;
    private TextView currentMonth;
    private ImageView prevMonth;
    private ImageView nextMonth;
    private ExpandableHeightGridView calendarView;
    private GridCellCalendarAdapter adapter;
    private Calendar calendar;
    private int month, year;

    private static final String dateTemplate = "MMMM yyyy";

    RecyclerViewAdapter1 recyclerViewAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalender_belajar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle("KALENDER BELAJAR");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back);

        calendar = Calendar.getInstance(Locale.getDefault());
        month = calendar.get(Calendar.MONTH) + 1;
        year = calendar.get(Calendar.YEAR);

        calendarView = (ExpandableHeightGridView)findViewById(R.id.calendar);

        prevMonth = (ImageView)findViewById(R.id.prevMonth);
        currentMonth = (TextView)findViewById(R.id.currentMonth);
        currentMonth.setText(DateFormat.format(dateTemplate, calendar.getTime()));
        nextMonth = (ImageView) findViewById(R.id.nextMonth);

        adapter = new GridCellCalendarAdapter(this, month, year);
        adapter.notifyDataSetChanged();

        calendarView.setAdapter(adapter);

        calendarView.setExpanded(true);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerviewA);

        prevMonth.setOnClickListener(this);
        nextMonth.setOnClickListener(this);

        LinearLayoutManager lLayout = new LinearLayoutManager(this);
        lLayout.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(lLayout);
        recyclerView.setNestedScrollingEnabled(false);

        rowListItem = new ArrayList<>();

        recyclerViewAdapter = new RecyclerViewAdapter1(rowListItem);
        recyclerView.setAdapter(recyclerViewAdapter);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        rowListItem.add(new ItemObject("1","Retno Putri Ayu","Matematika","12.00 - 13.00 WIB"));
        rowListItem.add(new ItemObject("1","Retno Putri Ayu","Kimia","15.00 - 16.00 WIB"));
        rowListItem.add(new ItemObject("1","Retno Putri Ayu","Fisika","19.00 - 20.00 WIB"));

        rowListItem.add(new ItemObject("1","Alan Budi Kusuma","Matematika","12.00 - 13.00 WIB"));
        rowListItem.add(new ItemObject("1","Agista Nuri Ramadhani","Kewarganegaraan","15.00 - 16.00 WIB"));
        rowListItem.add(new ItemObject("1","Rohman Akbariza","Pendidikan Agama Islam","19.00 - 20.00 WIB"));

        rowListItem.add(new ItemObject("3","Alan Budi Kusuma","Matematika","12.00 - 13.00 WIB"));
        rowListItem.add(new ItemObject("3","Retno Putri Ayu","Kimia","15.00 - 16.00 WIB"));
        rowListItem.add(new ItemObject("3","Retno Putri Ayu","Fisika","19.00 - 20.00 WIB"));

        rowListItem.add(new ItemObject("4","Alan Budi Kusuma","Matematika","12.00 - 13.00 WIB"));
        rowListItem.add(new ItemObject("4","Agista Nuri Ramadhani","Kewarganegaraan","15.00 - 16.00 WIB"));
        rowListItem.add(new ItemObject("4","Rohman Akbariza","Pendidikan Agama Islam","19.00 - 20.00 WIB"));

        rowListItem.add(new ItemObject("5","Retno Putri Ayu","Matematika","12.00 - 13.00 WIB"));
        rowListItem.add(new ItemObject("5","Retno Putri Ayu","Kimia","15.00 - 16.00 WIB"));
        rowListItem.add(new ItemObject("5","Retno Putri Ayu","Fisika","19.00 - 20.00 WIB"));

        rowListItem.add(new ItemObject("8","Alan Budi Kusuma","Matematika","12.00 - 13.00 WIB"));
        rowListItem.add(new ItemObject("8","Agista Nuri Ramadhani","Kewarganegaraan","15.00 - 16.00 WIB"));
        rowListItem.add(new ItemObject("8","Rohman Akbariza","Pendidikan Agama Islam","19.00 - 20.00 WIB"));

        rowListItem.add(new ItemObject("9","Alan Budi Kusuma","Matematika","12.00 - 13.00 WIB"));
        rowListItem.add(new ItemObject("9","Retno Putri Ayu","Kimia","15.00 - 16.00 WIB"));
        rowListItem.add(new ItemObject("9","Retno Putri Ayu","Fisika","19.00 - 20.00 WIB"));

        rowListItem.add(new ItemObject("10","Alan Budi Kusuma","Matematika","12.00 - 13.00 WIB"));
        rowListItem.add(new ItemObject("10","Agista Nuri Ramadhani","Kewarganegaraan","15.00 - 16.00 WIB"));
        rowListItem.add(new ItemObject("10","Rohman Akbariza","Pendidikan Agama Islam","19.00 - 20.00 WIB"));
    }

    private void setGridCellAdapterToDate(Context context, int month, int year) {
        adapter = new GridCellCalendarAdapter(context, month, year);
        calendar.set(year, month - 1, calendar.get(Calendar.DAY_OF_MONTH));
        currentMonth.setText(DateFormat.format(dateTemplate, calendar.getTime()));
        adapter.notifyDataSetChanged();
        calendarView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {

        if (v == prevMonth) {
            if (month <= 1) {
                month = 12;
                year--;
            } else {
                month--;
            }
            Log.d("tag", "Setting Prev Month in GridCellCalendarAdapter: " + "Month: " + month + " Year: " + year);
            setGridCellAdapterToDate(v.getContext(), month, year);
        }
        if (v == nextMonth) {
            if (month > 11) {
                month = 1;
                year++;
            } else {
                month++;
            }
            Log.d("tag", "Setting Next Month in GridCellCalendarAdapter: " + "Month: " + month + " Year: " + year);
            setGridCellAdapterToDate(v.getContext(), month, year);
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}