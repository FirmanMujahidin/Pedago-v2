package com.pedago2.LoginAndRegistration;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.pedago2.Components.Instant;
import com.pedago2.Database.DatabaseHelper;
import com.pedago2.First.InfiniteViewPagerActivity;
import com.pedago2.HomeScreen.HomeScreenActivity;
import com.pedago2.Profil.LengkapiProfilActivity;
import com.pedago2.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.regex.Pattern;

import static android.Manifest.permission.READ_CONTACTS;

public class LoginActivity extends AppCompatActivity {
    //implements OnClickListener{
    //LoaderCallbacks<Cursor>,

    private static final int REQUEST_READ_CONTACTS = 0;
    static String imei;
    //    static String token;
    static String nama;

    static String email;
    static String password;

    static Dialog dialog_transisi;
    // UI references.
    LinearLayout layout_Registrasi;
    LinearLayout layout_login;

    LoginButton btn_login_fb;

    TextView tv_fb;
    TextView tv_header;
    Button btn_masuk;

    TextView text1;
    TextView text2;

    CallbackManager callbackManager;
    InfiniteViewPagerActivity my_activity;

    private EditText et_email_login;
    private EditText et_password_login;

    private EditText et_email_registrasi;
    private EditText et_password_registrasi;

    private EditText et_nama_registrasi;
    private EditText et_confirm_password_registrasi;
    private EditText et_no_hp_registrasi;

    private TextView tv_lupa_pass;

    public static InfiniteViewPagerActivity activity;

    private View login_progress;

    public LoginActivity() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getBaseContext());
        setContentView(R.layout.activity_login);

        btn_masuk = (Button) findViewById(R.id.btn_masuk);
        tv_lupa_pass = (TextView) findViewById(R.id.tv_lupa_pass);
        tv_fb = (TextView) findViewById(R.id.tv_fb);
        tv_header = (TextView) findViewById(R.id.tv_header);
        text1 = (TextView) findViewById(R.id.text1);
        text2 = (TextView) findViewById(R.id.text2);

        dialog_transisi = Instant.dialog_transisi(this);

        layout_login = (LinearLayout) findViewById(R.id.layout_login);
        layout_Registrasi = (LinearLayout) findViewById(R.id.layout_Registrasi);

        if (getIntent().getExtras().getString("jenis_masuk").matches("login")) {
            layout_Registrasi.setVisibility(View.GONE);
            btn_masuk.setBackground(getResources().getDrawable(R.drawable.btn_login));
            btn_masuk.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    eventLogin(view);
                }
            });
            text2.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(LoginActivity.this, LoginActivity.class).putExtra("jenis_masuk", "registrasi"));
                    finish();
                }
            });
        } else {
            tv_header.setText("Buat Akun Baru Mu Sekarang!");
            tv_fb.setText("Daftar dengan Akun Facebook Kamu");
            text1.setText("Jika kamu sudah memiliki akun Pedago");
            text2.setText("Klik di sini untuk masuk");
            tv_lupa_pass.setVisibility(View.GONE);
            layout_login.setVisibility(View.GONE);

            btn_masuk.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    eventRegistrasi(view);
                }
            });

            text2.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(LoginActivity.this, LoginActivity.class).putExtra("jenis_masuk", "login"));
                    finish();
                }
            });
        }

        et_email_login = (EditText) findViewById(R.id.et_email_login);
        et_password_login = (EditText) findViewById(R.id.et_password_login);

        et_nama_registrasi = (EditText) findViewById(R.id.et_nama_registrasi);
        et_no_hp_registrasi = (EditText) findViewById(R.id.et_no_hp_registrasi);
        et_email_registrasi = (EditText) findViewById(R.id.et_email_registrasi);
        et_password_registrasi = (EditText) findViewById(R.id.et_password_registrasi);
        et_confirm_password_registrasi = (EditText) findViewById(R.id.et_confirm_pass_registrasi);

        et_password_login.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
//                eventLogin();
//                if (id == R.id.login || id == EditorInfo.IME_NULL) {
//                    return true;
//                }
                return false;
            }
        });

        login_progress = findViewById(R.id.login_progress);

        callbackManager = CallbackManager.Factory.create();

        btn_login_fb = (LoginButton) findViewById(R.id.btn_login_fb);

//        btn_login_fb.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
//            @Override
//            public void onSuccess(LoginResult loginResult) {
//                AccessToken accesstoken = loginResult.getAccessToken();
//                Profile profile = Profile.getCurrentProfile();
//                if(profile!=null)
//                Toast.makeText(LoginActivity.this, "nama "+profile.getName(), Toast.LENGTH_SHORT).show();
//            }
//            @Override
//            public void onCancel() { }
//
//            @Override
//            public void onError(FacebookException error) { }
//        });

//        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("user_photos", "email", "public_profile", "user_posts", "AccessToken"));
//        LoginManager.getInstance().logInWithPublishPermissions(this, Arrays.asList("publish_actions"));
//        LoginManager.getInstance().registerCallback(callbackManager,
//                new FacebookCallback<LoginResult>() {
//                    @Override
//                    public void onSuccess(LoginResult loginResult) {
//                        // App code
//                        FbGraphRequest(loginResult.getAccessToken());
//                    }
//
//                    @Override
//                    public void onCancel() {
//                        // App code
//                    }
//
//                    @Override
//                    public void onError(FacebookException exception) {
//                        // App code
//                    }
//                });

// cara ke 3
//        btn_login_fb.setReadPermissions(Arrays.asList("public_profile", "email", "user_friends"));
        btn_login_fb.setReadPermissions(Arrays.asList("profile_photos", "email", "user_birthday", "public_profile", "AccessToken"));
        btn_login_fb.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                FbGraphRequest(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });

        tv_fb.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                if (AccessToken.getCurrentAccessToken() != null) {
                    LoginManager.getInstance().logOut();
                } else {
//                            Arrays.asList("public_profile", "email", "user_friends")
                    LoginManager.getInstance().logInWithReadPermissions(LoginActivity.this,
                            Arrays.asList("user_photos", "email", "user_birthday", "public_profile", "email", "user_friends")
                    );
                }
            }
        });

        AccessTokenTracker accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {

                if (currentAccessToken == null) {
                    //Toast.makeText(getApplicationContext(), "FB User log out", Toast.LENGTH_SHORT).show();
                } else {
                    //Toast.makeText(getApplicationContext(), "FB User log in", Toast.LENGTH_SHORT).show();
                }
            }
        };

        if (AccessToken.getCurrentAccessToken() != null) {
            FbGraphRequest(AccessToken.getCurrentAccessToken());
        }

        //2
        btn_login_fb.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                String accessToken = loginResult.getAccessToken().getToken();
                Log.i("ic_facebook accessToken", accessToken);

                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {

                        Log.i("Login ic_facebook", response.toString());

                        try {

                            String id = object.getString("id");
                            JsonObject json = new JsonObject();
                            json.addProperty("username", object.getString("email"));
                            json.addProperty("password", "");

                            try {

                                URL profile_pic = new URL("http://graph.facebook.com/" + id + "/picture?type=large");
                                Log.i("profile_pic", profile_pic + "");

                            } catch (MalformedURLException e) {
                                e.printStackTrace();
                            }

                            id = " " + object.getString("name");
                            id += " " + object.getString("email");
                            id += " " + object.getString("gender");

                            nama = object.getString("name");
                            email = object.getString("email");
                            // id += " " + object.getString("birthday");
                            // ngambil datanya di sini

//                            Toast.makeText(LoginActivity.this, " " + id, Toast.LENGTH_SHORT).show();
//                            Toast.makeText(LoginActivity.this, "setelah login dari ic_facebook", Toast.LENGTH_SHORT).show();

                            dialog_transisi.show();

                            if (getIntent().getExtras().getString("jenis_masuk").matches("login")) {
                                Instant.show_dialog_n_save_data_login(LoginActivity.this, email, "password", "login", dialog_transisi, HomeScreenActivity.class);
                            } else {
                                Instant.show_dialog_n_save_data_login(LoginActivity.this, email, "password", "login", dialog_transisi, LengkapiProfilActivity.class);
                            }


                            try {
                                //"api.pedago.id/api-v2/login" "api.pedago.id/api-v2/login-sosmed"
                                Ion.with(LoginActivity.this)
                                        .load("http://api.pedago.id/api-v2/login-sosmed")//192.168.20.20
                                        .setHeader("request-Key", "" + Instant.getToken(LoginActivity.this, imei))
                                        .setHeader("Content-Type", "application/x-www-form-urlencoded")
                                        .setJsonObjectBody(json).asJsonObject()
                                        .setCallback(new FutureCallback<JsonObject>() {

                                            @Override
                                            public void onCompleted(Exception e, JsonObject result) {

                                                Log.d("even API login", result.toString());

                                                if (e != null) {

                                                    e.getStackTrace();
                                                    Log.d("ERROR", e.toString());

                                                } else if (result != null) {
                                                    Log.d("even result", "" + result.toString());

                                                    DatabaseHelper db = new DatabaseHelper(getApplicationContext());
                                                    db.InsertLogin(new String[]{"" + nama, "" + email});

                                                    Log.d("isi db ", "" + db.getAllDataLogin()[0]);
                                                    Log.d("data login", "" + nama + " " + email + " ");

                                                    startActivity(new Intent(LoginActivity.this, HomeScreenActivity.class));

                                                    if (my_activity != null) {
                                                        my_activity.finish();
                                                    }

                                                    finish();
                                                }
                                            }
                                        }).get();

                                Thread.sleep(2000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            } catch (NullPointerException ie) {
                                ie.printStackTrace();
                            } catch (ExecutionException ex) {
                                ex.printStackTrace();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,gender,birthday");
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException exception) {

                Log.v("LoginActivity", exception.getCause().toString());
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int responseCode, Intent data) {
        super.onActivityResult(requestCode, responseCode, data);
        if (requestCode == 9001) {

        }
        callbackManager.onActivityResult(requestCode, responseCode, data);

        if (responseCode == 2) {
//            setResult(2);
            LoginActivity.this.finish();
        }
    }

    public void FbGraphRequest(AccessToken token) {

//        GraphRequest request = GraphRequest.newMeRequest(
//                AccessToken.getCurrentAccessToken(),new GraphRequest.GraphJSONObjectCallback() {
//                    @Override
//                    public void onCompleted(JSONObject object,GraphResponse response) {
//                        try {
//                            String  email=object.getString("email");
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                });
//        request.executeAsync();

        GraphRequest request = GraphRequest.newMeRequest(token, new GraphRequest.GraphJSONObjectCallback() {

            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {

                try {

                    JSONObject json = object;
                    Log.d("jsonq", " " + object.toString());
                    String id = json.getString("id");
                    String email = json.getString("email");
                    String first_name = json.getString("first_name");
                    String last_name = json.getString("last_name");
                    String my_url = "";
//                    String my_url = json.getString("picture");

                    JSONObject data = response.getJSONObject();

                    if (data.has("picture")) {
                        my_url = data.getJSONObject("picture").getJSONObject("data").getString("url");
                    }

                    Log.d("akun ic_facebook", " facebook " + my_url + " " + id + " " + email + " " + first_name + " " + last_name);
//                    Toast.makeText(getApplicationContext(), " facebook " + email + " " + id + " " + first_name + " " + last_name, Toast.LENGTH_SHORT).show();

// userLoginTask = new UserLoginTask();
// userLoginTask.execute(new String[]{et_email_login.getText().toString(), "", "api.pedago.id/api-v2/login-sosmed", aa});//"Master Java 89"

                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (NullPointerException e2) {
                }

                Log.d("berhasil", response.toString());
            }
        });

        Bundle parameters = new Bundle();
        parameters.putString("fields", "id, email, last_name, first_name, picture.type(large), updated_time");
        request.setParameters(parameters);
        request.executeAsync();
    }

//    Step 5: Set your custom button click to FacebookLogin button click.

    public void onClick(View v) {

        Toast.makeText(LoginActivity.this, "out", Toast.LENGTH_SHORT).show();
        btn_login_fb.performClick();

        if (v.getId() == R.id.tv_fb) {
            Toast.makeText(LoginActivity.this, "in", Toast.LENGTH_SHORT).show();
        }

    }

    private boolean mayRequestContacts() {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (checkSelfPermission(READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        if (shouldShowRequestPermissionRationale(READ_CONTACTS)) {
            Snackbar.make(et_email_login, R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE)
                    .setAction(android.R.string.ok, new OnClickListener() {
                        @Override
                        @TargetApi(Build.VERSION_CODES.M)
                        public void onClick(View v) {
                            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
                        }
                    });
        } else {
            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
        }
        return false;
    }

    private void eventLogin(View view) {

        if (et_email_login.getText().toString().isEmpty()) {
            Toast.makeText(view.getContext(), R.string.alert_email1, Toast.LENGTH_SHORT).show();
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(et_email_login.getText().toString()).matches()) {
            Toast.makeText(view.getContext(), R.string.alert_email2, Toast.LENGTH_SHORT).show();
        }

        if (et_password_login.getText().toString().isEmpty()) {
            Toast.makeText(view.getContext(), R.string.alert_password, Toast.LENGTH_SHORT).show();
        }

        if (!Instant.cekKoneksi(view.getContext())) {
            Instant.noInternetSnackbar(view.getContext()).show();
        }

        if (android.util.Patterns.EMAIL_ADDRESS.matcher(et_email_login.getText().toString()).matches()
                && !et_password_login.getText().toString().isEmpty()) {

            if (!Instant.cekKoneksi(view.getContext())) {
                Instant.noInternetSnackbar(view.getContext()).show();
            } else {
                dialog_transisi.show();
                Instant.show_dialog_n_save_data_login(LoginActivity.this, et_email_login.getText().toString(), et_password_login.getText().toString(), "login", dialog_transisi, HomeScreenActivity.class);
            }
        }

//            if (!et_email_login.getText().toString().isEmpty() && !et_password_login.getText().toString().isEmpty())
//            userLoginTask = new UserLoginTask();
//            userLoginTask.execute(new String[]{et_email_login.getText().toString(), et_password_login.getText().toString(), "http://api.pedago.id/api-v2/login", aa});//"Master Java 89"

    }

    private void eventRegistrasi(View view) {

        if (et_nama_registrasi.getText().toString().isEmpty()) {
            Toast.makeText(this, R.string.alert_name1, Toast.LENGTH_SHORT).show();
        } else if (!Pattern.compile("^[a-zA-Z\\s]*$").matcher(et_nama_registrasi.getText().toString()).matches()) {
            Toast.makeText(this, R.string.alert_name2, Toast.LENGTH_SHORT).show();
        }

        if (et_email_registrasi.getText().toString().isEmpty()) {
            Toast.makeText(LoginActivity.this, R.string.alert_email1, Toast.LENGTH_SHORT).show();
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(et_email_registrasi.getText().toString()).matches()) {
            Toast.makeText(LoginActivity.this, R.string.alert_email2, Toast.LENGTH_SHORT).show();
        }

        if (et_password_registrasi.getText().toString().isEmpty()) {
            Toast.makeText(this, R.string.alert_password, Toast.LENGTH_SHORT).show();
        }

        if (et_confirm_password_registrasi.getText().toString().isEmpty()) {
            Toast.makeText(this, R.string.alert_confirm_password_registrasi, Toast.LENGTH_SHORT).show();
        } else if (!et_password_registrasi.getText().toString().matches(et_confirm_password_registrasi.getText().toString())) {
            Toast.makeText(this, R.string.alert_confirm_password_registrasi2, Toast.LENGTH_SHORT).show();
        }

        if (!Instant.cekKoneksi(view.getContext())) {
            Instant.noInternetSnackbar(view.getContext()).show();
        }

        if (!et_nama_registrasi.getText().toString().isEmpty() && Pattern.compile("^[a-zA-Z\\s]*$").matcher(et_nama_registrasi.getText().toString()).matches()
                && !et_email_registrasi.getText().toString().isEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(et_email_registrasi.getText().toString()).matches()
                && !et_password_registrasi.getText().toString().isEmpty() && et_password_registrasi.getText().toString().matches(et_confirm_password_registrasi.getText().toString())
                ) {

            if (!Instant.cekKoneksi(view.getContext())) {

                Instant.noInternetSnackbar(view.getContext()).show();
            } else{
                dialog_transisi.show();
                Instant.show_dialog_n_save_data_login(LoginActivity.this, et_email_registrasi.getText().toString(), "password", "registrasi", dialog_transisi, LengkapiProfilActivity.class);
            }
        }

    }

    @Override
    public void onBackPressed() {
        finish();
    }

    //Represents an asynchronous login/registration task used to authenticate the user.
    class UserLoginTask extends AsyncTask<String, String, Boolean> {

        String nama = "";
        String pass = "";
        String url = "";

        UserLoginTask() {

        }

        @Override
        protected Boolean doInBackground(String... params) {

            Log.d("isi 1", " " + params[0] + " " + params[1]);
            Log.d("isi 2", " " + params[2] + " " + params[3]);
            publishProgress("" + 111);
            JsonObject json = new JsonObject();
            json.addProperty("username", params[0]);
            json.addProperty("password", params[1]);

            try {
                //"api.pedago.id/api-v2/login" "api.pedago.id/api-v2/login-sosmed"
                Ion.with(LoginActivity.this)
                        .load(params[2])//192.168.20.20
                        .setHeader("request-Key", "" + Instant.getToken(LoginActivity.this, params[3]))
                        .setHeader("Content-Type", "application/x-www-form-urlencoded")
                        .setJsonObjectBody(json)
                        .asJsonObject()
                        .setCallback(new FutureCallback<JsonObject>() {

                            @Override
                            public void onCompleted(Exception e, JsonObject result) {

//                                Log.d("even API login", result.toString());

                                if (e != null) {

                                    e.getStackTrace();
                                    Log.d("ERROR", e.toString());

                                } else if (result != null) {
                                    Log.d("even result", "" + nama + " " + pass);
//                                    DatabaseHelper db = new DatabaseHelper(LoginActivity.this);
//                                    db.InsertLogin(new String[]{"" + nama, "" + pass});
                                    Log.d("data login", "" + nama + " " + pass + " " + url);
                                    startActivity(new Intent(LoginActivity.this, HomeScreenActivity.class));
                                    finish();
                                }
                            }
                        }).get();

                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return false;
            } catch (NullPointerException ie) {
            } catch (ExecutionException ex) {
            }

            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {

            if (!success) {
                et_password_login.setError(getString(R.string.error_incorrect_password));
                et_password_login.requestFocus();
            } else {
            }
        }

        @Override
        protected void onCancelled() {

        }
    }

    /**
     * Callback received when a permissions request has been completed.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case 999:
                if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
                    imei = telephonyManager.getDeviceId();
                    Log.d("imei di onreq", " " + imei);
                }
                break;

            default:
                break;
        }
    }
}