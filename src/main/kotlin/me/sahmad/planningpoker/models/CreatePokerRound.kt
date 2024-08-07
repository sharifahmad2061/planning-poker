package me.sahmad.planningpoker.models

import java.util.UUID
import kotlinx.serialization.Serializable
import me.sahmad.planningpoker.serde.UUIDSerializer

@Serializable
data class CreatePokerRound(
    @Serializable(with = UUIDSerializer::class)
    val sessionId: UUID,
    val storyName: String
)
