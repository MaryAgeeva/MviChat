package com.mary.mvichat.di.modules

import com.apollographql.apollo.ApolloClient
import com.mary.data.apollo.ApolloClientCreator
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object AppApolloModule {

    @Singleton
    @Provides
    @JvmStatic
    fun provideApolloClient() : ApolloClient = ApolloClientCreator().apolloClient
}