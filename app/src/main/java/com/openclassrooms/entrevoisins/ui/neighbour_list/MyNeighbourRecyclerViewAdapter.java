package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.events.DeleteFavNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyNeighbourRecyclerViewAdapter extends RecyclerView.Adapter<MyNeighbourRecyclerViewAdapter.ViewHolder> {

    private final List<Neighbour> mNeighbours;
    private Context context;
    private boolean fav;

    /* creer les variable final */

    static final String ID = "ID";
    static final String HASHCODE = "HASHCODE";
    static final String AVATAR = "avatar";
    static final String NOM_USER  = "nomUser";
    static final String ADDRS_USER = "addrsUser";
    static final String TEL_USER = "telUser";
    static final String APPR_USER = "apprUser";

    public MyNeighbourRecyclerViewAdapter(Context context, List<Neighbour> items, boolean fav) {
        mNeighbours = items;
        this.context = context;
        this.fav = fav;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_neighbour, parent, false);

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
                if (fav) {
                    // mode fav retir fav
                    EventBus.getDefault().post(new DeleteFavNeighbourEvent(neighbour));
                } else {
                    EventBus.getDefault().post(new DeleteNeighbourEvent(neighbour));
                }

            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, NeigbourProfilActivity.class);

                intent.putExtra(ID ,neighbour.getId());
                intent.putExtra(HASHCODE,neighbour.hashCode());
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

    public static class ViewHolder extends RecyclerView.ViewHolder {
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
