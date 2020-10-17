package com.kuzmin.app.messenger.api.components

import com.kuzmin.app.messenger.api.heplers.objects.UserListVO
import com.kuzmin.app.messenger.api.heplers.objects.UserVO
import com.kuzmin.app.messenger.api.models.User
import org.springframework.stereotype.Component

@Component
class UserAssembler {
    fun toUserVO(user: User): UserVO {
        return UserVO(user.id, user.userName, user.phoneNumber, user.accountState, user.createdAt.toString())
    }

    fun toUserListVO(users: List<User>): UserListVO {
        val userListVO = users.map { toUserVO(it) }
        return UserListVO(userListVO)
    }
}