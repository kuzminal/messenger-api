package com.kuzmin.app.messenger.api.services

import com.kuzmin.app.messenger.api.models.User

interface UserService {
    fun attemptRegistration(userDetails: User): User
    fun listUsers(currentUser: User): List<User>
    fun retrieveUserData(userName: String): User?
    fun retrieveUserData(id: Long): User?
    fun userNameExist(userName: String): Boolean
}