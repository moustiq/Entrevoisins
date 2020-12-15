package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;

public class NeigbourProfilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neigbour_profil);

        Intent intent = getIntent();

        Bundle extra = intent.getExtras();
        if (intent != null) {

            String strName = "";
            String straddsUsr = "";
            String strtelUsr = "";
            String strapprsUsr = "";

            if (extra.containsKey("avatar")) {
                ImageView avt = (ImageView) findViewById(R.id.AvatarUser);
                Glide.with(this)
                        .load(intent.getStringExtra("avatar"))
                        .into((ImageView) findViewById(R.id.AvatarUser));
            }
            if (intent.hasExtra("nomUser")) {
                TextView nameUser = (TextView) findViewById(R.id.NameUsers);
                nameUser.setText(intent.getStringExtra("nomUser"));
            }
            if (intent.hasExtra("addrsUser")) {
                TextView addsUser = (TextView) findViewById(R.id.addreseUsers);
                addsUser.setText(intent.getStringExtra("addrsUser"));
            }
            if (intent.hasExtra("telUser")) {
                TextView telUsr = (TextView) findViewById(R.id.tellUsers);
                telUsr.setText(intent.getStringExtra("telUser"));
            }
            if (intent.hasExtra("apprUser")) {
                TextView apprsUsr = (TextView) findViewById(R.id.appropoUsers);
                apprsUsr.setText(intent.getStringExtra("apprUser"));
            }

        }

    }

}