package com.mary.mvi_base

import kotlinx.coroutines.flow.Flow

interface MviView<I: MviIntent, S: MviState> {
    fun observeIntents() : Flow<I>
    fun render(state: S)
}