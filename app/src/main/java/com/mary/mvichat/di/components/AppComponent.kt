package com.mary.mvichat.di.components

import android.content.Context
import com.mary.mvichat.di.modules.AppApolloModule
import com.mary.mvichat.di.modules.AppModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class, AppApolloModule::class])
@Singleton
interface AppComponent {
    fun addChatComponent() : ChatComponent.Builder

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context) : Builder

        fun build() : AppComponent
    }
}