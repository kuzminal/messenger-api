package com.kuzmin.app.messenger.api.services

import com.kuzmin.app.messenger.api.repositories.UserRepository
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component

@Component
class AppUserDetailsService(val userRepository: UserRepository) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository.findByUserName(username)?:
                throw UsernameNotFoundException("A user with the username $username doesn't exist")

        return  User(user.userName, user.password, ArrayList<GrantedAuthority>())
    }
}