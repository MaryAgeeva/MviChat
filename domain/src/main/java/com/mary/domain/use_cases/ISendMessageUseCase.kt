package com.mary.domain.use_cases

import com.mary.domain.entities.Message

interface ISendMessageUseCase {
    suspend operator fun invoke(author: String,
                                message: String) : Pair<List<Message>, String>
}