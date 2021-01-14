package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.app.ActionBar;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.DummyNeighbourApiService;
import com.openclassrooms.entrevoisins.service.DummyNeighbourGenerator;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.OnClick;

import static android.os.Build.ID;
import static android.widget.Toast.LENGTH_SHORT;
import static com.openclassrooms.entrevoisins.service.DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
import static java.security.AccessController.getContext;

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

               Neighbour Fneighbour = new Neighbour(voisinID,voisinName,voisinAvatar,voisinAddrs,voisinTel,voisinAppr);

               Toast.makeText(NeigbourProfilActivity.this, "click", LENGTH_SHORT).show();
               mApiService.addFavNeighbours(Fneighbour);
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