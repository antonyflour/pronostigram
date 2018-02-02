package it.fmgroup.pronostigram;

import android.support.test.espresso.ViewInteraction;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.allOf;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class UseCaseGoToSettingsTest {

    @Rule public ActivityTestRule<LoginActivity> mActivityRule = new ActivityTestRule<LoginActivity>(LoginActivity.class);

    @Before
    public void testLoginCase() throws Exception {

        mActivityRule.getActivity().mProgressView = new util.FakeProgressBar(mActivityRule.getActivity());
        mActivityRule.getActivity().mProgressView.setId(R.id.login_progress);

        ViewInteraction editTextEmail = onView(allOf(withId(R.id.email)));
        editTextEmail.perform(replaceText("antonio@ciao.it"));
        editTextEmail.perform(closeSoftKeyboard());

        ViewInteraction editTextPassword = onView(allOf(withId(R.id.password)));
        editTextPassword.perform(replaceText("ciaociaociao"));
        editTextPassword.perform(closeSoftKeyboard());

        onView(withId(R.id.email_sign_in_button)).check(matches(isDisplayed()));
        onView(withId(R.id.email_sign_in_button)).perform(click());

        onView(withId(R.id.login_form)).check(matches(not(isDisplayed())));

        Thread.sleep(5000);
        onView(withId(R.id.list_view_feed)).check(matches(isDisplayed()));

        onView(withId(R.id.button_settings)).check(matches(isDisplayed()));
        onView(withId(R.id.button_settings)).perform(click());

        Thread.sleep(5000);

    }

    @Test
    public void testViewSettings() throws Exception {
        onView(allOf(withText("General"))).check(matches(isDisplayed()));
    }

}
