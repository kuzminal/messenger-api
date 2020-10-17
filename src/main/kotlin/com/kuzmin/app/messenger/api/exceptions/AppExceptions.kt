package com.kuzmin.app.messenger.api.exceptions

import com.kuzmin.app.messenger.api.models.Message
import java.lang.RuntimeException

class UserNameUnavailableException(override val message: String) : RuntimeException()

class InvalidUserIdException(override val message: String) : RuntimeException()

class MessageEmptyException(override val message: String = "A message cannot be empty.") : RuntimeException()

class MessageRecipientInvalidException(override val message: String) : RuntimeException()

class ConversationIdInvalidException(override val message: String) : RuntimeException()

class UserDeactivatedException(override val message: String) : RuntimeException()

class UserStatusEmptyException(override val message: String = "A user's status cannot be empty") : RuntimeException()