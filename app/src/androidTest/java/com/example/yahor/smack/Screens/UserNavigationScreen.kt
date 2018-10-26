package com.example.yahor.smack.Screens

import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import com.example.yahor.smack.Controller.App
import com.example.yahor.smack.R
import org.hamcrest.Matchers.allOf

class UserNavigationScreen: BaseScreen() {

    private val loginLogoutBtn: ViewInteraction
        get() = onView(withId(R.id.loginBtnNavHeader))

    private val userEmailI: ViewInteraction
        get() = onView(withId(R.id.userEmailNavHeader))

    val channelIsAdded: ViewInteraction
        get() = onView(allOf(
                withId(R.id.channel_list),
                withText("TEST!")
            )
        )

    val userIsLogout: ViewInteraction
        get() = loginLogoutBtn.check(matches(withText("LOGIN")))

    private val addChannelBtn: ViewInteraction
        get() = Espresso.onView(ViewMatchers.withId(R.id.addChannelBtn))

    val userInfoIsDisplayed: ViewInteraction
        get() = userEmailI.check(matches(isDisplayed()))

    override val uniqueView: ViewInteraction
        get() = loginLogoutBtn

    init {
        uniqueView.check(matches(isDisplayed()))
    }

    fun clickOnLoginLogoutBtn():LoginScreen {
            loginLogoutBtn.perform(click())
            return LoginScreen()
    }

    fun clickOnAddChannelBtn() {
        addChannelBtn.perform(click())
    }

}