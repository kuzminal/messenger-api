package com.kuzmin.app.messenger.api.controllers

import com.kuzmin.app.messenger.api.components.MessageAssembler
import com.kuzmin.app.messenger.api.heplers.objects.MessageVO
import com.kuzmin.app.messenger.api.models.User
import com.kuzmin.app.messenger.api.repositories.UserRepository
import com.kuzmin.app.messenger.api.services.MessageService
import com.kuzmin.app.messenger.api.services.MessageServiceImpl
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/messages")
class MessageController(val messageService: MessageServiceImpl,
                        val userRepository: UserRepository,
                        val messageAssembler: MessageAssembler) {
    @PostMapping
    fun create(@RequestBody messageDetails: MessageRequest,
    request: HttpServletRequest): ResponseEntity<MessageVO> {
        val principal = request.userPrincipal
        val sender = userRepository.findByUserName(principal.name) as User
        val message = messageService.sendMessage(sender, messageDetails.recipientId, messageDetails.message)
        return ResponseEntity.ok(messageAssembler.toMessageVO(message))
    }

    data class MessageRequest(val recipientId: Long, val message: String)
}