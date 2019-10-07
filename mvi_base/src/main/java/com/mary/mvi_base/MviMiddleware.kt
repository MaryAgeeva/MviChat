package com.mary.mvi_base

import kotlinx.coroutines.flow.Flow

interface MviMiddleware<I: MviIntent, S: MviState> {
    suspend fun process(intent: I)
    fun receive() : Flow<I>
    fun close()
}