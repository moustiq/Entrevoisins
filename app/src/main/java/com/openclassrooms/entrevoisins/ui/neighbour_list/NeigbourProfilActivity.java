package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.openclassrooms.entrevoisins.R;

public class NeigbourProfilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neigbour_profil);

        Intent intent = getIntent();

        if (intent != null) {
            String str = "";
            if (intent.hasExtra("nomUser")) {
                str = intent.getStringExtra("nomUser");
            }
            TextView nameUser = (TextView) findViewById(R.id.NameUsers);
            nameUser.setText(str);
        }

    }
}