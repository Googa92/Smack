package com.example.yahor.smack.Services

import android.util.Log
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.example.yahor.smack.Controller.App
import com.example.yahor.smack.Model.Channel
import com.example.yahor.smack.Model.Message
import com.example.yahor.smack.Utilities.URL_GET_CHANNELS
import com.example.yahor.smack.Utilities.URL_GET_MESSAGES
import org.json.JSONException

object MessageService {

    val channels = ArrayList<Channel>()
    val messages = ArrayList<Message>()
    val countingIdlingResource = AuthService.countingIdlingResource

    fun getChannels(complete: (Boolean) -> Unit) {

        countingIdlingResource.increment()
        val channelsRequest = object : JsonArrayRequest(Method.GET, URL_GET_CHANNELS, null,
            Response.Listener { response ->
                countingIdlingResource.decrement()
                try {
                    for (x in 0 until response.length()) {
                        val channel = response.getJSONObject(x)
                        val name = channel.getString("name")
                        val chanDesc = channel.getString("description")
                        val channelId = channel.getString("_id")

                        val newChannel = Channel(name, chanDesc, channelId)
                        this.channels.add(newChannel)
                    }

                    complete(true)
                } catch (e: JSONException) {
                    Log.d("JSON", "EXC:" + e.localizedMessage)
                    complete(false)
                }

            }, Response.ErrorListener { error ->
                countingIdlingResource.decrement()
                Log.d("ERROR", "Could not retrieve channels")
                complete(false)

            }) {
            override fun getBodyContentType(): String {
                return "application/json; charset=utf-8"
            }

            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers.put("Authorization", "Bearer ${App.prefs.authToken}")
                return headers
            }
        }
        App.prefs.requestQueue.add(channelsRequest)
    }

    fun getMessages(channelId: String, complete: (Boolean) -> Unit) {

        countingIdlingResource.increment()

        var url = "$URL_GET_MESSAGES$channelId"

        val messagesRequest = object : JsonArrayRequest(Method.GET, url, null, Response.Listener { response ->

            countingIdlingResource.decrement()
            clearMessages()
            try {
                for (x in 0 until response.length()) {
                    val message = response.getJSONObject(x)
                    val messageBody = message.getString("messageBody")
                    val channelId = message.getString("channelId")
                    val id = message.getString("_id")
                    val userName = message.getString("userName")
                    val userAvatar = message.getString("userAvatar")
                    val userAvatarColor = message.getString("userAvatarColor")
                    val timeStamp = message.getString("timeStamp")

                    val newMessage = Message(messageBody, userName, channelId, userAvatar, userAvatarColor, id, timeStamp)
                    this.messages.add(newMessage)
                }
                complete(true)
            } catch (e: JSONException) {
                Log.d("JSON", "EXC:" + e.localizedMessage)
                complete(false)
            }
        }, Response.ErrorListener { error ->

            countingIdlingResource.decrement()
            Log.d("ERROR", "Could not retrieve channels")
            complete(false)
        }) {
            override fun getBodyContentType(): String {
                return "application/json; charset=utf-8"
            }

            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers.put("Authorization", "Bearer ${App.prefs.authToken}")
                return headers
            }
        }
        App.prefs.requestQueue.add(messagesRequest)
    }

    fun clearMessages(){
        messages.clear()
    }

    fun clearChannels(){
        channels.clear()
    }
}