package com.mary.data.apollo

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.subscription.WebSocketSubscriptionTransport
import okhttp3.OkHttpClient

class ApolloClientCreator {

    val apolloClient : ApolloClient

    init {
        val okHttp = OkHttpClient.Builder().build()
        apolloClient = ApolloClient.builder()
                            .serverUrl(ENDPOINT)
                            .subscriptionTransportFactory(
                                WebSocketSubscriptionTransport.Factory(SUBSCRIPTION_ENDPOINT, okHttp))
                            .okHttpClient(okHttp)
                            .build()
    }

    private companion object {
        const val ENDPOINT = "DEFAULT_ENDPOINT"
        const val SUBSCRIPTION_ENDPOINT = "DEFAULT_WSS_ENDPOINT"
    }
}