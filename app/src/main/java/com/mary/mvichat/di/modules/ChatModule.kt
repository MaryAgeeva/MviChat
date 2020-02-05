package com.mary.mvichat.di.modules

import androidx.lifecycle.ViewModel
import com.mary.domain.use_cases.*
import com.mary.mvichat.di.modules.view_model.ViewModelKey
import com.mary.mvichat.di.scopes.ChatScope
import com.mary.mvichat.presentation.main_chat.ChatViewStore
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ChatModule {

    @Binds
    @IntoMap
    @ViewModelKey(ChatViewStore::class)
    fun provideChatStore(chatViewStore: ChatViewStore) : ViewModel

    @ChatScope
    @Binds
    fun provideMessagesUseCase(useCase: GetMessageUseCase) : IGetMessageUseCase

    @ChatScope
    @Binds
    fun provideSendUseCase(useCase: SendMessageUseCase) : ISendMessageUseCase

    @ChatScope
    @Binds
    fun provideChatUseCase(useCase: GetChatUseCase) : IGetChatUseCase
}