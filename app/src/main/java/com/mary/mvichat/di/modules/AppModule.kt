package com.mary.mvichat.di.modules

import com.mary.data.apollo.ApolloInterceptor
import com.mary.data.repositories.local.ChatStorage
import com.mary.data.repositories.local.UserStorage
import com.mary.data.repositories.remote.ChatRepository
import com.mary.domain.repositories.local.IChatStorage
import com.mary.domain.repositories.local.IUserStorage
import com.mary.domain.repositories.remote.IChatRepository
import com.mary.mvichat.presentation.utils.drawable.CircleDrawableCache
import com.mary.mvichat.presentation.utils.drawable.SimpleDrawableCache
import dagger.Binds
import dagger.Module
import okhttp3.Interceptor
import javax.inject.Singleton

@Module
interface AppModule {

    @Singleton
    @Binds
    fun provideUserStorage(userStorage: UserStorage) : IUserStorage

    @Singleton
    @Binds
    fun provideChatStorage(chatStorage: ChatStorage) : IChatStorage

    @Singleton
    @Binds
    fun provideChatRepository(chatRepository: ChatRepository) : IChatRepository

    @Singleton
    @Binds
    fun provideImageCache(cache: CircleDrawableCache) : SimpleDrawableCache<Int>

    @Singleton
    @Binds
    fun provideInterceptor(interceptor: ApolloInterceptor) : Interceptor
}