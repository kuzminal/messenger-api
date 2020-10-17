package com.kuzmin.app.messenger.api.components

import com.kuzmin.app.messenger.api.constants.ErrorResponse
import com.kuzmin.app.messenger.api.constants.ResponseConstants
import com.kuzmin.app.messenger.api.exceptions.MessageEmptyException
import com.kuzmin.app.messenger.api.exceptions.MessageRecipientInvalidException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class MessageControllerAdvice {
    @ExceptionHandler(MessageEmptyException::class)
    fun messageEmpty(messageEmptyException: MessageEmptyException) : ResponseEntity<ErrorResponse> {
        val res = ErrorResponse(ResponseConstants.MESSAGE_EMPTY.value,
        messageEmptyException.message)

        return ResponseEntity.unprocessableEntity().body(res)
    }

    @ExceptionHandler(MessageRecipientInvalidException::class)
    fun messageRecipientInvalid(messageRecipientInvalidException: MessageRecipientInvalidException) : ResponseEntity<ErrorResponse> {
        val res = ErrorResponse(ResponseConstants.MESSAGE_RECIPIENT_INVALID.value,
        messageRecipientInvalidException.message)
        return ResponseEntity.unprocessableEntity().body(res)
    }
}