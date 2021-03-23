package com.example.demo_list_tweets.data.interceptors

import android.content.Context
import com.example.demo_list_tweets.R
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val context: Context) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        val token = getTokenByPreference()
        if (token.isNotEmpty()) {
            val finalToken = "Bearer $token"
            request = request.newBuilder()
                .addHeader("Authorization", finalToken)
                .build()
        }
        return chain.proceed(request)
    }

    private fun getTokenByPreference(): String {
        return context.resources.getString(R.string.token)
    }
}