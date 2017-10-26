package com.pedago2.LatihanSoal;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import java.util.Calendar;

import com.pedago2.HomeScreen.HomeScreenActivity;
import com.pedago2.R;

public class LatihanSoalActivity extends AppCompatActivity implements View.OnClickListener{

    ViewFlipper viewFlipper;

    String jawaban;
    String jawabanTemp;
    int year = 0;
    int month = 0;
    int day = 0;

    public static final String[] MONTHS = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    String monthname = MONTHS[month];
    String currentDate = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_latihan_soal);

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //View view = inflater.inflate(R.layout.layout_kuis, null);

        viewFlipper = (ViewFlipper) findViewById(R.id.flipper);
//        viewFlipper.addView(view);

        String soal[] = new String[]{"soal 1",
                "soal 2",
                "soal 3",
                "soal 4",
                "soal 5",
                "soal 6",
                "soal 7",
                "soal 8",
                "soal 9",
                "soal 10"};

        String opsi[][] = new String[][]
                {
                        {"opsi 1", "opsi 2", "opsi 3", "opsi 4", "opsi 5"},
                        {"opsi A", "opsi B", "opsi C", "opsi D", "opsi E"},
                        {"opsi X", "opsi A", "opsi M", "opsi P", "opsi P"},
                        {"opsi H", "opsi T", "opsi M", "opsi L", "opsi X"},
                        {"opsi C", "opsi S", "opsi S", "opsi X", "opsi O"},
                        {"opsi I", "opsi N", "opsi D", "opsi O", "opsi M"},
                        {"opsi 1", "opsi 2", "opsi 3", "opsi 4", "opsi 5"},
                        {"opsi 1", "opsi 2", "opsi 3", "opsi 4", "opsi 5"},
                        {"opsi 1", "opsi 2", "opsi 3", "opsi 4", "opsi 5"},
                        {"opsi 1", "opsi 2", "opsi 3", "opsi 4", "opsi 5"},
                        {"opsi O", "opsi P", "opsi M", "opsi A", "opsi N"}
                };

        btn_n_text_Dinamis(soal, opsi, layoutDinamis(soal.length, inflater), viewFlipper);

        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        final Intent Myinttent = new Intent(this, HomeScreenActivity.class);
//        button5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getApplicationContext(), "finish", Toast.LENGTH_LONG).show();
//                startActivity(Myinttent);
////				finish();
//            }
//        });

    }// end oncreate

    View layoutDinamis(int jml_soal, LayoutInflater inflater)[] {
        View view[] = new View[jml_soal];
        //= inflater.inflate(R.layout.layout_kuis, null);
        for (int i = 0; i < jml_soal; i++) {
            view[i] = inflater.inflate(R.layout.layout_kuis, null);
        }
        return view;
    }

    void btn_n_text_Dinamis(String soal[], String opsi[][], View viewArr[], final ViewFlipper viewFlipper) {
        for (int i = 0; i < soal.length; i++) {

            ((TextView) ((LinearLayout) viewArr[i]).getChildAt(0)).setText("" + soal[i]);

//            for(int j=0; j<opsi.length; j++){
                for(int k=0; k<opsi[i].length; k++){
                    ((Button) ((LinearLayout) viewArr[i]).getChildAt(k+1)).setText(""+opsi[i][k]);
                    Log.d("coba", " "+opsi[i][k]);
                }
//            }

            ((Button) ((LinearLayout) viewArr[i]).getChildAt(1)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    jawabanTemp = "" + ((Button) view).getText();
                }
            });

            ((Button) ((LinearLayout) viewArr[i]).getChildAt(2)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    jawabanTemp = "" + ((Button) view).getText();
                }
            });

            ((Button) ((LinearLayout) viewArr[i]).getChildAt(3)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    jawabanTemp = "" + ((Button) view).getText();
                }
            });

            ((Button) ((LinearLayout) viewArr[i]).getChildAt(4)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    jawabanTemp = "" + ((Button) view).getText();
                }
            });

            ((Button) ((LinearLayout) viewArr[i]).getChildAt(5)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    jawabanTemp = "" + ((Button) view).getText();
                }
            });

            //back
            ((LinearLayout) ((LinearLayout) viewArr[i]).getChildAt(6)).getChildAt(0).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    jawabanTemp = "" + ((Button) view).getText();

                    viewFlipper.setInAnimation(inFromLeftAnimation());
                    viewFlipper.setOutAnimation(outToRightAnimation());
                    viewFlipper.showPrevious();
                }
            });

            //next
            ((LinearLayout) ((LinearLayout) viewArr[i]).getChildAt(6)).getChildAt(1).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    jawabanTemp = "" + ((Button) view).getText();
                    viewFlipper.setInAnimation(inFromRightAnimation());
                    viewFlipper.setOutAnimation(outToLeftAnimation());
                    viewFlipper.showNext();
                }
            });

            viewFlipper.addView(viewArr[i]);
        }

    }

    private Animation inFromRightAnimation() {

        Animation inFromRight = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, +1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f);

        inFromRight.setDuration(500);
        inFromRight.setInterpolator(new AccelerateInterpolator());
        return inFromRight;
    }

    private Animation outToLeftAnimation() {
        Animation outtoLeft = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, -1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f);
        outtoLeft.setDuration(500);
        outtoLeft.setInterpolator(new AccelerateInterpolator());
        return outtoLeft;
    }

    private Animation inFromLeftAnimation() {
        Animation inFromLeft = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, -1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f);
        inFromLeft.setDuration(500);
        inFromLeft.setInterpolator(new AccelerateInterpolator());
        return inFromLeft;
    }

    private Animation outToRightAnimation() {
        Animation outtoRight = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, +1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f);
        outtoRight.setDuration(500);
        outtoRight.setInterpolator(new AccelerateInterpolator());
        return outtoRight;
    }

    @Deprecated
    @Override
    protected Dialog onCreateDialog(int id) {
        // 1 setting myDateSetListener
        // 2 setting onCreateDialog
        // 3 showDialog(1);
        switch (id) {
            case 1:
                return new DatePickerDialog(this, myDateSetListener, year, month, day);
        }
        return null;
    }

    DatePickerDialog.OnDateSetListener myDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int j, int k) {
            year = i;
            month = j;
            day = k;
            monthname = MONTHS[month];
            currentDate = new StringBuilder().append(day).append(" ").append(monthname).append(" ").append(year).toString();
        }
    };

    @Override
    public void onClick(View view) {

    }
}
