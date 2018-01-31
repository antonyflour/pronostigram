package it.fmgroup.pronostigram;

import android.graphics.drawable.Drawable;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.intent.Intents;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.ProgressBar;

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
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.allOf;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class LoginActivityInstrumentedTest {

    @Rule public ActivityTestRule<LoginActivity> mActivityRule = new ActivityTestRule<LoginActivity>(LoginActivity.class);


    @Test
    public void testLayoutLogin() throws Exception {
        onView(withId(R.id.textview_login)).check(matches(isDisplayed()));
        onView(withId(R.id.email)).check(matches(isDisplayed()));
        onView(withId(R.id.password)).check(matches(isDisplayed()));
        onView(withId(R.id.email_sign_in_button)).check(matches(isDisplayed()));
    }

    @Test
    public void testEmptyForm() throws Exception {
        onView(withId(R.id.email_sign_in_button)).check(matches(isDisplayed()));
        onView(withId(R.id.email_sign_in_button)).perform(click());
        onView(withId(R.id.login_form)).check(matches(isDisplayed()));

    }

    @Test
    public void testProgress() throws Exception {
        onView(withId(R.id.login_progress)).check(matches(not(isDisplayed())));
    }


    @Test
    public void testCompilazione() throws Exception{

        ViewInteraction editTextEmail = onView(allOf(withId(R.id.email)));
        editTextEmail.perform(replaceText("antonio@ciao.it"));
        editTextEmail.perform(closeSoftKeyboard());

        ViewInteraction editTextPassword = onView(allOf(withId(R.id.password)));
        editTextPassword.perform(replaceText("ciaociaociao"));
        editTextPassword.perform(closeSoftKeyboard());

        onView(withId(R.id.email_sign_in_button)).check(matches(isDisplayed()));
        onView(withId(R.id.email_sign_in_button)).perform(click());

        Drawable notAnimatedDrawable = ContextCompat.getDrawable(mActivityRule.getActivity(), R.drawable.ic_plus);
        ((ProgressBar) mActivityRule.getActivity().findViewById(R.id.login_progress)).setIndeterminateDrawable(notAnimatedDrawable);

        //onView(withId(R.id.progress_bar)).check(matches(isDisplayed()));

        onView(withId(R.id.login_form)).check(matches(not(isDisplayed())));

    }
}
