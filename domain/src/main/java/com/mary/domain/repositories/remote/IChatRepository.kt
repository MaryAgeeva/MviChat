package com.mary.domain.repositories.remote

import com.mary.domain.entities.Message
import kotlinx.coroutines.flow.Flow

interface IChatRepository {

    suspend fun sendMessage(user: String, message: String) : Boolean

    fun getNewMessage() : Flow<Message>
}