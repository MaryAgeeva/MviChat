package com.mary.mvi_base

interface MviReducer<S: MviState, I: MviIntent> {
    fun reduce(state: S, intent: I) : S
}