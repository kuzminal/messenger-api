package com.kuzmin.app.messenger.api.repositories

import com.kuzmin.app.messenger.api.models.Conversation
import org.springframework.data.repository.CrudRepository

interface ConversationRepository: CrudRepository<Conversation, Long> {
    fun findBySenderId(senderId: Long): List<Conversation>
    fun findByRecipientId(recipientId: Long): List<Conversation>
    fun findByRecipientIdAndSenderId(recipientId: Long, senderId: Long): Conversation?
}