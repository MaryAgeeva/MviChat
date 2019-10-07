package com.mary.mvichat.presentation.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.mary.domain.utils.LOG_TAG
import com.mary.mvi_base.MviIntent
import com.mary.mvi_base.MviState
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@UseExperimental(ExperimentalCoroutinesApi::class)
abstract class BaseMviFragment<V: BaseMviViewStore<I, S>, I: MviIntent, S: MviState> : Fragment() {

    @Inject lateinit var store: V

    private val scope = MainScope()

    protected abstract val viewResource: Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(viewResource, container, false)
    }

    override fun onResume() {
        super.onResume()
        observeIntents()
            .onEach {
                Log.i(LOG_TAG, "intent: $it")
                store.processIntents(it)
            }
            .launchIn(scope)
        scope.launch {
            store.getStates()
        }
        store.state.observe(this, Observer<S> { render(it) })
    }

    override fun onPause() {
        super.onPause()
        scope.cancel()
    }

    protected fun showToast(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
        Log.i(LOG, message)
    }

    protected abstract fun observeIntents() : Flow<I>

    protected abstract fun render(state: S)

    private companion object {
        const val LOG = "$LOG_TAG message"
    }
}