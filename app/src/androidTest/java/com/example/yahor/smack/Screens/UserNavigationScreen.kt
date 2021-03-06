package com.example.yahor.smack.Screens

import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import com.example.yahor.smack.R

class UserNavigationScreen: BaseScreen() {

    private val loginLogoutBtn: ViewInteraction
        get() = onView(withId(R.id.loginBtnNavHeader))

    private val userEmailInfo: ViewInteraction
        get() = onView(withId(R.id.userEmailNavHeader))

    val userIsLogout: ViewInteraction
        get() = loginLogoutBtn.check(matches(withText("LOGIN")))

    private val addChannelBtn: ViewInteraction
        get() = Espresso.onView(ViewMatchers.withId(R.id.addChannelBtn))

    val userInfoIsDisplayed: ViewInteraction
        get() = userEmailInfo.check(matches(isDisplayed()))

    override val uniqueView: ViewInteraction
        get() = loginLogoutBtn

    init {
        uniqueView.check(matches(isDisplayed()))
    }

    fun clickOnLoginLogoutBtn():LoginScreen {
            loginLogoutBtn.perform(click())
            return LoginScreen()
    }

    fun clickOnAddChannelBtn() :AddChannelAlert {
        addChannelBtn.perform(click())
        return AddChannelAlert()
    }
}