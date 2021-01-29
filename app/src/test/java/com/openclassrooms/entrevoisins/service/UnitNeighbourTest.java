package com.openclassrooms.entrevoisins.service;

import android.content.Intent;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import com.openclassrooms.entrevoisins.ui.neighbour_list.NeigbourProfilActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static com.openclassrooms.entrevoisins.service.DummyNeighbourGenerator.*;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(JUnit4.class)
public class UnitNeighbourTest {

    private NeighbourApiService neighbourApiService;

    @Before
    public void setup() {
        neighbourApiService = DI.getNewInstanceApiService();
    }


    @Test
    public void getFavUserList() {

        List<Neighbour> favUserActual = neighbourApiService.getFavNeighbours();
        List<Neighbour> favUserExpected = Neighbour_FAVORI;

        assertThat(favUserActual,containsInAnyOrder(favUserExpected.toArray()));

    }


    @Test
    public void addUserFav() {

        Neighbour user = neighbourApiService.getNeighbours().get(0);
        neighbourApiService.addFavNeighbours(user);
        assertTrue(neighbourApiService.getFavNeighbours().contains(user));

    }

    @Test
    public void deleteFavUser() {

        Neighbour favUser = neighbourApiService.getFavNeighbours().get(0);
        neighbourApiService.deleteFavNeighbour(favUser);
        assertFalse(neighbourApiService.getFavNeighbours().contains(favUser));

    }

}
