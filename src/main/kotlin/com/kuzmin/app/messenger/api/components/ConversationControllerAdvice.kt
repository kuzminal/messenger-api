package com.kuzmin.app.messenger.api.components

import com.kuzmin.app.messenger.api.constants.ErrorResponse
import com.kuzmin.app.messenger.api.exceptions.ConversationIdInvalidException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody

@ControllerAdvice
class ConversationControllerAdvice {
    @ExceptionHandler
    fun conversationIdInvalidException(conversationIdInvalidException: ConversationIdInvalidException) : ResponseEntity<ErrorResponse> {
        val res = ErrorResponse("", conversationIdInvalidException.message)
        return ResponseEntity.unprocessableEntity().body(res)
    }
}