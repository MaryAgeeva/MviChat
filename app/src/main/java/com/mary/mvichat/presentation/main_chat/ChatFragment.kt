package com.mary.mvichat.presentation.main_chat

import android.os.Bundle
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import com.mary.domain.utils.DEFAULT_USER
import com.mary.mvichat.ChatApp
import com.mary.mvichat.R
import com.mary.mvichat.presentation.base.BaseMviFragment
import com.mary.mvichat.presentation.main_chat.adapter.ChatAdapter
import com.mary.mvichat.presentation.utils.clicks
import com.mary.mvi_base.MviInitialView
import kotlinx.android.synthetic.main.chat_fragment.*
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*

@UseExperimental(FlowPreview::class)
class ChatFragment : BaseMviFragment<ChatViewStore, ChatIntent, ChatViewState>(),
                        MviInitialView<ChatIntent, ChatViewState, ChatIntent.GetAllMessagesIntent> {

    private var chatAdapter : ChatAdapter = ChatAdapter()

    override val viewResource = R.layout.chat_fragment

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        ChatApp.appComponent.addChatComponent().build().inject(this)
        initView()
    }

    override fun initialIntent(): Flow<ChatIntent.GetAllMessagesIntent> = listOf(ChatIntent.GetAllMessagesIntent).asFlow()

    override fun observeIntents(): Flow<ChatIntent> =
        listOf (
            chat_send_btn.clicks()
                .map { ChatIntent.NewMessageIntent(
                    if(chat_name_box.text.toString().isNotBlank())
                        chat_name_box.text.toString()
                    else DEFAULT_USER,
                    chat_text_box.text.toString()) },
            initialIntent()
        )
        .asFlow()
        .flattenMerge()

    override fun render(state: ChatViewState) {
        chat_send_btn.isEnabled = state.canSendMessage
        chat_name_box.setText(state.author)
        chat_text_box.setText(state.message)
        chatAdapter.setList(state.messages.toMutableList())
        if(state.newMessage) {
            chat_name_box.clearFocus()
            chat_text_box.clearFocus()
        }
    }

    private fun initView() {
        chat_rv.apply {
            adapter = chatAdapter
            layoutManager = LinearLayoutManager(activity)
        }
        chat_name_box.doOnTextChanged { text, _, _, _ ->
            chat_send_btn.isEnabled = chat_name_box.text.isNotBlank() && chat_text_box.text.isNotBlank()
        }
        chat_text_box.doOnTextChanged { text, _, _, _ ->
            chat_send_btn.isEnabled = chat_name_box.text.isNotBlank() && chat_text_box.text.isNotBlank()
        }
    }

    companion object {
        fun newInstance() = ChatFragment()
    }
}
