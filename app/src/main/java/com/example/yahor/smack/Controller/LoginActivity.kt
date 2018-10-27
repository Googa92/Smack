package com.example.yahor.smack.Controller

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.test.espresso.IdlingRegistry
import android.view.View
import android.widget.Toast
import com.example.yahor.smack.R
import com.example.yahor.smack.Services.AuthService
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    val countingIdlingResource = AuthService.countingIdlingResource

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        loginSpinner.visibility = View.INVISIBLE
    }

    fun loginLoginBtnClicked(view: View) {

        val email = loginEmailTxt.text.toString()
        val password = loginPasswordTxt.text.toString()
        enableSpinner(true)

        if (email.isNotEmpty() && password.isNotEmpty()) {
            IdlingRegistry.getInstance().register(countingIdlingResource)
            AuthService.loginUser(email, password) { loginSuccess ->
                if (loginSuccess) {
                    AuthService.findUserByEmail(this) { findSuccess ->
                        if (findSuccess) {
                            IdlingRegistry.getInstance().unregister(countingIdlingResource)
                            enableSpinner(false)
                            finish()
                        } else {
                            IdlingRegistry.getInstance().register(countingIdlingResource)
                            errorToast()
                            loginEmailTxt.setText("")
                            loginPasswordTxt.setText("")
                        }
                    }
                } else {
                    IdlingRegistry.getInstance().register(countingIdlingResource)
                    errorToast()
                    loginEmailTxt.setText("")
                    loginPasswordTxt.setText("")
                }
            }
        } else {
            Toast.makeText(
                this, "Please fill in both email and password",
                Toast.LENGTH_LONG
            ).show()
            enableSpinner(false)
        }
    }

    fun loginCreateUserBtnClicked(view: View) {
        val createUserIntent = Intent(this, CreateUserActivity::class.java)
        finish()
        startActivity(createUserIntent)
    }

    fun enableSpinner(enable: Boolean) {
        if (enable) {
            loginSpinner.visibility = View.VISIBLE
        } else {
            loginSpinner.visibility = View.INVISIBLE
        }
        loginLoginBtn.isEnabled = !enable
        loginCreateUserBtn.isEnabled = !enable
        loginEmailTxt.isEnabled = !enable
        loginPasswordTxt.isEnabled = !enable
    }

    fun errorToast() {
        Toast.makeText(this, "Somethin went wrong, please try again", Toast.LENGTH_LONG).show()
        enableSpinner(false)
    }
}
    /*fun hideKeyboard(){
        val inputManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        if (inputManager.isAcceptingText){
            inputManager.hideSoftInputFromWindow(currentFocus.windowToken, 0)
        }
    }*/

