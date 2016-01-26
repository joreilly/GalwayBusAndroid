package com.surrus.galwaybus;


import android.support.test.espresso.contrib.DrawerActions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Test
    public void testFabButtonIsClickable() {
        onView(withId(R.id.fab)).perform(click());
        onView(allOf(withId(android.support.design.R.id.snackbar_text), withText("Replace with your own action")))
                .check(matches(isDisplayed()));
    }

    @Test
    public void testOpenNavigationDrawer() {
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        onView(withText("Routes")).perform(click());
    }

    @Test
    public void testClickOneRouteDisplayResults() {
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        pressBack();
        onView(withId(R.id.list)).perform(RecyclerViewActions.actionOnItemAtPosition(2, click()));
    }


}
