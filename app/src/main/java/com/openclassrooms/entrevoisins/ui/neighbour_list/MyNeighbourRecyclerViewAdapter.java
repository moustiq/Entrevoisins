package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.widget.Toast.LENGTH_SHORT;

public class MyNeighbourRecyclerViewAdapter extends RecyclerView.Adapter<MyNeighbourRecyclerViewAdapter.ViewHolder> {

    private final List<Neighbour> mNeighbours;
    private Context context;

    /* creer les variable final */

    static final String ID = "ID";
    static final String AVATAR = "avatar";
    static final String NOM_USER  = "nomUser";
    static final String ADDRS_USER = "addrsUser";
    static final String TEL_USER = "telUser";
    static final String APPR_USER = "apprUser";

    public MyNeighbourRecyclerViewAdapter(Context context, List<Neighbour> items) {
        mNeighbours = items;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_neighbour, parent, false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Neighbour neighbour = mNeighbours.get(position);
        holder.mNeighbourName.setText(neighbour.getName());
        Glide.with(holder.mNeighbourAvatar.getContext())
                .load(neighbour.getAvatarUrl())
                .apply(RequestOptions.circleCropTransform())
                .into(holder.mNeighbourAvatar);

        holder.mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new DeleteNeighbourEvent(neighbour));
            }
        });



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Tout cela devrait etre remplace par:
                // `EventBus.getDefault().post(new OpenProfileEvent(neighbour))`
                // et ajoute une methode  `public void onClickNeighbour(OpenProfileEvent event)`
                // qui execute les actions ci dessous a la classe `NeighbourFragment`
                // inspire toi de `holder.mDeleteButton.setOnClickListener` au dessus
                // tu devras creer une class `OpenProfileEvent` similaire a `DeleteNeighbourEvent`
                Intent intent = new Intent(context, NeigbourProfilActivity.class);

                intent.putExtra(ID ,neighbour.getId());
                intent.putExtra(AVATAR, neighbour.getAvatarUrl());
                intent.putExtra(NOM_USER, neighbour.getName());
                intent.putExtra(ADDRS_USER , neighbour.getAddress());
                intent.putExtra(TEL_USER, neighbour.getPhoneNumber());
                intent.putExtra(APPR_USER, neighbour.getAboutMe());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mNeighbours.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_list_avatar)
        public ImageView mNeighbourAvatar;
        @BindView(R.id.item_list_name)
        public TextView mNeighbourName;
        @BindView(R.id.item_list_delete_button)
        public ImageButton mDeleteButton;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }




    }


}
