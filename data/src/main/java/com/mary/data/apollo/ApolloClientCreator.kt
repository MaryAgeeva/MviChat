package com.mary.data.apollo

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.subscription.WebSocketSubscriptionTransport
import okhttp3.Interceptor
import okhttp3.OkHttpClient

class ApolloClientCreator(interceptor: Interceptor) {

    val apolloClient : ApolloClient

    init {
        val okHttp = OkHttpClient.Builder().addInterceptor(interceptor).build()
        apolloClient = ApolloClient.builder()
                            .serverUrl(ENDPOINT)
                            .subscriptionTransportFactory(
                                WebSocketSubscriptionTransport.Factory(SUBSCRIPTION_ENDPOINT, okHttp))
                            .subscriptionConnectionParams(mapOf(CONNECT_AUTH_KEY to true))
                            .okHttpClient(okHttp)
                            .build()
    }

    private companion object {
        const val ENDPOINT = "https://defaultendpoint.com/dev/graphql"
        const val SUBSCRIPTION_ENDPOINT = "wss://defaultendpoint.com/dev?auth=123"
        const val CONNECT_AUTH_KEY = "isAuthorized"
    }
}