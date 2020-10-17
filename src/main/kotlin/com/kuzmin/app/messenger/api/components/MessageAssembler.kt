package com.kuzmin.app.messenger.api.components

import com.kuzmin.app.messenger.api.heplers.objects.MessageVO
import com.kuzmin.app.messenger.api.models.Message
import org.springframework.stereotype.Component

@Component
class MessageAssembler {
    fun toMessageVO(messsage: Message): MessageVO {
        return MessageVO(messsage.id, messsage.sender?.id,
                messsage.recipient?.id, messsage.conversation?.id,
                messsage.body, messsage.createAt.toString())
    }
}