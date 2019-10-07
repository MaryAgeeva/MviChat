package com.mary.mvi_base

import kotlinx.coroutines.flow.Flow

interface MviInitialView<I: MviIntent, S: MviState, In: MviIntent> : MviView<I, S> {
    fun initialIntent(): Flow<In>
}