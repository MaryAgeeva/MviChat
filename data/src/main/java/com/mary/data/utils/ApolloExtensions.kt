package com.mary.data.utils

import android.util.Log
import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.ApolloSubscriptionCall
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import com.mary.domain.utils.LOG_TAG
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

fun <T> ApolloSubscriptionCall<T>.asFlow() : Flow<Response<T>> = flow {
    val channel = Channel<Response<T>>(Channel.UNLIMITED)
    execute(ChannelCallback(channel))
    for(item in channel)
        emit(item)
}

fun <T> ApolloCall<T>.asDeferred() : Deferred<Response<T>> {
    val deferred = CompletableDeferred<Response<T>>()
    deferred.invokeOnCompletion {
        if(!deferred.isCancelled)
            cancel()
    }
    enqueue(object : ApolloCall.Callback<T>() {
        override fun onFailure(e: ApolloException) {
            deferred.completeExceptionally(e)
        }

        override fun onResponse(response: Response<T>) {
            deferred.complete(response)
        }
    })
    return deferred
}

suspend fun <T> ApolloCall<T>.await() : T? {
    return suspendCancellableCoroutine { cont ->
        enqueue(object : ApolloCall.Callback<T>() {
            override fun onFailure(e: ApolloException) {
                cont.resumeWithException(e)
            }

            override fun onResponse(response: Response<T>) {
                cont.resume(response.data())
            }
        })
        cont.invokeOnCancellation { this@await.cancel() }
    }
}

private class ChannelCallback<T>(val channel: Channel<Response<T>>) : ApolloSubscriptionCall.Callback<T> {

    override fun onConnected() {
        Log.i(LOG_TAG, "subscription: connected")
    }

    override fun onTerminated() {
        Log.i(LOG_TAG, "subscription: terminated")
        channel.close()
    }

    override fun onCompleted() {
        Log.i(LOG_TAG, "subscription: completed")
        channel.close()
    }

    override fun onFailure(e: ApolloException) {
        Log.i(LOG_TAG, "subscription: failed, exception: $e")
        channel.close(e)
    }

    override fun onResponse(response: Response<T>) {
        Log.i(LOG_TAG, "subscription: response: $response")
        channel.offer(response)
    }
}
