package com.mary.mvichat.presentation.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mary.mvi_base.MviIntent
import com.mary.mvi_base.MviState
import com.mary.mvi_base.MviViewStore

abstract class BaseMviViewStore<I: MviIntent, S: MviState> : ViewModel(), MviViewStore<I, S> {

    val state : MutableLiveData<S> = MutableLiveData()
}