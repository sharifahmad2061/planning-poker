package me.sahmad.planningpoker.common.models


import kotlinx.serialization.Serializable
import java.util.UUID
import me.sahmad.planningpoker.common.serde.UUIDSerializer

@Serializable
data class User(
    val name: String,
    @Serializable(with = UUIDSerializer::class)
    val id: UUID
)
