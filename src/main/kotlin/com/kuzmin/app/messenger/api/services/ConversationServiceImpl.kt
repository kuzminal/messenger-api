package com.kuzmin.app.messenger.api.services

import com.kuzmin.app.messenger.api.exceptions.ConversationIdInvalidException
import com.kuzmin.app.messenger.api.models.Conversation
import com.kuzmin.app.messenger.api.models.User
import com.kuzmin.app.messenger.api.repositories.ConversationRepository
import org.springframework.stereotype.Component

@Component
class ConversationServiceImpl(val repository: ConversationRepository) : ConversationService {
    override fun createConversation(userA: User, userB: User): Conversation {
        val conversation = Conversation(userA, userB)
        repository.save(conversation)
        return conversation
    }

    override fun conversationExists(userA: User, userB: User): Boolean {
        return if (repository.findByRecipientIdAndSenderId(userA.id, userB.id) != null)
            true
        else repository.findByRecipientIdAndSenderId(userA.id, userB.id) != null
    }

    override fun getConversation(userA: User, userB: User): Conversation? {
        return when {
            repository.findByRecipientIdAndSenderId(userA.id, userB.id) != null ->
                repository.findByRecipientIdAndSenderId(userA.id, userB.id)
            repository.findByRecipientIdAndSenderId(userB.id, userA.id) != null ->
                repository.findByRecipientIdAndSenderId(userB.id, userA.id)
            else -> null
        }
    }

    override fun retrieveThread(conversationId: Long): Conversation {
        val conversation = repository.findById(conversationId)

        if (conversation.isPresent) {
            return conversation.get()
        }
        throw ConversationIdInvalidException("Invalid conversation id '$conversationId'")
    }

    override fun listUserConversations(userId: Long): List<Conversation> {
        val conversationList: ArrayList<Conversation> = ArrayList()
        conversationList.addAll(repository.findBySenderId(userId))
        conversationList.addAll(repository.findByRecipientId(userId))

        return conversationList
    }

    override fun nameSecondParty(conversation: Conversation, userId: Long): String {
        return if (conversation.sender?.id == userId) {
            conversation.recipient?.userName as String
        } else {
            conversation.sender?.userName as String
        }
    }

}