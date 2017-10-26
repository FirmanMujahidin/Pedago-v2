package com.pedago2.ChatTutor;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.pedago2.Base.BasesActivity;
import com.pedago2.R;

import butterknife.BindView;

public class ChatTutorActivity extends BasesActivity {

    @BindView(R.id.btn_gallery)
    Button btnGallery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind(R.layout.activity_chat_tutor);
        initToolbar("Chat Tutor", true);

        selectGallery();
    }

    public void selectGallery(){
        btnGallery.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("content://media/internal/images/media"));
            startActivity(intent);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.token_info, menu);
        MenuItem infoToken = menu.findItem(R.id.chat_tutor_token);


        return super.onCreateOptionsMenu(menu);
    }

}
