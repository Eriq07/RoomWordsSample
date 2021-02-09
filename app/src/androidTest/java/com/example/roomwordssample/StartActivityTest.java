package com.example.roomwordssample;

import androidx.test.core.app.ActivityScenario;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

public class StartActivityTest {


   // public ActivityTestRule<StartActivity> mActivityTestRule = new ActivityTestRule(StartActivity.class);


    @Test
    public void test_isActivityInView() {
        ActivityScenario mActivity = ActivityScenario.launch(StartActivity.class);
        onView(withId(R.id.drawer_layout)).check(matches(isDisplayed()));
    }
}