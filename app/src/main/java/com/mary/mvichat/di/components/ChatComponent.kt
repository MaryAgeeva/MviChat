package com.mary.mvichat.di.components

import com.mary.mvichat.di.modules.ChatModule
import com.mary.mvichat.di.scopes.ChatScope
import com.mary.mvichat.presentation.main_chat.ChatFragment
import dagger.Subcomponent

@ChatScope
@Subcomponent(modules = [ChatModule::class])
interface ChatComponent {
    fun inject(fragment: ChatFragment)

    @Subcomponent.Builder
    interface Builder {
        fun build() : ChatComponent
    }
}