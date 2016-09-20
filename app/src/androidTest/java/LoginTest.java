import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.app.jawad.esspressotest.MainActivity;
import com.app.jawad.esspressotest.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by jawad on 8/15/2016.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class LoginTest {
    private static final String STRING_TO_BE_TYPED = "login successfully";

    private IdlingResource mIdlingResource;

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule(MainActivity.class);

    @Before
    public void registerIdlingResource() {
        mIdlingResource = mActivityRule.getActivity().getIdlingResource();
        /**
         *Register Idling Resource to let Espresso
         *wait for async call to get completed.
         */
        Espresso.registerIdlingResources(mIdlingResource);
    }

    @Test
    public void performLoginTest() {
        /**
         * This is very self explanatory
         */
        onView(withId(R.id.et_email)).perform(typeText("agentone@testone.com"), closeSoftKeyboard());
        onView(withId(R.id.et_password)).perform(typeText("test1234"), closeSoftKeyboard());
        onView(withId(R.id.btn_login)).perform(click());
        onView(withId(R.id.tv_login_status)).check(matches(withText(STRING_TO_BE_TYPED)));
    }

    @After
    public void unregisterIdlingResource() {
        /**
         * Un registering the idle Resources
         * once call back complete for the async
         * call
         */
        if (mIdlingResource != null) {
            Espresso.unregisterIdlingResources(mIdlingResource);
        }
    }


}
