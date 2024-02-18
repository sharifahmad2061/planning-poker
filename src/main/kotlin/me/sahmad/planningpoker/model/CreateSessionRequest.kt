package me.sahmad.planningpoker.model

import kotlinx.serialization.Serializable

@Serializable
data class CreateSessionRequest(
    val name: String
)
