package me.sahmad.planningpoker.models

import java.util.UUID
import kotlinx.serialization.Serializable
import me.sahmad.planningpoker.serde.UUIDSerializer

data class JoinSessionResponse(
    @Serializable(with = UUIDSerializer::class)
    val userId: UUID
)
