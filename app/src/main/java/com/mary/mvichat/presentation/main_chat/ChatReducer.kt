package com.mary.mvichat.presentation.main_chat

import com.mary.domain.utils.empty
import com.mary.mvichat.di.scopes.ChatScope
import com.mary.mvi_base.MviReducer
import javax.inject.Inject

@ChatScope
class ChatReducer @Inject constructor(private val mapper: ChatViewMapper) : MviReducer<ChatViewState, ChatIntent> {

    override fun reduce(state: ChatViewState, intent: ChatIntent): ChatViewState =
        when(intent) {
            is ChatIntent.NewMessageReceivedIntent -> state.copy(messages = mapper.transform(intent.messages, intent.currentUser))
            is ChatIntent.NewMessageIntent -> state.copy(message = String.empty())
            is ChatIntent.NewMessageSentIntent -> state.copy(newMessage = true)
            is ChatIntent.ChatDataReceivedIntent -> state.copy(messages = mapper.transform(intent.messages, intent.currentUser))
            else -> state
        }
}