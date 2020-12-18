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
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.DummyNeighbourGenerator;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FavoriFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FavoriFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private NeighbourApiService mApiService;
    private RecyclerView mRecyclerView;
    private List<Neighbour> mNeighbours;

    public FavoriFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FavoriFragment.
     */

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
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_favori, container, false);

        initlist();

        mRecyclerView = root.findViewById(R.id.favorite_recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(new MyNeighbourRecyclerViewAdapter(getContext(), mNeighbours));

        return root;
    }

    public void initlist() {
        mNeighbours = mApiService.getFavNeighbours();
    }

    @Override
    public void onResume() {
        super.onResume();
        // actualizer les donné
        // refresh()
        // adapter.notifyDataChanged()
        Toast.makeText(getContext(), "RESUME", Toast.LENGTH_SHORT).show();
    }
}