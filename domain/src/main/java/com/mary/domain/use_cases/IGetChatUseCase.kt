package com.mary.domain.use_cases

import com.mary.domain.entities.Message

interface IGetChatUseCase {
    suspend operator fun invoke() : Pair<List<Message>, String>
}