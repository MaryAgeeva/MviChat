package com.mary.mvichat.di.modules.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mary.mvichat.di.scopes.ChatScope
import javax.inject.Inject
import javax.inject.Provider

@ChatScope
class ViewModelFactory @Inject constructor(
    private val creators: Map<Class<out ViewModel>,
    @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val creator = creators[modelClass] ?:
            creators.asSequence().firstOrNull { modelClass.isAssignableFrom(it.key) }?.value ?:
            throw IllegalArgumentException("Unknown ViewModel class $modelClass")

        return try {
            creator.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}