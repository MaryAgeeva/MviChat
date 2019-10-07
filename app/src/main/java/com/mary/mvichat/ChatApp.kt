package com.mary.mvichat

import android.app.Application
import com.mary.mvichat.di.components.AppComponent
import com.mary.mvichat.di.components.DaggerAppComponent

class ChatApp : Application() {

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().context(this).build()
    }

    companion object {
        lateinit var appComponent : AppComponent
    }
}