package com.example.yahor.smack.Screens

import android.support.test.espresso.Espresso
import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers
import com.example.yahor.smack.R

class AddChannelAlert: BaseScreen() {

        private val channelNameField: ViewInteraction
        get() = Espresso.onView(ViewMatchers.withId(R.id.addChannelNameTxt))

        private val channelDescriptionField: ViewInteraction
        get() = Espresso.onView(ViewMatchers.withId(R.id.addChannelDescTxt))

        private val addBtn: ViewInteraction
        get() = Espresso.onView(ViewMatchers.withId(android.R.id.button1))

        override val uniqueView: ViewInteraction
        get() = channelNameField

        init {
            uniqueView.check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        }

        fun enterChannelInformation(channelName: String, channelDescription: String){
            channelNameField.perform(ViewActions.typeText(channelName))
            channelDescriptionField.perform(ViewActions.typeText(channelDescription))
        }

        fun tapOnAddChannelBtn(){
            addBtn.perform(ViewActions.click())
        }
}