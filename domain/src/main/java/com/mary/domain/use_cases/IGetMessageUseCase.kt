package com.mary.domain.use_cases

import com.mary.domain.entities.Message
import kotlinx.coroutines.flow.Flow

interface IGetMessageUseCase {
    operator fun invoke() : Flow<Pair<List<Message>, String>>
}