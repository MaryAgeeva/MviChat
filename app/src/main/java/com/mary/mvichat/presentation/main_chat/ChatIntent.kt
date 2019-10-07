package com.mary.mvichat.presentation.main_chat

import com.mary.domain.entities.Message
import com.mary.mvi_base.MviIntent
import com.mary.mvi_base.MviInternalIntent

sealed class ChatIntent : MviIntent {

    object GetAllMessagesIntent : ChatIntent()

    data class NewMessageIntent(val author: String,
                                val message: String) : ChatIntent()

    data class NewMessageReceivedIntent(val messages: List<Message>,
                                        val currentUser: String) : ChatIntent()

    data class ChatDataReceivedIntent(val messages: List<Message>,
                                      val currentUser: String) : ChatIntent(), MviInternalIntent

    data class TextChangeIntent(val name: String,
                                val text: String) : ChatIntent()

    object ChatDataErrorIntent : ChatIntent(), MviInternalIntent

    data class NewMessageSentIntent(val messages: List<Message>,
                                    val currentUser: String) : ChatIntent(), MviInternalIntent

    data class TextChangedIntent(val canBeSent: Boolean) : ChatIntent(), MviInternalIntent

    object EmptyIntent : ChatIntent(), MviInternalIntent
}