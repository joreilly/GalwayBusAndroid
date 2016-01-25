package com.surrus.galwaybus.ui;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.surrus.galwaybus.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.core.AllOf.allOf;

@RunWith(AndroidJUnit4.class)
public class StopsActivityTest {

    private Context targetContext;

    @Rule
    public IntentsTestRule<StopsActivity> mActivityRule = new IntentsTestRule<StopsActivity>(StopsActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            targetContext = InstrumentationRegistry.getInstrumentation()
                    .getTargetContext();
            Intent result = new Intent(targetContext, StopsActivity.class);
            result.putExtra(StopsActivity.ROUTE_ID_ARG, 404);
            return result;
        }
    };

    @Test
    public void testSwipeViewPager() {
        onView(withId(R.id.pager))
                .perform(swipeLeft());
    }

    @Test
    public void testClickOnRecyclerViewLaunchActivity() {
        onData(allOf(withId(R.id.firstLine))).
                inAdapterView(allOf(withId(R.id.listView), isDisplayed())).atPosition(0)
                .perform(click());
        intended(hasComponent(StopsMapActivity.class.getName()));
    }

}
