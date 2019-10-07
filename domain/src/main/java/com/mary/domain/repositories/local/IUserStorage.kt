package com.mary.domain.repositories.local

import com.mary.domain.entities.User

interface IUserStorage {
    fun setCurrentUser(name: String)
    fun getCurrentUser() : User
}