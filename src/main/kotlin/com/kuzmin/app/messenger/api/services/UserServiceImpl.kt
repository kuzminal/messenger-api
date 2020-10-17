package com.kuzmin.app.messenger.api.services

import com.kuzmin.app.messenger.api.exceptions.InvalidUserIdException
import com.kuzmin.app.messenger.api.exceptions.UserNameUnavailableException
import com.kuzmin.app.messenger.api.exceptions.UserStatusEmptyException
import com.kuzmin.app.messenger.api.models.User
import com.kuzmin.app.messenger.api.repositories.UserRepository

class UserServiceImpl(val repository: UserRepository) : UserService {
    @Throws(UserNameUnavailableException::class)
    override fun attemptRegistration(userDetails: User): User {
        if (!userNameExist(userDetails.userName)) {
            val user = User()
            user.userName = userDetails.userName
            user.phoneNumber = userDetails.phoneNumber
            user.password = userDetails.password
            repository.save(user)
            obscurePassword(user)
            return user
        }
        throw UserNameUnavailableException("The username ${userDetails.userName} is unavailable.")
    }

    @Throws(UserStatusEmptyException::class)
    fun updateUserStatus(currentUser: User, updateDetails: User): User {
        if (!updateDetails.status.isEmpty()) {
            currentUser.status = updateDetails.status
            repository.save(currentUser)
            return currentUser
        }
        throw UserStatusEmptyException()
    }

    @Throws(UserStatusEmptyException::class)
    override fun listUsers(currentUser: User): List<User> {
        return repository.findAll().mapTo(ArrayList(), { it }).filter { it != currentUser }
    }

    override fun retrieveUserData(userName: String): User? {
        val user = repository.findByUserName(userName)
        obscurePassword(user)
        return user
    }

    @Throws(InvalidUserIdException::class)
    override fun retrieveUserData(id: Long): User? {
        val userOptional = repository.findById(id)
        if (userOptional.isPresent) {
            val user = userOptional.get()
            obscurePassword(user)
            return user
        }
        throw InvalidUserIdException("A user with an id of '$id' does not exist.")
    }

    override fun userNameExist(userName: String): Boolean {
        return repository.findByUserName(userName) != null
    }

    private fun obscurePassword(user: User?) {
        user?.password = "XXX XXXX XXX"
    }
}