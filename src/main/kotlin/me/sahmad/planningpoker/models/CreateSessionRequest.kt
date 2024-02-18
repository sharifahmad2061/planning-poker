package me.sahmad.planningpoker.models

import kotlinx.serialization.Serializable

@Serializable
data class CreateSessionRequest(
    val name: String
)

