package com.mary.data.apollo

import com.mary.domain.utils.LOG_TAG
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject

class ApolloInterceptor @Inject constructor() : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request().newBuilder()
            .addHeader(APP_NAME, LOG_TAG)
            .build()
        return chain.proceed(newRequest)
    }

    private companion object {
        const val APP_NAME = "client-name"
    }
}