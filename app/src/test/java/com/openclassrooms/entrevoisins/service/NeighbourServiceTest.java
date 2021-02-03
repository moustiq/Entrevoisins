package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static com.openclassrooms.entrevoisins.service.DummyNeighbourGenerator.NEIGHBOUR_FAVORI;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Unit test on Neighbour service
 */
@RunWith(JUnit4.class)
public class NeighbourServiceTest {

    private NeighbourApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void getNeighboursWithSuccess() {
        List<Neighbour> neighbours = service.getNeighbours();
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }

    @Test
    public void deleteNeighbourWithSuccess() {
        Neighbour neighbourToDelete = service.getNeighbours().get(0);
        service.deleteNeighbour(neighbourToDelete);
        assertFalse(service.getNeighbours().contains(neighbourToDelete));
    }

    @Test
    public void getFavUserList() {

        List<Neighbour> favUserActual = service.getFavNeighbours();
        List<Neighbour> favUserExpected = NEIGHBOUR_FAVORI;

        assertThat(favUserActual,containsInAnyOrder(favUserExpected.toArray()));

    }


    @Test
    public void addUserFav() {

        Neighbour user = service.getNeighbours().get(0);
        service.addFavNeighbours(user);
        assertTrue(service.getFavNeighbours().contains(user));

    }

    @Test
    public void deleteFavUser() {

        Neighbour favUser = service.getFavNeighbours().get(0);
        service.deleteFavNeighbour(favUser);
        assertFalse(service.getFavNeighbours().contains(favUser));

    }
}
