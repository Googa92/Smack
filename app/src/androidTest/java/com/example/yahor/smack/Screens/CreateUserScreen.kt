package com.example.yahor.smack.Screens

import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import com.example.yahor.smack.R

class CreateUserScreen: BaseScreen(){

    private val userNameField: ViewInteraction
        get() = onView(withId(R.id.createUserNameText))

    private val emailField: ViewInteraction
        get() = Espresso.onView(ViewMatchers.withId(R.id.createEmailText))

    private val passwordField: ViewInteraction
        get() = Espresso.onView(ViewMatchers.withId(R.id.createPasswordText))

    private val generateUserAvatarBtn: ViewInteraction
        get() = Espresso.onView(ViewMatchers.withId(R.id.createAvatarImageView))

    private val createBackgroundColorBtn: ViewInteraction
        get() = Espresso.onView(ViewMatchers.withId(R.id.backgroundColorBtn))

    private val createUserBtn: ViewInteraction
        get() = Espresso.onView(ViewMatchers.withId(R.id.createUserBtn))

    override val uniqueView: ViewInteraction
        get() = createUserBtn

    init {
        uniqueView.check(matches(isDisplayed()))
    }

    fun enterNewUser(userName: String, email: String, password: String){
        userNameField.perform(typeText(userName))
        emailField.perform(typeText(email))
        passwordField.perform(typeText(password))
    }

    fun tapOnGenerateUserAvatarBtn(){
        generateUserAvatarBtn.perform(click())
    }

    fun tapOnGenerateBackgroundColorBtn(){
        createBackgroundColorBtn.perform(click())
    }

    fun tapOnCreateUserBtn(){
        createUserBtn.perform(click())
    }
}