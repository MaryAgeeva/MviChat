package com.mary.data.repositories.local

import com.mary.domain.entities.Message
import com.mary.domain.repositories.local.IChatStorage
import javax.inject.Inject

class ChatStorage @Inject constructor() : IChatStorage {

    private val messages : MutableList<Message> = mutableListOf()

    override fun addNewMessage(message: Message) {
        messages.add(message)
    }

    override suspend fun getAllMessages(): List<Message> = messages
}