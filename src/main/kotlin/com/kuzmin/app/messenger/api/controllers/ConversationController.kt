package com.kuzmin.app.messenger.api.controllers

import com.kuzmin.app.messenger.api.components.ConversationAssembler
import com.kuzmin.app.messenger.api.heplers.objects.ConversationListVO
import com.kuzmin.app.messenger.api.heplers.objects.ConversationVO
import com.kuzmin.app.messenger.api.models.User
import com.kuzmin.app.messenger.api.repositories.UserRepository
import com.kuzmin.app.messenger.api.services.ConversationServiceImpl
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/conversations")
class ConversationController(val conversationService: ConversationServiceImpl,
                            val conversationAssembler: ConversationAssembler,
                            val userRepository: UserRepository) {
    @GetMapping
    fun list(request: HttpServletRequest): ResponseEntity<ConversationListVO> {
        val user = userRepository.findByUserName(request.userPrincipal.name) as User
        val conversations = conversationService.listUserConversations(user.id)
        return ResponseEntity.ok(conversationAssembler.toConversationListVO(conversations, user.id))
    }

    @GetMapping
    @RequestMapping("/{conversation_id}")
    fun show(@PathVariable(name = "conversation_id") conversationId: Long,
    request: HttpServletRequest): ResponseEntity<ConversationVO> {
        val user = userRepository.findByUserName(request.userPrincipal.name) as User
        val conversationThread = conversationService.retrieveThread(conversationId)
        return ResponseEntity.ok(conversationAssembler.toConversationVO(conversationThread, user.id))
    }
}