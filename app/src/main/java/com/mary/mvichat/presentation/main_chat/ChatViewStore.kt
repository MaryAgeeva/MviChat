package com.mary.mvichat.presentation.main_chat

import android.util.Log
import com.mary.domain.utils.LOG_TAG
import com.mary.mvichat.presentation.base.BaseMviViewStore
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class ChatViewStore @Inject constructor(private val middleware: ChatMiddleware,
                                        private val reducer : ChatReducer): BaseMviViewStore<ChatIntent, ChatViewState>() {

    override suspend fun processIntents(intent: ChatIntent) {
        middleware.process(intent)
    }

    override suspend fun getStates() {
        listOf(
            middleware.receive()
                .map { reducer.reduce(state.value?: ChatViewState(), it) }
                .onEach { Log.i(LOG_TAG, "state: $it") },
            middleware.listenForChat()
                .map { reducer.reduce(state.value?: ChatViewState(),
                    ChatIntent.NewMessageReceivedIntent(messages = it.first,
                                                        currentUser = it.second)) }
            ).asFlow()
        .flattenMerge()
        .collect { state.value = it }
    }
}
