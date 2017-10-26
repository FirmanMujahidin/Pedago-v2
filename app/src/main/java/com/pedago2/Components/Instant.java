package com.pedago2.Components;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.pedago2.HomeScreen.HomeScreenActivity;
import com.pedago2.Profil.LengkapiProfilActivity;
import com.pedago2.R;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.pedago2.Database.DatabaseHelper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;

import static com.facebook.FacebookSdk.getApplicationContext;

public class Instant {

    static String token = "";

    public Instant() {
    }

    public void saveImageViewToDrawable(Context context, ImageView Icon[], Drawable drawableIcon[]) {
        ImageView IconTemp[] = new ImageView[Icon.length];

        for (int iconIndex = 0; iconIndex < Icon.length; iconIndex++) {
            IconTemp[iconIndex] = new ImageView(context);
            IconTemp[iconIndex] = Icon[iconIndex];
            drawableIcon[iconIndex] = Icon[iconIndex].getBackground();
        }
    }

//      API 21
//    public static void ClearAllActivity(Context context) {
//        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
//        if (am != null) {
//            List<ActivityManager.AppTask> tasks = am.getAppTasks();
//            if (tasks != null) {
//                tasks.get(0).finishAndRemoveTask();
//            }
//        }
//    }

    public static boolean cekKoneksi(Context context) {

        boolean konek = false;

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo wifiNetwork = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        NetworkInfo mobileNetwork = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if (wifiNetwork != null && wifiNetwork.isConnected()) {
            konek = true;
            return konek;
        } else if (mobileNetwork != null && mobileNetwork.isConnected()) {
            konek = true;
            return konek;
        }

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnected()) {
            konek = true;
            return konek;
        }

        return konek;
    }

    public static Snackbar noInternetSnackbar(Context context) {
        View my_view = ((Activity) context).findViewById(android.R.id.content);
        final Snackbar snackbar = Snackbar.make(my_view, "No Connection Internet", Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction("X", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                snackbar.dismiss();
            }
        });
        return snackbar;
    }

    public static Dialog noConnectionInternet(Context context) {

        final Dialog dialog = new Dialog(context);
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_no_connection);
        Window window = dialog.getWindow();
        window.setBackgroundDrawableResource(android.R.color.transparent);

        Button dialogBtn = (Button) dialog.findViewById(R.id.btn_ok);
        dialogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        return dialog;
    }

    public static Dialog dialog_searching(Context context, String title, TextView tv_pilih_nama_sekolah, List<ItemObject> rowListItem) {

        RecyclerViewAdapterDialogSearching recyclerViewAdapterDialogSearching;

        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_searching);

        ((TextView)dialog.findViewById(R.id.tv_title)).setText(title);

        LinearLayoutManager lLayout = new LinearLayoutManager(dialog.getContext());
        lLayout.setOrientation(LinearLayoutManager.VERTICAL);

        RecyclerView recyclerView = dialog.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(lLayout);

        recyclerViewAdapterDialogSearching = new RecyclerViewAdapterDialogSearching(dialog, rowListItem, tv_pilih_nama_sekolah);
        recyclerView.setAdapter(recyclerViewAdapterDialogSearching);

        EditText filter = dialog.findViewById(R.id.et_search);

        filter.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int arg1, int arg2, int arg3) {
                recyclerViewAdapterDialogSearching.getFilter().filter(s);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            }

            @Override
            public void afterTextChanged(Editable arg0) {
            }
        });

        Window window = dialog.getWindow();
        window.setBackgroundDrawableResource(android.R.color.transparent);
        return dialog;
    }

    public static Dialog dialog_transisi(Context context) {

        final Dialog dialog = new Dialog(context);
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_transisi_login);
        (dialog.findViewById(R.id.dialog_progress)).animate()
                .setDuration(1000).alpha(1)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {

                    }
                });

        Random rand = new Random();
        int random_number = rand.nextInt(2);

        String kata[] = {
                "Semua orang itu jenius. Tetapi jika kejeniusannya di bidang tertentu di nilai dari sudut pandang kejeniusan di bidang yang lain. Maka orang tersebut akan menyadari dirinya bodoh, bukan jenius.",
                "Pendidikan adalah senjata paling kuat yang dapat kita gunakan untuk menaklukan dunia",
                "Petualangan terbesar yang dapat kamu ambil adalah menjalani kehidupan yang kamu impikan"
        };

        String orang[] = {"-Albert Einstein, Terjemahan disederhanakan-",
                "-Nelson Mandela-",
                "-Oprah Winfrey-"};

        int background[] = {
                R.drawable.einstein,
                R.drawable.nelson,
                R.drawable.oprah
        };

        ((TextView) dialog.findViewById(R.id.tv_kata)).setText("" + kata[random_number]);
        ((TextView) dialog.findViewById(R.id.tv_orang)).setText("" + orang[random_number]);
        (dialog.findViewById(R.id.linear_layout_background)).setBackground(context.getResources().getDrawable(background[random_number]));

        Window window = dialog.getWindow();
        window.setBackgroundDrawableResource(android.R.color.transparent);
        return dialog;
    }

    public static Dialog YouthmanualDialog1(Context context) {

        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_two_options);

        (dialog.findViewById(R.id.btn_sudah))
                .setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            Instant.YouthmanualDialog2(view.getContext()).show();
                                            dialog.dismiss();
                                        }
                                    }
                );

        (dialog.findViewById(R.id.btn_belum))
                .setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            dialog.dismiss();
                                        }
                                    }
                );

        Window window = dialog.getWindow();
        window.setBackgroundDrawableResource(android.R.color.transparent);
        return dialog;
    }

    public static Dialog YouthmanualDialog2(Context context) {

        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_youthmanual);

        (dialog.findViewById(R.id.btn_baik))
                .setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            dialog.dismiss();
                                        }
                                    }
                );

        Window window = dialog.getWindow();
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawableResource(android.R.color.transparent);
        return dialog;
    }

    public static Dialog UjiAkademikDialog(LengkapiProfilActivity lengkapiProfilActivity) {
        //dialog ini muncul setelah registrasi

        final Dialog dialog = new Dialog(lengkapiProfilActivity);
        dialog.setContentView(R.layout.dialog_two_options);

        TextView tv_kata = dialog.findViewById(R.id.tv_kata);
        tv_kata.setText("Ayo lanjutkan ke uji akademik sesuai level kamu untuk mengetahui seberapa dekat lagi kamu menggapai cita - cita mu sesuai dengan minat dan bakat");

        Button btn_sudah = dialog.findViewById(R.id.btn_sudah);
        btn_sudah.setText("Lanjutkan");

        Button btn_belum = dialog.findViewById(R.id.btn_belum);
        btn_belum.setText("Nanti, ingatkan saya");

        btn_sudah.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View view) {
//                                             start activity Ujian
                                             dialog.dismiss();
                                             lengkapiProfilActivity.finish();
                                         }
                                     }
        );

        btn_belum.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View view) {
                                             //activity.startActivity(new Intent(activity, HomeScreenActivity.class));

                                             Intent i = new Intent(lengkapiProfilActivity, HomeScreenActivity.class)
                                                     .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                                                     .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                             lengkapiProfilActivity.startActivity(i);
                                             lengkapiProfilActivity.finish();
                                             dialog.dismiss();
                                         }
                                     }
        );

        Window window = dialog.getWindow();
        window.setBackgroundDrawableResource(android.R.color.transparent);
        return dialog;
    }

    public static void show_dialog_n_save_data_login(final Activity activity, final String mail, final String pass, final String jenis_masuk, final Dialog dialog_transisi, final Class next_activity) {
        try {
            new Handler().postDelayed(() -> {
                DatabaseHelper db = new DatabaseHelper(getApplicationContext());
                db.InsertLogin(new String[]{"" + mail, " " + pass});
                activity.startActivity(new Intent(activity, next_activity).putExtra("jenis_masuk", jenis_masuk).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                dialog_transisi.dismiss();
                activity.finish();
            }, 2000L);
            Log.d("dialog transisi", "is acctivity finish " + activity.isFinishing());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void minimizeActivity(Context context) {
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(startMain);
    }

    public static void setTabLayoutHeader(TabLayout tabLayout) {
        final Typeface font = Typeface.createFromAsset(tabLayout.getContext().getAssets(), "RobotoCondensed/Roboto-Medium.ttf");
        final Typeface font2 = Typeface.createFromAsset(tabLayout.getContext().getAssets(), "RobotoCondensed/Roboto-Bold.ttf");

        tabLayout.setTabTextColors(Color.BLACK, Color.parseColor("#006fa6"));
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#006fa6"));
    }

    public static void setupViewPager(FragmentManager fm, ViewPager viewPager, String title[], Fragment fragments[]) {
//        viewPager.getChildAt(0)
        if (viewPager != null) {

            Adapter adapter = new Adapter(fm);

            for (int i = 0; i < fragments.length; i++) {
                adapter.addFragment("" + title[i], fragments[i]);//
            }

            viewPager.setAdapter(adapter);
        }
    }

    public static void setupEventViewPager(final ViewPager viewPager, final Button button1, final Button button2, final Button button3, final Button button4, final Button button5) {

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Deprecated
            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    button1.setBackground(viewPager.getResources().getDrawable(R.drawable.btn_home_menu_selected));
                    button2.setBackground(viewPager.getResources().getDrawable(R.drawable.btn_home_menu));
                    button3.setBackground(viewPager.getResources().getDrawable(R.drawable.btn_home_menu));
                    button4.setBackground(viewPager.getResources().getDrawable(R.drawable.btn_home_menu));
                    button5.setBackground(viewPager.getResources().getDrawable(R.drawable.btn_home_menu));
                } else if (position == 1) {
                    button1.setBackground(viewPager.getResources().getDrawable(R.drawable.btn_home_menu));
                    button2.setBackground(viewPager.getResources().getDrawable(R.drawable.btn_home_menu_selected));
                    button3.setBackground(viewPager.getResources().getDrawable(R.drawable.btn_home_menu));
                    button4.setBackground(viewPager.getResources().getDrawable(R.drawable.btn_home_menu));
                    button5.setBackground(viewPager.getResources().getDrawable(R.drawable.btn_home_menu));
                } else if (position == 2) {
                    button1.setBackground(viewPager.getResources().getDrawable(R.drawable.btn_home_menu));
                    button2.setBackground(viewPager.getResources().getDrawable(R.drawable.btn_home_menu));
                    button3.setBackground(viewPager.getResources().getDrawable(R.drawable.btn_home_menu_selected));
                    button4.setBackground(viewPager.getResources().getDrawable(R.drawable.btn_home_menu));
                    button5.setBackground(viewPager.getResources().getDrawable(R.drawable.btn_home_menu));
                } else if (position == 3) {
                    button1.setBackground(viewPager.getResources().getDrawable(R.drawable.btn_home_menu));
                    button2.setBackground(viewPager.getResources().getDrawable(R.drawable.btn_home_menu));
                    button3.setBackground(viewPager.getResources().getDrawable(R.drawable.btn_home_menu));
                    button4.setBackground(viewPager.getResources().getDrawable(R.drawable.btn_home_menu_selected));
                    button5.setBackground(viewPager.getResources().getDrawable(R.drawable.btn_home_menu));
                } else if (position == 4) {
                    button1.setBackground(viewPager.getResources().getDrawable(R.drawable.btn_home_menu));
                    button2.setBackground(viewPager.getResources().getDrawable(R.drawable.btn_home_menu));
                    button3.setBackground(viewPager.getResources().getDrawable(R.drawable.btn_home_menu));
                    button4.setBackground(viewPager.getResources().getDrawable(R.drawable.btn_home_menu));
                    button5.setBackground(viewPager.getResources().getDrawable(R.drawable.btn_home_menu_selected));
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                // ((ScrollView)((LinearLayout)viewPager.getChildAt(0)).getChildAt(0)).setScrollY(0);
            }
        });

    }

    public static void setupEventViewPager(final ViewPager viewPager, final Button button1, final Button button2, final Button button3) {

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Deprecated
            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    button1.setBackground(viewPager.getResources().getDrawable(R.drawable.selected));
                    button2.setBackground(viewPager.getResources().getDrawable(R.drawable.dua));
                    button3.setBackground(viewPager.getResources().getDrawable(R.drawable.tiga));
                } else if (position == 1) {
                    button1.setBackground(viewPager.getResources().getDrawable(R.drawable.satu));
                    button2.setBackground(viewPager.getResources().getDrawable(R.drawable.selected));
                    button3.setBackground(viewPager.getResources().getDrawable(R.drawable.tiga));
                } else if (position == 2) {
                    button1.setBackground(viewPager.getResources().getDrawable(R.drawable.satu));
                    button2.setBackground(viewPager.getResources().getDrawable(R.drawable.dua));
                    button3.setBackground(viewPager.getResources().getDrawable(R.drawable.selected));
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                // ((ScrollView)((LinearLayout)viewPager.getChildAt(0)).getChildAt(0)).setScrollY(0);
            }
        });
    }

    public static class getDataGratis {

        String id_barang[];
        String tv_nama[];
        String tv_harga[];
        String url_foto[];
        String progress_caryta[];
        String max_progress_caryta[];
        String jumlah_cerita[];
        String nama_toko[];
        String lokasi_toko[];
        ImageView foto[];

        public getDataGratis(final Context context,
                             String url, int PageStart,
                             List<ItemObject> rowListItem1,
                             List<ItemObject> rowListItem2,
                             List<ItemObject> rowListItemTemp) {

            rowListItemTemp.clear();

// coba tembak barang gratis .addHeader("Authorization", tokenQ).addHeader("Page", "" + PageStart)
//            "http://api.pedago.id/api-v2/all-free?page="
            try {
                Ion.with(context)
                        .load(url + PageStart)//192.168.20.20
                        .asJsonObject()
                        .setCallback(new FutureCallback<JsonObject>() {
                            @Override
                            public void onCompleted(Exception e, JsonObject result) {

                                Log.d("test", "url API " + result.toString());
                                Log.d("test", "url API " + ((JsonObject) result.getAsJsonArray("data").get(0)).get("id_produk").toString().replaceAll("\"", ""));

                                if (e != null) {

                                    e.getStackTrace();
                                    Log.d("ERROR", e.toString());

                                } else if (result != null) {

                                    id_barang = new String[result.getAsJsonArray("data").size()];
                                    tv_nama = new String[result.getAsJsonArray("data").size()];
                                    tv_harga = new String[result.getAsJsonArray("data").size()];
                                    url_foto = new String[result.getAsJsonArray("data").size()];

                                    if (url_foto == null) {
                                        url_foto = new String[10];
                                    }

                                    progress_caryta = new String[result.getAsJsonArray("data").size()];
                                    max_progress_caryta = new String[result.getAsJsonArray("data").size()];
                                    jumlah_cerita = new String[result.getAsJsonArray("data").size()];
                                    nama_toko = new String[result.getAsJsonArray("data").size()];
                                    lokasi_toko = new String[result.getAsJsonArray("data").size()];

                                    for (int i = 0; i < result.getAsJsonArray("data").size(); i++) {

//                                        JsonObject json_data = (JsonObject) result.getAsJsonArray("data").get(i);

                                        id_barang[i] = ((JsonObject) result.getAsJsonArray("data").get(i)).get("id_produk").toString().replaceAll("\"", "");
                                        Log.d("url API ", "id " + id_barang[i]);

                                        tv_nama[i] = ((JsonObject) result.getAsJsonArray("data").get(i)).get("nama_produk").toString().replaceAll("\"", "");
                                        Log.d("url API", "Nama " + tv_nama[i]);

                                        tv_harga[i] = ((JsonObject) result.getAsJsonArray("data").get(i)).get("harga_produk").toString().replaceAll("\"", "");
                                        Log.d("url API", "harga " + tv_harga[i]);
//                                        http://api.pedago.id/images/produk/
//                                        https://www.pedago.id
                                        url_foto[i] = "https://www.pedago.id" + ((JsonObject) result.getAsJsonArray("data").get(i)).get("foto_small").toString().replaceAll("\"", "");
                                        Log.d("url API", "foto " + url_foto[i]);
                                        //.replaceAll("192.168.20.20", "www.pedago.id");

                                        progress_caryta[i] = ((JsonObject) result.getAsJsonArray("data").get(i)).get("progress_cerita").toString().replaceAll("\"", "");
                                        max_progress_caryta[i] = ((JsonObject) result.getAsJsonArray("data").get(i)).get("max_cerita").toString().replaceAll("\"", "");

                                        jumlah_cerita[i] = ((JsonObject) result.getAsJsonArray("data").get(i)).get("jumlah_cerita").toString().replaceAll("\"", "");

                                        nama_toko[i] = " "; //+ ((JsonObject) result.getAsJsonArray("data").get(i)).get("nama_toko").toString().replaceAll("\"", "");
                                        lokasi_toko[i] = " ";//+((JsonObject) result.getAsJsonArray("data").get(i)).get("lokasi_toko").toString().replaceAll("\"", "");
                                        Log.d("looping", "loop " + tv_nama[i]);
                                    }
                                }
                            }
                        }).get();
            } catch (NullPointerException ie) {
            } catch (InterruptedException ie) {
            } catch (ExecutionException ex) {
            }

            try {
                foto = new ImageView[url_foto.length];
            } catch (NullPointerException e) {
                e.printStackTrace();
                foto = new ImageView[10];
            }

            for (int i = 0; i < foto.length; i++) {

                foto[i] = new ImageView(context);
                try {
                    foto[i].setImageBitmap(Ion.with(context).load(url_foto[i]).withBitmap().asBitmap().get());
                } catch (NullPointerException exo) {
                    exo.printStackTrace();
                } catch (ExecutionException ex) {
                    ex.printStackTrace();
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }

                try {
//                    rowListItemTemp.add(new ItemObject(2, id_barang[i], tv_nama[i], url_foto[i], tv_harga[i], progress_caryta[i], max_progress_caryta[i], jumlah_cerita[i], nama_toko[i], lokasi_toko[i]));
                } catch (NullPointerException exo) {
                    exo.printStackTrace();
                }

                try {
                    if (i % 2 == 0) { //kiri kanan
                        rowListItem1.add(rowListItemTemp.get(i));
                        Log.d(i + "%" + 2 + " ", "" + tv_nama[i]);
                        Log.d("kanan Nama", "" + tv_nama[i]);
//                        Log.d("kanan progres", "" + progress_caryta[i]);
//                        Log.d("kanan harga", "" + tv_harga[i]);
                        Log.d("kanan foto", "" + url_foto[i]);
                    } else {
                        rowListItem2.add(rowListItemTemp.get(i));
                        Log.d(i + "% N ", "" + tv_nama[i]);
                        Log.d("kiri Nama", "" + tv_nama[i]);
//                        Log.d("kiri progres", "" + progress_caryta[i]);
//                        Log.d("kiri harga", "" + tv_harga[i]);
                        Log.d("kiri foto", "" + url_foto[i]);
                    }
                } catch (IndexOutOfBoundsException ie) {
                }
            }
        }
    }

    public static String getToken(final Context context, final String tokey) {

        try {
            Ion.with(context)
                    .load("http://api.pedago.id/api-v2/get-token")//192.168.20.20
                    .addHeader("AppId", tokey)
                    .asJsonObject()
                    .setCallback(new FutureCallback<JsonObject>() {
                        @Override
                        public void onCompleted(Exception e, JsonObject result) {

//                            Log.d("3 fikri result", result.get("diagnostic").getAsJsonObject().get("status").toString());
//                            Log.d("ERROR token", result.toString());
                            if (e != null) {

                                e.getStackTrace();
                                Log.d("token", e.toString());

                            } else if (result != null) {

//                                String status = result.get("diagnostic").getAsJsonObject().get("status").toString();
                                String status = result.get("token").getAsString();
                                token = status;
                                //token = result.getAsString();
                                Log.d("imei tokey", " " + tokey);
                                Log.d("tokenQ", " " + token);
//                                if (status.matches("401")) {
//                                    Toast.makeText(context, "Tunggu, untuk terhubung ke internet", Toast.LENGTH_SHORT).show();
//                                } else {}
//                                    result.get("response").getAsJsonObject().get("token_type").toString() + " " +
//                                    result.get("response").getAsJsonObject().get("access_token").toString();
//                                    Log.d("fikri", "" + token.replaceAll("\"", ""));
//                                    token = token.replaceAll("\"", "");
                            }
                        }
                    }).get();

        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (InterruptedException ie) {

        } catch (ExecutionException ex) {

        }
        return "" + token;
    }

    public String getImei(Context context) {
        int permissionCheck = ContextCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE);
        String imei = "";
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_PHONE_STATE}, 999);
        } else {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            imei = telephonyManager.getDeviceId();
        }
        return imei;
    }

    public static class getDetailGratis {

        String tv_nama[];
        String tv_harga[];
        String url_foto[];
        String progress_caryta[];
        String max_progress_caryta[];
        String jumlah_cerita[];
        String nama_toko[];
        String lokasi_toko[];
        ImageView foto[];

// "nama_produk": "Liburan ke Atlantis the palm selama 4 hari",
// "harga_produk": "19360000",
// "deskripsi_produk":
// "<p style=\"color: rgb(74, 74, 74); font-family: AcuminPro, arial, helvetica, sans-serif; font-size: 14px;\">Hotel&nbsp;<a href=\"http://lifestyle.liputan6.com/read/2688801/sensasi-menegangkan-berpetualang-di-img-world-of-adventure-dubai\" style=\"color: rgb(246, 118, 56);\">Atlantis The Palm</a>&nbsp;terdiri dari terowongan bawah laut, yang akan menjadi pengalaman mendalam. Desain pemandangan bawah laut seperti reruntuhan peradaban yang hilang dan kecelakaan kapal, membuat pengalaman Anda menakjubkan dan berkesan.</p><p style=\"color: rgb(74, 74, 74); font-family: AcuminPro, arial, helvetica, sans-serif; font-size: 14px;\">Anda akan menemukan koleksi kehidupan bawah laut yang paling menakjubkan dan berwarna-warni di 20 aquarium yang indah, dalam terowongan Hotel Atlantis The Palm. Termasuk melihat langsung dua binatang langka yakni buaya putih.</p>",
// "status_free": 1,
// "stok": "1",
// "foto": "ABS2017001029-58eb2f20dd8d0.png", tambahin "api.pedago.id/images/produk/"
// "progress_cerita": "0",
// "link_share": "/carita/liburan-ke-atlantis-the-palm-selama-4-hari.html",

        public getDetailGratis(final Context context,
                               final String idProduct,
                               final TextView tv_nama_barang,
                               final TextView tv_harga_barang
        ) {

            try {
                Ion.with(context)
                        .load("http://api.pedago.id/api-v2/produk/" + idProduct)
                        .asJsonObject()
                        .setCallback(new FutureCallback<JsonObject>() {
                            @Override
                            public void onCompleted(Exception e, JsonObject result) {

                                Log.d("hasil", "hasil" + result.toString());

                                if (e != null) {

                                    e.getStackTrace();
                                    Log.d("ERROR", e.toString());

                                } else if (result != null) {

//                                  "response" "product" "id_produk" "ABS2017000989"
//                                  JsonObject json_data = (JsonObject) ((JsonObject) result.get("response")).get("product");

                                    tv_nama_barang.setText("" + result.get("nama_produk").toString().replaceAll("\"", ""));
                                    tv_harga_barang.setText("" + result.get("harga_produk").toString().replaceAll("\"", ""));
//                                  url_foto="" + json_data.get("harga_produk").getAsString();
                                    //JsonObject json_data2 = (JsonObject) json_data1.get("product");
                                    //Toast.makeText(context, ""+json_data2.get("id_produk").getAsString(), Toast.LENGTH_SHORT).show();
                                    //Toast.makeText(context, "Nama barang" + tv_nama_barang.getText().toString(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        }).get();
            } catch (NullPointerException ne) {
                ne.printStackTrace();
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            } catch (ExecutionException ex) {
                ex.printStackTrace();
            }

//            try {
//                foto = new ImageView[url_foto.length];
//            } catch (NullPointerException e) {
//                e.printStackTrace();
//                foto = new ImageView[10];
//            }

//            for (int i = 0; i < foto.length; i++) {
//
//                foto[i] = new ImageView(context);
//
//                try {
//                    foto[i].setImageBitmap(Ion.with(context).load("" + url_foto[i]).withBitmap().asBitmap().get());
//                } catch (NullPointerException exo) {
//                    exo.printStackTrace();
//                } catch (ExecutionException ex) {
//                    ex.printStackTrace();
//                } catch (InterruptedException ie) {
//                    ie.printStackTrace();
//                }
//            }
        }
    }

    private static class Adapter extends FragmentPagerAdapter {

        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public Adapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(String title, Fragment fragment) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }

        @Override
        public void setPrimaryItem(View container, int position, Object object) {
            super.setPrimaryItem(container, position, object);
        }
    }

    //  namaFile = /storage/sdcard1/Print Sekarang/Ijazah Fikri.pdf
    public static void openFile(Context c, String namaFile) {
//        open
        MimeTypeMap myMime = MimeTypeMap.getSingleton();
        Intent newIntent = new Intent(Intent.ACTION_VIEW);
        String mimeType = myMime.getMimeTypeFromExtension("pdf");
        File filePath = new File(namaFile);//+ "//DIR//"
//        Log.d("lokasi", "onCreate: "+fileExt(".pdf").substring(1));
        newIntent.setDataAndType(Uri.fromFile(filePath), mimeType);
        newIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            c.startActivity(newIntent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(c, "No handler for this type of file.", Toast.LENGTH_LONG).show();
        }
    }

    public static Bitmap getImageQ(String path) throws IOException {

        Bitmap bitmap = null;

        try {

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(path, options);
            int srcWidth = options.outWidth;
            int srcHeight = options.outHeight;
            int[] newWH = new int[2];
            newWH[0] = srcWidth / 3;
            newWH[1] = (newWH[0] * srcHeight) / srcWidth;

            int inSampleSize = 1;
            while (srcWidth / 3 >= newWH[0]) {
                srcWidth /= 3;
                srcHeight /= 3;
                inSampleSize *= 3;
            }

            options.inJustDecodeBounds = false;
            options.inDither = false;
            options.inSampleSize = inSampleSize;
            options.inScaled = false;
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;

            Bitmap sampledSrcBitmap = BitmapFactory.decodeFile(path, options);

            ExifInterface exif = new ExifInterface(path);
            String s = exif.getAttribute(ExifInterface.TAG_ORIENTATION);

            Matrix matrix = new Matrix();
//        float rotation = rotationForImage(MainActivity3.this, Uri.fromFile(new File(path)));
//        if (rotation != 0f) {
//            matrix.preRotate(rotation);
//        }
            bitmap = Bitmap.createBitmap(sampledSrcBitmap, 0, 0, sampledSrcBitmap.getWidth(), sampledSrcBitmap.getHeight(), matrix, true);

        } catch (RuntimeException ex) {

        }
        return bitmap;
    }

    public static Bitmap getMyImage(Context context, String path) throws IOException {

        Bitmap bitmap = null;

        try {
            try {
                bitmap = Ion.with(context)
                        .load("http://192.168.20.20/images/produk/medium/ABS-JG-AA%2001-58539433b03f9.jpg")
                        .withBitmap()
                        .asBitmap()
                        .get();
            } catch (ExecutionException ea) {
                ea.printStackTrace();
            } catch (InterruptedException ei) {
                ei.printStackTrace();
            }
        } catch (RuntimeException ex) {

        }
        return bitmap;
    }

    public Bitmap getResizedBitmap(Bitmap bm, int newWidth) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float aspectRatio = bm.getWidth() / (float) bm.getHeight();
        int newheight = Math.round(newWidth / aspectRatio);
        float scaleHeight = ((float) newheight) / height;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false);
        bm.recycle();
        return resizedBitmap;
    }

    public static String getPathQ(Uri uri, Activity activity) {
        String[] projection = {MediaStore.MediaColumns.DATA};
        Cursor cursor = activity.managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

//      API 21
//    public static void ClearAllActivity(Context context) {
//        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
//        if (am != null) {
//            List<ActivityManager.AppTask> tasks = am.getAppTasks();
//            if (tasks != null) {
//                tasks.get(0).finishAndRemoveTask();
//            }
//        }
//    }

    public static Dialog UjiAkademikDialog(Activity activity) {
        //dialog ini muncul setelah registrasi
        final Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.dialog_two_options);

        TextView tv_kata = (TextView) dialog.findViewById(R.id.tv_kata);
        tv_kata.setText("Ayo lanjutkan ke uji akademik sesuai level kamu untuk mengetahui seberapa dekat lagi kamu menggapai cita - cita mu sesuai dengan minat dan bakat");

        Button btn_sudah = (Button) dialog.findViewById(R.id.btn_sudah);
        btn_sudah.setText("Lanjutkan");

        Button btn_belum = (Button) dialog.findViewById(R.id.btn_belum);
        btn_belum.setText("Nanti, ingatkan saya");

        btn_sudah.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View view) {
//                                             start activity Ujian
                                             dialog.dismiss();
                                             activity.finish();
                                         }
                                     }
        );

        btn_belum.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View view) {
                                             activity.startActivity(new Intent(activity, HomeScreenActivity.class));
                                             dialog.dismiss();
                                             activity.finish();
                                         }
                                     }
        );

        Window window = dialog.getWindow();
        window.setBackgroundDrawableResource(android.R.color.transparent);
        return dialog;
    }

    //https://www.pedago.id/images/produk/medium/ABS2017000981-58cd119ca0539.jpg
//http://dev.pedago.id/images/produk/medium/ABS2017000981-58cd119ca0539.jpg
//http://beta.pedago.id/images/produk/medium/ABS2017000981-58cd119ca0539.jpg
    //public static class getDetailGratis

//      API 21
//    public static void ClearAllActivity(Context context) {
//        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
//        if (am != null) {
//            List<ActivityManager.AppTask> tasks = am.getAppTasks();
//            if (tasks != null) {
//                tasks.get(0).finishAndRemoveTask();
//            }
//        }
//    }

}
/*

 @Override
    public void run()
    {
        try
        {
            //Obtain the thread's token
            synchronized (thread)
            {
                //While the counter is smaller than four
                while(counter <= 4)
                {
                    //Wait 850 milliseconds
                    thread.wait(850);
                    //Increment the counter
                    counter++;

                    //update the changes to the UI thread
                    handler.post(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            //Set the current progress.
                            progressDialog.setProgress(counter*25);
                        }
                    });
                }
            }
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        //This works just like the onPostExecute method from the AsyncTask class
        handler.post(new Runnable()
        {
            @Override
            public void run()
            {
                //Close the progress dialog
                progressDialog.dismiss();
            }
        });

        //Try to "kill" the thread, by interrupting its execution
        synchronized (thread)
        {
            thread.interrupt();
        }
    }

 */