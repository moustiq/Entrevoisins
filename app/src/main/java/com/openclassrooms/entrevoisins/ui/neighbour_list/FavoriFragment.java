package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.DummyNeighbourGenerator;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;


public class FavoriFragment extends Fragment {


    private NeighbourApiService mApiService;
    private RecyclerView mRecyclerView;
    private List<Neighbour> mNeighbours;

    public FavoriFragment() {
        // Required empty public constructor
    }

    // peut etre simplifie par
    // public static Fragment newInstance() {
    //    return new FavoriFragment();
    // }
    public static Fragment newInstance() {
        FavoriFragment fragment = new FavoriFragment();
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApiService = DI.getNeighbourApiService();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root =  inflater.inflate(R.layout.fragment_favori, container, false);
        mRecyclerView = root.findViewById(R.id.favorite_recycler);

        refresh();

        return root;
    }


    private void refresh() {
        mNeighbours = mApiService.getFavNeighbours();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(new MyNeighbourRecyclerViewAdapter(getContext(), mNeighbours));
    }

    @Override
    public void onResume() {
        super.onResume();
        refresh();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onDeleteNeighbour(DeleteNeighbourEvent event) {
        // Ton bouton delete des favouris ne marche pas vraiment car il delete egalement
        // le neighbour de la liste des neighbours
        // Comme les 2 fragments existe en meme temps le fragment `NeighbourFragment` recoit egalement
        // l'event `DeleteNeighbourEvent` et execute son action: delete le neighbour de la liste principale
        // il faut pet etre creer 2 RecyclerViewAdapter afin que la recyclerview des favouris et son propre adapter
        // tres similaire a celui de NeighbourFragment mais lorsque on clique sur le bouton delete un autre type
        // d'event est emit, example: Un event DeleteFavourisEvent
        mApiService.deleteFavNeighbour(event.neighbour);
        refresh();
    }
}