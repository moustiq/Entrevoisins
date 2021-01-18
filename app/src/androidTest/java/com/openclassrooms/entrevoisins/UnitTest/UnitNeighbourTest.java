package com.openclassrooms.entrevoisins.UnitTest;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

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

@RunWith(JUnit4.class)
public class UnitNeighbourTest {

    private NeighbourApiService neighbourApiService;

    @Before
    public void setup() {
        neighbourApiService = DI.getNewInstanceApiService();
    }

    @Test
    public void getUserList() {

        List<Neighbour> allUserActual = neighbourApiService.getNeighbours();
        List<Neighbour> allUserExpected = DUMMY_NEIGHBOURS;

        assertThat(allUserActual,containsInAnyOrder(allUserExpected.toArray()));

    }

    @Test
    public void getFavUserList() {

        List<Neighbour> favUserActual = neighbourApiService.getFavNeighbours();
        List<Neighbour> favUserExpected = Neighbour_FAVORI;

        assertThat(favUserActual,containsInAnyOrder(favUserExpected.toArray()));

    }

    @Test
    public void deleteUser() {

        Neighbour deleteUser = neighbourApiService.getNeighbours().get(0);
        neighbourApiService.deleteNeighbour(deleteUser);
        assertFalse(neighbourApiService.getNeighbours().contains(deleteUser));

    }

    @Test
    public void addUserFav() {

        Neighbour User = neighbourApiService.getNeighbours().get(0);
        neighbourApiService.addFavNeighbours(User);
        assertTrue(neighbourApiService.getFavNeighbours().contains(User));

    }


}
