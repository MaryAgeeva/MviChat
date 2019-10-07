package com.mary.domain.use_cases

import com.mary.domain.entities.Message
import com.mary.domain.repositories.local.IChatStorage
import com.mary.domain.repositories.local.IUserStorage
import com.mary.domain.repositories.remote.IChatRepository
import javax.inject.Inject

class SendMessageUseCase @Inject constructor(private val userStorage: IUserStorage,
                                             private val chatStorage: IChatStorage,
                                             private val chatRepository: IChatRepository) : ISendMessageUseCase {

    override suspend fun invoke(author: String, message: String) : Pair<List<Message>, String> {
        userStorage.setCurrentUser(author)
        try {
            chatRepository.sendMessage(author, message)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return chatStorage.getAllMessages() to userStorage.getCurrentUser().name
    }
}