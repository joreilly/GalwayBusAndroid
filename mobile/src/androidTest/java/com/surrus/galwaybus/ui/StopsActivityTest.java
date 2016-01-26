package com.surrus.galwaybus.ui;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;
import com.surrus.galwaybus.R;
import com.surrus.galwaybus.model.GetStopsResponse;
import com.surrus.galwaybus.service.GalwayBusService;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;

import javax.inject.Inject;

import retrofit.Callback;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class StopsActivityTest {

    private Context targetContext;
    @Inject
    GalwayBusService weatherApiClient;


    @Captor
    ArgumentCaptor<Callback<GetStopsResponse>> e;

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

    @Before
    public void registerIntentServiceIdlingResource() {
        MockWebServer server = new MockWebServer();
        server.enqueue(new MockResponse().setBody("Hello World Response"));

       /* RestAdapter rest = mock(RestAdapter.class);
        RestAdapter.Builder builder =  mock(RestAdapter.Builder .class);
        GalwayBusService galwayBusService = mock(GalwayBusService.class);
        GalwayBusRestInterface galwayBusRestInterface = mock(GalwayBusRestInterface.class);

        doReturn(builder).when(builder).setEndpoint(anyString());
        doReturn(rest).when(builder).build();
        doReturn(galwayBusRestInterface).when(rest).create(GalwayBusRestInterface.class);*/


    }

        @After
        public void unregisterIntentServiceIdlingResource () {
            //    Espresso.unregisterIdlingResources(idlingResource);
        }


        @Test
        public void testSwipeViewPager () {
            onView(withId(R.id.pager))
                    .perform(swipeLeft());
        }

        @Test
        public void testClickOnRecyclerViewLaunchActivity() {
            Log.e("inaki", "ksksksksksksk1");
       //     galwayBusRestInterface.getStops(anyInt(), e.capture());

            //selectionArgsArgumentCaptor.getValue().success(an)

            //       .getStops(anyInt()))

       //     when(galwayBusRestInterface//).thenReturn(e);


            //     Espresso.registerIdlingResources(new BetterIdlingResource());
            // ArgumentCaptor<String> jsonCaptor = ArgumentCaptor.forClass(String.class);

            //  TestSubscriber<User> testSubscriber = new TestSubscriber<>();
            //  galwayBusService.getStops(404).subscribe(testSubscriber);
            //  testSubscriber.assertNoErrors();
            //  testSubscriber.assertReceivedOnNext(Arrays.asList(user1, user2))
//        onData(allOf(withId(R.id.firstLine))).
//                inAdapterView(allOf(withId(R.id.listView), FirstViewMatcher.firstView())).atPosition(0)
            //               .perform(click());
//        intended(hasComponent(StopsMapActivity.class.getName()));
        }

    }
