package com.example.yahor.smack.Screens

import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.closeSoftKeyboard
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withContentDescription
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import com.example.yahor.smack.R
import org.hamcrest.Matchers
import org.hamcrest.Matchers.allOf
import java.util.Random

class MainScreen: BaseScreen() {

    val randomString = "Hello${Random().nextInt(100 - 1)}"

    val messageIsSent: ViewInteraction
        get() = onView(allOf(withId(R.id.messageListView), withText(randomString)))

    private val openNavigationDrawerBtn: ViewInteraction
    get() = onView(
        allOf(
            withContentDescription("Open navigation drawer"),
            isDescendantOfA(withId(R.id.toolbar))
        ))

    private val messageField: ViewInteraction
        get() = onView(withId(R.id.messageTextField))

    private val sendMessageBtn: ViewInteraction
        get() = onView(withId(R.id.sendMessageBtn))

    override val uniqueView: ViewInteraction
        get() = openNavigationDrawerBtn

    init{
        uniqueView.check(matches(isDisplayed()))
    }

    fun clickOnNavigationDrawer() : UserNavigationScreen {
        openNavigationDrawerBtn.perform(click())
        return UserNavigationScreen()
    }

    fun enterMessage(){
        messageField.perform(typeText(randomString))
        closeSoftKeyboard()
        sendMessageBtn.perform(click())
    }
}