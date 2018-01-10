package it.fmgroup.pronostigram;

import android.app.Activity;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.TextView;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Rule public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<MainActivity>(MainActivity.class);


    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("it.fmgroup.pronostigram", appContext.getPackageName());
        //provaTest1();
    }

    @Test
    public void provaTest() {
        Activity activity = mActivityRule.getActivity();
        TextView tv = activity.findViewById(R.id.textview);
        assertEquals("dovrebbero essere uguali", tv.getText().toString(), "Benvenuto!");
        activity.finish();
    }
/*
    @Test
    public void provaTest1() {
        Activity activity = mActivityRule.getActivity();
        TextView tv = activity.findViewById(R.id.textview);
        assertEquals("test che fallisce", tv.getText().toString(), "sbagliato");
        activity.finish();
    }
*/
}
