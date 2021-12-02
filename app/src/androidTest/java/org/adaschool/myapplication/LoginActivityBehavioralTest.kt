package org.adaschool.myapplication

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.hasErrorText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import org.adaschool.taskplanner.R
import org.adaschool.taskplanner.ui.login.LoginActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * @author Santiago Carrillo
 * 22/11/21.
 */
@RunWith(AndroidJUnit4::class)
@SmallTest
class LoginActivityBehavioralTest {

    @get:Rule
    var activityRule: ActivityScenarioRule<LoginActivity> =
        ActivityScenarioRule(LoginActivity::class.java)


    @Test
    fun invalidEmailThenErrorMessageDisplayed() {
        onView(withId(R.id.emailEditText)).perform(clearText(), typeText("santiagomail.com"))
        closeSoftKeyboard()
        onView(withId(R.id.loginButton)).perform(click())
        onView(withId(R.id.emailEditText)).check(matches(hasErrorText(TestsUtils.getResourceString(R.string.invalid_email))))
    }

}