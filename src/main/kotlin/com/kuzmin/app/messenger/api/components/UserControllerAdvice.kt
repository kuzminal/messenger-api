package com.kuzmin.app.messenger.api.components

import com.kuzmin.app.messenger.api.constants.ErrorResponse
import com.kuzmin.app.messenger.api.constants.ResponseConstants
import com.kuzmin.app.messenger.api.exceptions.InvalidUserIdException
import com.kuzmin.app.messenger.api.exceptions.UserNameUnavailableException
import com.kuzmin.app.messenger.api.exceptions.UserStatusEmptyException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class UserControllerAdvice {

    @ExceptionHandler(UserNameUnavailableException::class)
    fun userNameUnavailable(userNameUnavailableException: UserNameUnavailableException)
            : ResponseEntity<ErrorResponse> {
        val res = ErrorResponse(ResponseConstants.USERNAME_UNAVAILABLE.value, userNameUnavailableException.message)
        return ResponseEntity.unprocessableEntity().body(res)
    }

    @ExceptionHandler(InvalidUserIdException::class)
    fun invalidId(invalidUserIdException: InvalidUserIdException) : ResponseEntity<ErrorResponse> {
        val res = ErrorResponse(ResponseConstants.INVALID_USER_ID.value,
        invalidUserIdException.message)
        return ResponseEntity.badRequest().body(res)
    }

    @ExceptionHandler(UserStatusEmptyException::class)
    fun statusEmpty(userStatusEmptyException: UserStatusEmptyException): ResponseEntity<ErrorResponse> {
        val res = ErrorResponse(ResponseConstants.EMPTY_STATUS.value,
        userStatusEmptyException.message)
        return ResponseEntity.unprocessableEntity().body(res)
    }
}