package it.fmgroup.pronostigram;

import android.app.Activity;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.TextView;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.app.PendingIntent.getActivity;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.AllOf.allOf;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityInstrumentedTest {

    @Rule public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<MainActivity>(MainActivity.class);


    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("it.fmgroup.pronostigram", appContext.getPackageName());
    }


    @Test
    public void testTextViewWelcome() throws Exception {
        onView(withId(R.id.textview_welcome)).check(matches(isDisplayed()));
    }

    @Test
    public void testButtonRegistrati() throws Exception {
        onView(withId(R.id.button_registrati)).check(matches(isDisplayed()));
        onView(withId(R.id.button_registrati)).perform(click());
        onView(withId(R.id.textview_registrazione)).check(matches(isDisplayed()));

    }

    @Test
    public void testButtonEntra() throws Exception {
        onView(withId(R.id.button_entra)).check(matches(isDisplayed()));
        onView(withId(R.id.button_entra)).perform(click());
        onView(withId(R.id.textview_login)).check(matches(isDisplayed()));
    }

}
