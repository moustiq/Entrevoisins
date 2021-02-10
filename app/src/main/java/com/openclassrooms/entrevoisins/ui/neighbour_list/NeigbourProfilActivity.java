package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import java.util.List;

import static android.widget.Toast.LENGTH_SHORT;

public class NeigbourProfilActivity extends AppCompatActivity {


    private NeighbourApiService mApiService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neigbour_profil);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mApiService = DI.getNeighbourApiService();

        FloatingActionButton favAdd = findViewById(R.id.btn_addFavori);

        Intent intent = getIntent();
        Bundle extra = intent.getExtras();

        int voisinID = intent.getIntExtra("ID", 0);
        int voisinHashcode = intent.getIntExtra("HASHCODE", 0);
        String voisinAvatar = intent.getStringExtra("avatar");
        String voisinName = intent.getStringExtra("nomUser");
        String voisinAddrs = intent.getStringExtra("addrsUser");
        String voisinTel = intent.getStringExtra("telUser");
        String voisinAppr = intent.getStringExtra("apprUser");



        if (intent != null) {
            
            if (extra.containsKey("avatar")) {
                Glide.with(this)
                        .load(voisinAvatar)
                        .into((ImageView) findViewById(R.id.AvatarUser));
            }
            if (intent.hasExtra("nomUser")) {
                TextView nameUser = (TextView) findViewById(R.id.NameUsers);
                nameUser.setText(voisinName);
            }
            if (intent.hasExtra("addrsUser")) {
                TextView addsUser = (TextView) findViewById(R.id.addreseUsers);
                addsUser.setText(voisinAddrs);
            }
            if (intent.hasExtra("telUser")) {
                TextView telUsr = (TextView) findViewById(R.id.tellUsers);
                telUsr.setText(voisinTel);
            }
            if (intent.hasExtra("apprUser")) {
                TextView apprsUsr = (TextView) findViewById(R.id.appropoUsers);
                apprsUsr.setText(voisinAppr);
            }

        }


        favAdd.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               List<Neighbour> listNeighbours = mApiService.getFavNeighbours();

               Neighbour Fneighbour = new Neighbour(voisinID,voisinName,voisinAvatar,voisinAddrs,voisinTel,voisinAppr);

               boolean id = true;

               for (Neighbour n: listNeighbours) {

                   if (n.hashCode() == voisinHashcode) {
                       id = false;
                   }
               }
               if (id) {
                       Toast.makeText(NeigbourProfilActivity.this, "favoris ajouté", LENGTH_SHORT).show();
                       mApiService.addFavNeighbours(Fneighbour);
               } else {
                       Toast.makeText(NeigbourProfilActivity.this, "favoris exist", LENGTH_SHORT).show();
               }

           }
        });
        
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home : {
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }


}