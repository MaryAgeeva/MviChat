package com.mary.domain.entities

import java.util.*

data class Message(val author: String,
                   val text: String,
                   val time: Date)