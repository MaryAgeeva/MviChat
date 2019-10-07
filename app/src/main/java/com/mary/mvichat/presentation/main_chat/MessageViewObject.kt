package com.mary.mvichat.presentation.main_chat

import android.graphics.drawable.Drawable

class MessageViewObject(val nameAbbreviation: String,
                        val name: String,
                        val message: String,
                        val time: String,
                        val isCurrentUser: Boolean,
                        val background: Drawable)