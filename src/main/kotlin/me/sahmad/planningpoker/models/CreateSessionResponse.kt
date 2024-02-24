package me.sahmad.planningpoker.models

import kotlinx.serialization.Serializable
import me.sahmad.planningpoker.serde.UUIDSerializer
import java.util.UUID

@Serializable
sealed interface CreateSessionResponse {
    data class Success (@Serializable(with = UUIDSerializer::class) val sessionId: UUID): CreateSessionResponse
    data class Error (val message: String): CreateSessionResponse
}

