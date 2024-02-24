package me.sahmad.planningpoker.models

import java.util.UUID
import kotlinx.serialization.Serializable
import me.sahmad.planningpoker.serde.UUIDSerializer

@Serializable
sealed interface LoginResponse {

    data class Error(
        val message: String
    )
    @Serializable
    data class Success(
        @Serializable(with = UUIDSerializer::class) val sessionId: UUID,
        @Serializable(with = UUIDSerializer::class) val userId: UUID
    )

}
