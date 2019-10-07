package com.mary.mvi_base

interface MviViewStore<I: MviIntent, S: MviState> {
    suspend fun processIntents(intent: I)
    suspend fun getStates()
}