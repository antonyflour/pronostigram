package it.fmgroup.pronostigram;

import android.support.test.espresso.ViewInteraction;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
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
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.hamcrest.core.StringContains.containsString;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class PronosticoActivityInstrumentationTest {

    @Rule public ActivityTestRule<LoginActivity> mActivityRule = new ActivityTestRule<>(LoginActivity.class);
    @Rule public ActivityTestRule<MatchActivity> mActivityRuleMain = new ActivityTestRule<>(MatchActivity.class);

    @Before
    //devo loggarmi e passare per la feed actviity
    public void testCompilazione() throws Exception{

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



        //onView(withId(R.id.progress_bar)).check(matches(isDisplayed()));

        onView(withId(R.id.login_form)).check(matches(not(isDisplayed())));

        Thread.sleep(5000);

        // A questo punto sto in FeedActivity. Cerco di premere il floatingbutton

        onView(withId(R.id.floating_action_button_listview)).check(matches(isDisplayed()));
        onView(withId(R.id.floating_action_button_listview)).perform(click());


        Thread.sleep(5000);

        // entro in ProonsticoActivity
        onData(allOf()).inAdapterView(withId(R.id.listViewMatches)).atPosition(0).perform(click());

    }


    @Test
    public void testListViewMatches() throws Exception {
        onView(withId(R.id.textView_scegli_pronostico)).check(matches(isDisplayed()));
    }

    @Test
    public void testOnItemListViewMatches() throws Exception{

        ViewInteraction editTextDescrizione = onView(allOf(withId(R.id.editText)));
        editTextDescrizione.perform(replaceText("Descrizioneutomatica"));

        onData(allOf(is(instanceOf(String.class)), is("1X"))).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("1X"))).perform(click());


        onView(withId(R.id.button_conferma_pronostico)).check(matches(isDisplayed()));
        onView(withId(R.id.button_conferma_pronostico)).perform(click());

        Thread.sleep(2000);

        //ritorno nella feedActivity e cerco la listview
        onView(withId(R.id.list_view_feed)).check(matches(isDisplayed()));


//        onView(withId(R.id.spinner)).check(matches(withText(containsString("1X"))));

        //    ViewInteraction customTextView = onView(allOf(withId(R.id.spinner), withText("1"), isDisplayed()));
      //  customTextView.perform(click());
      //  onView(withId(R.id.spinner)).perform(click());
       // onData(anything()).inAdapterView(withId(R.id.list_view)).atPosition(0).perform(click());\
    }

}
