package it.fmgroup.pronostigram;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.intent.Intents;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class RegistrationActivityInstrumentedTest {

    @Rule public ActivityTestRule<RegistrationActivity> mActivityRule = new ActivityTestRule<RegistrationActivity>(RegistrationActivity.class);


    @Test
    public void testLayoutRegistrazione() throws Exception {
        onView(withId(R.id.textview_registrazione)).check(matches(isDisplayed()));
        onView(withId(R.id.text_nome)).check(matches(isDisplayed()));
        onView(withId(R.id.text_cognome)).check(matches(isDisplayed()));
        onView(withId(R.id.text_email)).check(matches(isDisplayed()));
        onView(withId(R.id.text_email_confirm)).check(matches(isDisplayed()));
        onView(withId(R.id.text_password)).check(matches(isDisplayed()));
        onView(withId(R.id.text_password_confirm)).check(matches(isDisplayed()));
        onView(withId(R.id.button_reg_confirm)).check(matches(isDisplayed()));
    }

    @Test
    public void testEmptyForm () throws Exception {
        onView(withId(R.id.button_reg_confirm)).check(matches(isDisplayed()));
        onView(withId(R.id.button_reg_confirm)).perform(click());
        onView(withId(R.id.registration_form)).check(matches(isDisplayed()));
    }

    @Test
    public void testProgress() throws Exception {
        onView(withId(R.id.registration_progress)).check(matches(not(isDisplayed())));
    }


    @Test
    public void testCompilazione() throws Exception{

        mActivityRule.getActivity().mProgressView = new util.FakeProgressBar(mActivityRule.getActivity());
        mActivityRule.getActivity().mProgressView.setId(R.id.registration_progress);

        ViewInteraction editTextNome = onView(allOf(withId(R.id.text_nome)));
        editTextNome.perform(replaceText("Antonio"));
        editTextNome.perform(closeSoftKeyboard());

        ViewInteraction editTextCognome = onView(allOf(withId(R.id.text_cognome)));
        editTextCognome.perform(replaceText("Ciao"));
        editTextCognome.perform(closeSoftKeyboard());

        ViewInteraction editTextEmail = onView(allOf(withId(R.id.text_email)));
        editTextEmail.perform(replaceText("antonio@ciao.it"));
        editTextEmail.perform(closeSoftKeyboard());

        ViewInteraction editTextEmailConfirm = onView(allOf(withId(R.id.text_email_confirm)));
        editTextEmailConfirm.perform(replaceText("antonio@ciao.it"));
        editTextEmailConfirm.perform(closeSoftKeyboard());

        ViewInteraction editTextPassword = onView(allOf(withId(R.id.text_password)));
        editTextPassword.perform(replaceText("ciaociaociao"));
        editTextPassword.perform(closeSoftKeyboard());

        ViewInteraction editTextPasswordConfirm = onView(allOf(withId(R.id.text_password_confirm)));
        editTextPasswordConfirm.perform(replaceText("ciaociaociao"));
        editTextPasswordConfirm.perform(closeSoftKeyboard());

        onView(withId(R.id.button_reg_confirm)).check(matches(isDisplayed()));
        onView(withId(R.id.button_reg_confirm)).perform(click());

        onView(withId(R.id.registration_form)).check(matches(not(isDisplayed())));

    }
}
