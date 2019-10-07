package com.mary.data.repositories.local

import com.mary.domain.entities.User
import com.mary.domain.repositories.local.IUserStorage
import com.mary.domain.utils.DEFAULT_USER
import java.util.*
import javax.inject.Inject

class UserStorage @Inject constructor() : IUserStorage {

    private val listUsers: LinkedList<User> = LinkedList()

    override fun getCurrentUser(): User = if(listUsers.isNotEmpty()) listUsers.last else User(DEFAULT_USER)

    override fun setCurrentUser(name: String) {
        val user = User(name)
        if(!listUsers.contains(user))
            listUsers.add(user)
        else {
            listUsers.remove(user)
            listUsers.add(user)
        }
    }
}