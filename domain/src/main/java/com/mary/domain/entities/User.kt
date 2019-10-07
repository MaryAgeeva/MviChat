package com.mary.domain.entities

data class User(val name: String,
                val messages: List<Message> = listOf())