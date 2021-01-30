package com.openclassrooms.entrevoisins.neighbour_profil;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import static com.openclassrooms.entrevoisins.service.DummyNeighbourGenerator.Neighbour_FAVORI;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class NeighbourProfilTest {

    NeighbourApiService neighbourApiService = DI.getNewInstanceApiService();

    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityTestRule = new ActivityTestRule<>(ListNeighbourActivity.class);

    @Test
    public void nameExist() {

        onView(allOf(withId(R.id.list_neighbours), withParent(withId(R.id.container)))).perform(actionOnItemAtPosition(0, click()));
        onView(withId(R.id.NameUsers)).check(matches(withText("Caroline")));

    }

    // Ce test devrait se trouver dans la class `NeighboursListTest`
    // tu ne test pas la bonne chose, l'ennoncé te demande
    // "test vérifiant que l’onglet Favoris n’affiche​ ​que les neighbours marqués comme favoris."
    // la tu verifie que le click sur favoris ajoute bien le neighbours aux favoris
    // c'est un bon test mais il faut l'ameliorer un peu (voir commentaire en dessous) et ajouter celui
    // qui est demandé.
    // Pour verifier que les favoris sont bien affiché tu devrais:
    // 1. Dans ton test unitaire grace a la methode `neighbourApiService.addFavNeighbours` ajoute des
    // neighbours que tu creer toi meme.
    // 2. Cliquer sur la tab favoris pour afficher les favouris
    // 3. verifie que les nom de tes neighbours est bien affiché.
    @Test
    public void listFavUser() {
        
        //int nbUserFav = neighbourApiService.getFavNeighbours().size();

        List<Neighbour> favUserActual = neighbourApiService.getFavNeighbours();
        List<Neighbour> favUserExpected = Neighbour_FAVORI;

        // Tout les `onView(allOf(withId(R.id.list_neighbours), withParent(withId(R.id.container))))`
        // peuvent etre remplacer par `onView(withId(R.id.list_neighbours))` pour plus de simplicite
        onView(allOf(withId(R.id.list_neighbours), withParent(withId(R.id.container)))).perform(actionOnItemAtPosition(0, click()));
        onView(withId(R.id.btn_addFavori)).perform(click());
        pressBack();

        onView(allOf(withId(R.id.list_neighbours), withParent(withId(R.id.container)))).perform(actionOnItemAtPosition(2, click()));
        onView(withId(R.id.btn_addFavori)).perform(click());
        pressBack();

        onView(allOf(withId(R.id.list_neighbours), withParent(withId(R.id.container)))).perform(actionOnItemAtPosition(5, click()));
        onView(withId(R.id.btn_addFavori)).perform(click());
        pressBack();


        onView((allOf(childAtPosition(childAtPosition(withId(R.id.tabs), 0), 1)))).perform(click());

        // tu ne verifie pas vraiment que les neighbours on bien ete ajouté.
        // tu peux a la place:
        // 1. avant de cliquer sur les neighbours, verifie que la taille de ta liste est egale a 1
        // 2. apres avoir cliqué sur les 3 neighbours verifie que la taille de ta list est bien egale a 4
        assertThat(favUserActual,containsInAnyOrder(favUserExpected.toArray()));

    }


    private static Matcher<View> childAtPosition(final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}