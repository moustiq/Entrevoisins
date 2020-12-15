package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.openclassrooms.entrevoisins.R;

public class NeigbourProfilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neigbour_profil);

        Intent intent = getIntent();

        if (intent != null) {

            String strAvatar = "";
            String strName = "";
            String straddsUsr = "";
            String strtelUsr = "";
            String strapprsUsr = "";

            if (intent.hasExtra("avatar")) {
                strAvatar = intent.getStringExtra("avatar");
            }
            if (intent.hasExtra("nomUser")) {
                strName = intent.getStringExtra("nomUser");
            }
            if (intent.hasExtra("addrsUser")) {
                straddsUsr = intent.getStringExtra("addrsUser");
            }
            if (intent.hasExtra("telUser")) {
                strtelUsr = intent.getStringExtra("telUser");
            }
            if (intent.hasExtra("apprUser")) {
                strapprsUsr = intent.getStringExtra("apprUser");
            }

            ImageView avatarUser = (ImageView) findViewById(R.id.AvatarUser);
            avatarUser.setImageURI(Uri.parse(strAvatar));

            TextView nameUser = (TextView) findViewById(R.id.NameUsers);
            nameUser.setText(strName);

            TextView addsUser = (TextView) findViewById(R.id.addreseUsers);
            addsUser.setText(straddsUsr);

            TextView telUsr = (TextView) findViewById(R.id.tellUsers);
            telUsr.setText(strtelUsr);

            TextView apprsUsr = (TextView) findViewById(R.id.appropoUsers);
            apprsUsr.setText(strapprsUsr);
        }

    }
}