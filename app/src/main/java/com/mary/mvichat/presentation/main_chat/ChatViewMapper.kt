package com.mary.mvichat.presentation.main_chat

import com.mary.domain.entities.Message
import com.mary.domain.utils.DEFAULT_USER
import com.mary.domain.utils.empty
import com.mary.mvichat.R
import com.mary.mvichat.di.scopes.ChatScope
import com.mary.mvichat.presentation.utils.drawable.SimpleDrawableCache
import com.mary.mvichat.presentation.utils.toDateString
import javax.inject.Inject

@ChatScope
class ChatViewMapper @Inject constructor(private val drawableCache: SimpleDrawableCache<Int>) {

    internal fun transform(messages: List<Message>, currentUserName: String = DEFAULT_USER) : List<MessageViewObject> =
        messages.map { MessageViewObject(nameAbbreviation = if(it.author.isNotEmpty()) it.author.first().toString() else String.empty(),
            name = it.author,
            message = it.text,
            time = it.time.toDateString(),
            isCurrentUser = it.author == currentUserName,
            background = if(it.author == currentUserName)
                drawableCache.get(R.color.orange)
            else drawableCache.getRandom()) }
}