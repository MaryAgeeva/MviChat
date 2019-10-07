package com.mary.mvichat.presentation.utils

import android.view.View
import android.widget.EditText
import androidx.core.widget.doOnTextChanged
import com.mary.domain.utils.empty
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

@UseExperimental(ExperimentalCoroutinesApi::class)
fun View.clicks(): Flow<Unit> = callbackFlow {
    setOnClickListener { offer(Unit) }
    awaitClose {
        setOnClickListener(null)
    }
}

@UseExperimental(ExperimentalCoroutinesApi::class)
fun EditText.textChanged() : Flow<CharSequence> = callbackFlow {
    doOnTextChanged { text, _, _, _ ->
        offer(text?: String.empty())
    }
    awaitClose {
        addTextChangedListener(null)
    }
}