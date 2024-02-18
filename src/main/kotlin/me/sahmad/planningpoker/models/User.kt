package me.sahmad.planningpoker.models

import kotlinx.serialization.Serializable
import me.sahmad.planningpoker.serde.UUIDSerializer
import java.util.UUID

@Serializable
data class User(
    val name: String,
    @Serializable(with = UUIDSerializer::class)
    val userId: UUID,
)
