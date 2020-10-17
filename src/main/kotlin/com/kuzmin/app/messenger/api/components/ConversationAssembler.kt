package com.kuzmin.app.messenger.api.components

import com.kuzmin.app.messenger.api.heplers.objects.ConversationListVO
import com.kuzmin.app.messenger.api.heplers.objects.ConversationVO
import com.kuzmin.app.messenger.api.heplers.objects.MessageVO
import com.kuzmin.app.messenger.api.models.Conversation
import com.kuzmin.app.messenger.api.services.ConversationService
import com.kuzmin.app.messenger.api.services.ConversationServiceImpl
import org.springframework.stereotype.Component

@Component
class ConversationAssembler(val conversationService: ConversationServiceImpl,
            val messageAssembler: MessageAssembler) {
    fun toConversationVO(conversation: Conversation, userId: Long): ConversationVO {
        val conversationMessages: ArrayList<MessageVO> = ArrayList()
        conversation.messages?.mapTo(conversationMessages) {
            messageAssembler.toMessageVO(it)
        }
        return ConversationVO(conversation.id, conversationService.nameSecondParty(conversation, userId),
        conversationMessages)
    }

    fun toConversationListVO(conversations: List<Conversation>, userId: Long): ConversationListVO {
        val conversationListVO = conversations.map {
            toConversationVO(it, userId)
        }
        return ConversationListVO(conversationListVO)
    }
}