package com.mary.mvichat.presentation.main_chat

import com.mary.domain.utils.DEFAULT_USER
import com.mary.domain.utils.empty
import com.mary.mvi_base.MviState

data class ChatViewState(val author: String = DEFAULT_USER,
                         val message: String = String.empty(),
                         val canSendMessage: Boolean = message.isNotBlank(),
                         val newMessage: Boolean = false,
                         val messages: List<MessageViewObject> = listOf(),
                         val errorMessage: String? = null) : MviState