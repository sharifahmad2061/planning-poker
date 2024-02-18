package me.sahmad.planningpoker.model

import java.util.UUID
import kotlinx.serialization.Serializable
import me.sahmad.planningpoker.serde.UUIDSerializer

@Serializable
data class CreateSessionResponse(
    @Serializable(with = UUIDSerializer::class)
    val sessionId: UUID,
)
