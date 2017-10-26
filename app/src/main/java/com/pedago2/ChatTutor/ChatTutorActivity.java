package com.pedago2.ChatTutor;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.pedago2.Base.BasesActivity;
import com.pedago2.R;
import com.pedago2.Util.Utility;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ChatTutorActivity extends BasesActivity {

    @BindView(R.id.iv_view_file)
    ImageView ivViewFile;
    @BindView(R.id.btn_gallery)
    Button btnGallery;
    @BindView(R.id.spinner_mapel)
    MaterialSpinner spinnerMapel;
    @BindView(R.id.btn_camera)
    Button btnCamera;
    @BindView(R.id.btn_next)
    Button btnNext;

    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    private boolean reault = Utility.checkPermission(ChatTutorActivity.this);
//    private String userChoosenTask;
//    private List<String> spinnerArray = new ArrayList<>();

    private static final String[] MATA_PELAJARAN = {
            "","Bahasa Inggris", "Bahasa Jepang", "Matematika", "Ilmu Pengetahuan Alam", "Biologi", "Ilmu Pengetahuan sosial", "logika Matematika", "Komputer Grafis", "Kecerdasan Tiruan",
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind(R.layout.activity_chat_tutor);
        initToolbar("Chat Tutor", true);

        btnGallery.setOnClickListener(view ->{
            if (reault)
            galleryIntent();
            else
                Toast.makeText(getApplicationContext(), "Error Gallery photo", Toast.LENGTH_SHORT).show();
        });
        btnCamera.setOnClickListener(view -> {
            if (reault)
            cameraIntent();
            else
                Toast.makeText(getApplicationContext(), "Error Open Camera", Toast.LENGTH_SHORT).show();
        });
//        spinnerMapel.setOnClickListener(View -> spinnerSelect());

        spinnerMapel.setItems(MATA_PELAJARAN);
    }

    private void spinnerSelect(){
//        spinnerArray.add("Matematika");
//        spinnerArray.add("Ilmu Pengetahuan Alam");
//        spinnerArray.add("Biologi");
//        spinnerArray.add("Ilmu Pengetahuan sosial");
//        spinnerArray.add("Komputer");
//        spinnerArray.add("logika");
//        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinnerArray);
//        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerMapel.setAdapter(dataAdapter);
    }


    private void galleryIntent()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, "Select File"),SELECT_FILE);
    }

    private void cameraIntent()
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE)
                onSelectFromGalleryResult(data);
            else if (requestCode == REQUEST_CAMERA)
                onCaptureImageResult(data);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.token_info, menu);
        MenuItem infoToken = menu.findItem(R.id.chat_tutor_token);

        return super.onCreateOptionsMenu(menu);
    }

    private void onSelectFromGalleryResult(Intent data) {

        Bitmap bm=null;
        if (data != null) {
            try {
                bm = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        ivViewFile.setImageBitmap(bm);
    }

    private void onCaptureImageResult(Intent data) {
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);

        File destination = new File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis() + ".jpg");

        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ivViewFile.setImageBitmap(thumbnail);
    }


}
