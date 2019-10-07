package com.mary.data.repositories.remote

import android.util.Log
import com.apollographql.apollo.ApolloClient
import com.mary.ListenMessageSubscription
import com.mary.SendMessageMutation
import com.mary.data.utils.asDeferred
import com.mary.data.utils.asFlow
import com.mary.domain.entities.Message
import com.mary.domain.repositories.remote.IChatRepository
import com.mary.domain.utils.LOG_TAG
import com.mary.domain.utils.MessageIsBlankException
import com.mary.domain.utils.empty
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import java.util.*
import javax.inject.Inject

@UseExperimental(ExperimentalCoroutinesApi::class)
class ChatRepository @Inject constructor(private val apolloClient: ApolloClient) : IChatRepository {

    override suspend fun sendMessage(user: String, message: String) : Boolean =
        apolloClient.mutate(SendMessageMutation.builder()
            .message("$user : $message")
            .build())
            .asDeferred().await()
            .data()?.sendMessage()?.text()?.isNotBlank() == true

    override fun getNewMessage(): Flow<Message> {
        return apolloClient.subscribe(ListenMessageSubscription.builder().build()).asFlow()
            .map {
                processMessage(it.data()?.messageFeed()?.text()?: String.empty())
            }
            .catch { Log.e(LOG_TAG, "${javaClass.name} exception: $it") }
            .flowOn(Dispatchers.IO)
    }

    private fun processMessage(text: String) : Message {
        val message = text.split(":").asSequence().filter { it.isNotBlank() }.map { it.trim() }.toList()
        if(message.isNotEmpty() && message.size >= 2)
            return Message(author = message[0],
                            text = message[1],
                            time = Date())
        else throw MessageIsBlankException()
    }
}