package com.mary.mvichat.presentation.main_chat.adapter

import com.mary.mvichat.presentation.base.BaseDiffUtilCallback
import com.mary.mvichat.presentation.main_chat.MessageViewObject

class ChatDiffUtils(oldList: List<MessageViewObject>,
                    newList: List<MessageViewObject>) : BaseDiffUtilCallback<MessageViewObject>(oldList, newList) {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].name == newList[newItemPosition].name &&
        oldList[oldItemPosition].time == newList[newItemPosition].time

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].message == newList[newItemPosition].message
}