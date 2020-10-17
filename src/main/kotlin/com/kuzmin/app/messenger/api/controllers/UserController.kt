package com.kuzmin.app.messenger.api.controllers

import com.kuzmin.app.messenger.api.components.UserAssembler
import com.kuzmin.app.messenger.api.heplers.objects.UserListVO
import com.kuzmin.app.messenger.api.heplers.objects.UserVO
import com.kuzmin.app.messenger.api.models.User
import com.kuzmin.app.messenger.api.repositories.UserRepository
import com.kuzmin.app.messenger.api.services.UserServiceImpl
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/users")
@Api(description = "Пользователи")
class UserController(val userService: UserServiceImpl,
                     val userAssembler: UserAssembler,
                     val userRepository: UserRepository) {

    @PostMapping
    @RequestMapping("/registrations")
    @ApiOperation("Регистрация нового пользователя")
    fun create(@Validated @RequestBody userDetails: User) :
            ResponseEntity<UserVO> {
        val user = userService.attemptRegistration(userDetails)
        return ResponseEntity.ok(userAssembler.toUserVO(user))
    }

    @GetMapping
    @RequestMapping("/{user_id}")
    fun show(@PathVariable("user_id") userId: Long):
            ResponseEntity<UserVO> {
        val user = userService.retrieveUserData(userId)
        return ResponseEntity.ok(userAssembler.toUserVO(user!!))
    }

    @GetMapping
    @RequestMapping("/details")
    fun echoDetails(request: HttpServletRequest): ResponseEntity<UserVO> {
        val user = userRepository.findByUserName(request.userPrincipal.name) as User
        return ResponseEntity.ok(userAssembler.toUserVO(user))
    }

    @GetMapping
    fun index(request: HttpServletRequest): ResponseEntity<UserListVO> {
        val user = userRepository.findByUserName(request.userPrincipal.name) as User
        val users = userService.listUsers(user)
        return  ResponseEntity.ok(userAssembler.toUserListVO(users))
    }

    @PutMapping
    fun update(@RequestBody updateDetails: User,
    request: HttpServletRequest): ResponseEntity<UserVO> {
        val currentUser = userRepository.findByUserName(request.userPrincipal.name)
        userService.updateUserStatus(currentUser as User, updateDetails)
        return ResponseEntity.ok(userAssembler.toUserVO(currentUser))
    }
}