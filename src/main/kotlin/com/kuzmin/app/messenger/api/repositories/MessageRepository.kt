package com.kuzmin.app.messenger.api.repositories

import com.kuzmin.app.messenger.api.models.Conversation
import com.kuzmin.app.messenger.api.models.Message
import org.springframework.data.repository.CrudRepository

interface MessageRepository: CrudRepository<Message, Long> {
    fun findByConversationId(conversationId: Long): List<Message>
}