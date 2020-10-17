package com.kuzmin.app.messenger.api.components

import com.kuzmin.app.messenger.api.constants.ErrorResponse
import com.kuzmin.app.messenger.api.constants.ResponseConstants
import com.kuzmin.app.messenger.api.exceptions.UserDeactivatedException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class RestControllerAdvice {
    @ExceptionHandler(UserDeactivatedException::class)
    fun userDeactivated(userDeactivatedException: UserDeactivatedException) : ResponseEntity<ErrorResponse> {
        val res = ErrorResponse(ResponseConstants.ACCOUNT_DEACTIVATED.value,
        userDeactivatedException.message)
        return ResponseEntity(res, HttpStatus.UNAUTHORIZED)
    }
}