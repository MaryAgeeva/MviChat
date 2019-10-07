package com.mary.mvichat.presentation.main_chat.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.mary.mvichat.R
import com.mary.mvichat.presentation.base.BaseAdapter
import com.mary.mvichat.presentation.base.BaseViewHolder
import com.mary.mvichat.presentation.main_chat.MessageViewObject
import com.mary.mvichat.presentation.utils.inflateView
import kotlinx.android.synthetic.main.item_dialog.view.*
import kotlinx.android.synthetic.main.item_dialog_current_user.view.*

class ChatAdapter : BaseAdapter<ChatAdapter.ChatViewHolder, MessageViewObject>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder =
        if(viewType == TYPE_ANOTHER_USER)
            ChatAnotherUserViewHolder(parent.inflateView(R.layout.item_dialog))
        else ChatCurrentUserViewHolder(parent.inflateView(R.layout.item_dialog_current_user))

    override fun getItemViewType(position: Int) =
        if(items[position].isCurrentUser) TYPE_CURRENT_USER
        else TYPE_ANOTHER_USER

    override fun setList(newList: MutableList<MessageViewObject>) {
        val oldList = items
        val diffResult = DiffUtil.calculateDiff(
            ChatDiffUtils(
                oldList,
                newList
            )
        )
        diffResult.dispatchUpdatesTo(this)
        this.items = newList
    }

    fun addItem(item: MessageViewObject) {
        items.add(item)
        notifyItemInserted(items.size - 1)
    }

    private companion object {
        const val TYPE_CURRENT_USER = 0
        const val TYPE_ANOTHER_USER = 1
    }

    abstract class ChatViewHolder(itemView : View) : BaseViewHolder<MessageViewObject>(itemView)

    inner class ChatAnotherUserViewHolder(itemView : View) : ChatViewHolder(itemView) {

        override fun initView(model: MessageViewObject) = with(itemView) {
            val item = items[adapterPosition]
            item_dialog_avatar.apply {
                background = item.background
                text = item.nameAbbreviation
            }
            item_dialog_autor.text = item.name
            item_dialog_text.text = item.message
            item_dialog_time.text = item.time
        }
    }

    inner class ChatCurrentUserViewHolder(itemView : View) : ChatViewHolder(itemView) {

        override fun initView(model: MessageViewObject) = with(itemView) {
            val item = items[adapterPosition]
            item_dialog_user_avatar.apply {
                background = item.background
                text = item.nameAbbreviation
            }
            item_dialog_user_autor.text = item.name
            item_dialog_user_text.text = item.message
            item_dialog_user_time.text = item.time
        }
    }
}