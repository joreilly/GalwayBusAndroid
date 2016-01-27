package com.surrus.galwaybus;

import android.view.View;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;

public class FirstViewMatcher extends BaseMatcher<View> {


    public static boolean matchedBefore = false;

    public FirstViewMatcher() {
        matchedBefore = false;
    }

    @Override
    public boolean matches(Object o) {
        if (matchedBefore) {
            return false;
        } else {
            matchedBefore = true;
            return true;
        }
    }

    @Factory
    public static <T> Matcher<View> firstView() {
        return new FirstViewMatcher();
    }

    @Override
    public void describeTo(org.hamcrest.Description description) {
        description.appendText(" is the first view that comes");

    }
}