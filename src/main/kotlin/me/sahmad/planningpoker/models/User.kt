package me.sahmad.planningpoker.models

import java.util.UUID
import kotlinx.serialization.Serializable
import me.sahmad.planningpoker.serde.UUIDSerializer

@Serializable
data class User(
    val name: String,
    @Serializable(with = UUIDSerializer::class)
    val userId: UUID,
)
