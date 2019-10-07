package com.mary.domain.use_cases

import com.mary.domain.entities.Message
import com.mary.domain.repositories.local.IChatStorage
import com.mary.domain.repositories.local.IUserStorage
import javax.inject.Inject

class GetChatUseCase @Inject constructor(private val userStorage : IUserStorage,
                                         private val chatStorage: IChatStorage) : IGetChatUseCase {

    override suspend fun invoke(): Pair<List<Message>, String> = chatStorage.getAllMessages() to userStorage.getCurrentUser().name
}