package com.mary.mvichat.presentation.main_chat

import com.mary.domain.entities.Message
import com.mary.domain.use_cases.IGetChatUseCase
import com.mary.domain.use_cases.IGetMessageUseCase
import com.mary.domain.use_cases.ISendMessageUseCase
import com.mary.mvichat.di.scopes.ChatScope
import com.mary.mvi_base.MviMiddleware
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ChatScope
@UseExperimental(ExperimentalCoroutinesApi::class, FlowPreview::class)
class ChatMiddleware @Inject constructor(private val getChat: IGetChatUseCase,
                                         private val getMessage: IGetMessageUseCase,
                                         private val sendMessage: ISendMessageUseCase)
    : MviMiddleware<ChatIntent, ChatViewState> {

    private val channel = ConflatedBroadcastChannel<ChatIntent>()

    override suspend fun process(intent: ChatIntent) {
        when(intent) {
            is ChatIntent.GetAllMessagesIntent -> {
                try {
                    withContext(Dispatchers.IO) {
                        val result = getChat()
                        channel.offer(
                            ChatIntent.ChatDataReceivedIntent(
                                result.first,
                                result.second
                            )
                        )
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    channel.offer(ChatIntent.ChatDataErrorIntent)
                }
            }
            is ChatIntent.NewMessageIntent -> {
                withContext(Dispatchers.IO) {
                    val result = sendMessage(intent.author, intent.message)
                    channel.offer(ChatIntent.NewMessageSentIntent(result.first, result.second))
                }
            }
            is ChatIntent.TextChangeIntent ->
                channel.offer(ChatIntent.TextChangedIntent(canBeSent = intent.name.isNotBlank() && intent.text.isNotBlank()))
            else -> channel.offer(ChatIntent.EmptyIntent)
        }
    }

    override fun receive(): Flow<ChatIntent> = channel.asFlow()

    override fun close() {
        channel.close()
    }

    internal fun listenForChat(): Flow<Pair<List<Message>, String>> = getMessage()
}