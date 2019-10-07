package com.mary.domain.repositories.local

import com.mary.domain.entities.Message

interface IChatStorage {

    suspend fun addNewMessage(message: Message)

    suspend fun getAllMessages() : List<Message>
}