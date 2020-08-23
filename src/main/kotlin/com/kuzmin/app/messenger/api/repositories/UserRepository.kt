package com.kuzmin.app.messenger.api.repositories

import com.kuzmin.app.messenger.api.models.User
import org.springframework.data.repository.CrudRepository

interface UserRepository: CrudRepository<User, Long> {
    fun findByUserName(userName: String): User?
    fun findByPhoneNumber(phoneNumber: String): User?
}