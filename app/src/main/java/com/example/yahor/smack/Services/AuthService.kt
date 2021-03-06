package com.example.yahor.smack.Services

import android.content.Context
import android.content.Intent
import android.support.test.espresso.idling.CountingIdlingResource
import android.support.v4.content.LocalBroadcastManager
import android.util.Log
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.example.yahor.smack.Controller.App
import com.example.yahor.smack.Utilities.BROADCAST_USER_DATA_CHANGE
import com.example.yahor.smack.Utilities.URL_CREATE_USER
import com.example.yahor.smack.Utilities.URL_GET_USER
import com.example.yahor.smack.Utilities.URL_LOGIN
import com.example.yahor.smack.Utilities.URL_REGISTER
import org.json.JSONException
import org.json.JSONObject

object AuthService {

    val countingIdlingResource = CountingIdlingResource("API")

    fun registerUser(context: Context, email: String, password: String, complete: (Boolean) -> Unit){

        countingIdlingResource.increment()

        val jsonBody = JSONObject()
        jsonBody.put("email", email)
        jsonBody.put("password", password)
        val requestBody = jsonBody.toString()

        val registerRequest =
            object : StringRequest(Method.POST, URL_REGISTER, Response.Listener { response ->
                countingIdlingResource.decrement()
                complete(true)
            }, Response.ErrorListener { error ->
                countingIdlingResource.decrement()
                Log.d("ERROR", "Could not register user: $error")
                complete(false)

            }) {
                override fun getBodyContentType(): String {
                    return "application/json; charset=utf-8"
                }

                override fun getBody(): ByteArray {
                    return requestBody.toByteArray()
                }
            }

        App.prefs.requestQueue.add(registerRequest)
    }

    fun loginUser( email: String, password: String, complete: (Boolean) -> Unit) {

        countingIdlingResource.increment()

        val jsonBody = JSONObject()
        jsonBody.put("email", email)
        jsonBody.put("password", password)
        val requestBody = jsonBody.toString()

        val loginRequest = object : JsonObjectRequest(Method.POST, URL_LOGIN, null,

            Response.Listener { response ->
                countingIdlingResource.decrement()

                try{
                    App.prefs.userEmail = response.getString("user")
                    App.prefs.authToken = response.getString("token")
                    App.prefs.isLoggedIn = true
                    complete(true)
                } catch (e:JSONException){
                    Log.d("JSON", "EXC:"+e.localizedMessage)
                    complete(false)
                }

            }, Response.ErrorListener { error ->
                countingIdlingResource.decrement()
                Log.d("ERROR", "Could not login user: $error")
                complete(false)
            })
            {
            override fun getBodyContentType(): String {
                return "application/json; charset=utf-8"
            }

            override fun getBody(): ByteArray {
                return requestBody.toByteArray()
            }
        }
        App.prefs.requestQueue.add(loginRequest)
    }

    fun createUser(name:String, email: String, avatarName:String, avatarColor:String, complete: (Boolean) -> Unit){

        countingIdlingResource.increment()

        val jsonBody = JSONObject()
        jsonBody.put("name", name)
        jsonBody.put("email", email)
        jsonBody.put("avatarName", avatarName)
        jsonBody.put("avatarColor", avatarColor)
        val requestBody = jsonBody.toString()

        val createRequest = object : JsonObjectRequest(Method.POST, URL_CREATE_USER, null, 
            Response.Listener {response ->
                countingIdlingResource.decrement()
                try {

                    UserDataService.name = response.getString("name")
                    UserDataService.email = response.getString("email")
                    UserDataService.avatarName = response.getString("avatarName")
                    UserDataService.avatarColor = response.getString("avatarColor")
                    UserDataService.id = response.getString("_id")
                    complete(true)

                }catch (e: JSONException){
                    Log.d("JSON", "EXC"+e.localizedMessage)
                    complete(false)
                }
        },
            Response.ErrorListener{error ->

                countingIdlingResource.decrement()
                Log.d("ERROR", "Could not register user: $error")
                complete(false)
        }) {
            override fun getBodyContentType(): String {
                return "application/json; charset=utf-8"
            }

            override fun getBody(): ByteArray {
                return requestBody.toByteArray()
            }

            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String,String>()
                headers.put("Authorization","Bearer ${App.prefs.authToken}")
                return headers
            }
        }
        App.prefs.requestQueue.add(createRequest)
    }

    fun findUserByEmail(context:Context, complete: (Boolean) -> Unit){

        countingIdlingResource.increment()

        val findUserRequest = object: JsonObjectRequest(Method.GET, "$URL_GET_USER${App.prefs.userEmail}", null,
            Response.Listener {response ->

                countingIdlingResource.decrement()
                try {
                    UserDataService.name = response.getString("name")
                    UserDataService.email = response.getString("email")
                    UserDataService.avatarName = response.getString("avatarName")
                    UserDataService.avatarColor = response.getString("avatarColor")
                    UserDataService.id = response.getString("_id")

                    val userDataChange = Intent(BROADCAST_USER_DATA_CHANGE)
                    LocalBroadcastManager.getInstance(context).sendBroadcast(userDataChange)
                    complete(true)

                }catch (e:JSONException){
                    Log.d("JSON", "EXC" + e.localizedMessage)
                }
            },
            Response.ErrorListener {  error ->

                countingIdlingResource.decrement()

                Log.d("ERROR", "Could not find user.")
                complete(false)
            }){
            override fun getBodyContentType(): String {
                return "application/json; charset=utf-8"
            }

            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String,String>()
                headers.put("Authorization","Bearer ${App.prefs.authToken}")
                return headers
            }
        }
        App.prefs.requestQueue.add(findUserRequest)
    }
}