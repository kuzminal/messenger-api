package com.kuzmin.app.messenger.api.services

import com.kuzmin.app.messenger.api.models.Message
import com.kuzmin.app.messenger.api.models.User

interface MessageService {
    fun sendMessage(sender: User, recipientId: Long, messageText: String): Message
}