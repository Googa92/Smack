package com.example.yahor.smack.Screens

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.closeSoftKeyboard
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import com.example.yahor.smack.R

class AddChannelAlert: BaseScreen() {

    private val channelNameField: ViewInteraction
        get() = onView(withId(R.id.addChannelNameTxt))

    private val channelDescriptionField: ViewInteraction
        get() = onView(withId(R.id.addChannelDescTxt))

    private val addBtn: ViewInteraction
        get() = onView(withId(android.R.id.button1))

    override val uniqueView: ViewInteraction
        get() = channelNameField

    init {
        uniqueView.check(matches(isDisplayed()))
    }

    fun addNewChannel(channelName: String, channelDescription: String): UserNavigationScreen {
        channelNameField.perform(typeText(channelName))
        channelDescriptionField.perform(typeText(channelDescription), closeSoftKeyboard())
        addBtn.perform(click())
        return UserNavigationScreen()
    }
}