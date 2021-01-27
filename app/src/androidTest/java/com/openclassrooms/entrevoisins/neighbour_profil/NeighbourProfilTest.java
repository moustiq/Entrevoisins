package com.openclassrooms.entrevoisins.neighbour_profil;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.Matchers.allOf;

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

    @Test
    public void listFavUser() {

        int nbUser = neighbourApiService.getNeighbours().size();
        int nbUserFav = neighbourApiService.getFavNeighbours().size();

        onView(allOf(withId(R.id.list_neighbours), withParent(withId(R.id.container)))).perform(actionOnItemAtPosition(0, click()));
        onView(withId(R.id.btn_addFavori)).perform(click());
        nbUserFav = nbUserFav + 1;
        pressBack();

        onView(allOf(withId(R.id.list_neighbours), withParent(withId(R.id.container)))).perform(actionOnItemAtPosition(2, click()));
        onView(withId(R.id.btn_addFavori)).perform(click());
        nbUserFav = nbUserFav + 1;
        pressBack();

        onView(allOf(withId(R.id.list_neighbours), withParent(withId(R.id.container)))).perform(actionOnItemAtPosition(5, click()));
        onView(withId(R.id.btn_addFavori)).perform(click());
        nbUserFav = nbUserFav + 1;
        pressBack();


        onView((allOf(childAtPosition(childAtPosition(withId(R.id.tabs), 0), 1)))).perform(click());
        onView(withId(R.id.favorite_recycler)).check(withItemCount(nbUserFav));

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