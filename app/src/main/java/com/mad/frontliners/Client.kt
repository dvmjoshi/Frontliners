package com.mad.frontliners

import okhttp3.OkHttpClient
import okhttp3.Request

// Kotlin Singleton concept is used to call api.
//Concept 1  for 30 Days with kotlin
object Client {
    private val OkHttpClient = OkHttpClient()
    private val request = Request.Builder().url("https://api.covid19india.org/data.json").build()

    val api = OkHttpClient.newCall(request)


}

