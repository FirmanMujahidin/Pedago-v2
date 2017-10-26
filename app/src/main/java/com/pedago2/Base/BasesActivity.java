package com.pedago2.Base;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.pedago2.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by firma on 12-Oct-17.
 */

public class BasesActivity extends AppCompatActivity {

    private static final String TAG = "BasesActivity";

    @Nullable
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private ProgressDialog pDialog;
    private static BasesActivity mInstance;

    public static synchronized BasesActivity getmIntance(){
        return mInstance;
    }

    protected void bind (int layout){
        setContentView(layout);
        ButterKnife.bind(this);
        setupProgressDialog();
    }

    protected void initToolbar(String title, boolean backhome){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(backhome);
        getSupportActionBar().setHomeButtonEnabled(backhome);
        getSupportActionBar().setTitle(title.equals("") ? "" : title);
        if (backhome){
            toolbar.setNavigationOnClickListener(v -> onBackPressed());
            toolbar.setNavigationIcon(R.drawable.ic_back_long_white);
        }
    }


    private void setupProgressDialog() {
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        mInstance = this;
    }

    protected void showToast(String msg, int length) {
        Toast.makeText(this, msg, length).show();
    }

    protected void showToast(String msg ){
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    protected void showLoading(String msg){
        pDialog.setMessage(msg);
        pDialog.show();
    }


    protected void hideLoading() {
        if (pDialog.isShowing())
            pDialog.cancel();
    }

    protected boolean isInternetConnectionAvailable() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork == null)
            return false;
        return activeNetwork.isConnectedOrConnecting();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

}
