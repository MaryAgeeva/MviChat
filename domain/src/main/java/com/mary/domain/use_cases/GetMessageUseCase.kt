package com.mary.domain.use_cases

import com.mary.domain.entities.Message
import com.mary.domain.repositories.local.IChatStorage
import com.mary.domain.repositories.local.IUserStorage
import com.mary.domain.repositories.remote.IChatRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetMessageUseCase @Inject constructor(private val userStorage: IUserStorage,
                                            private val chatStorage: IChatStorage,
                                            private val chatRepository: IChatRepository) : IGetMessageUseCase {

    override fun invoke(): Flow<Pair<List<Message>, String>> =
        chatRepository.getNewMessage()
            .map {
                chatStorage.addNewMessage(it)
                chatStorage.getAllMessages() to userStorage.getCurrentUser().name
            }
}