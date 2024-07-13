package me.sahmad.planningpoker.models

import java.util.UUID

object SessionStorage {
    val sessions: MutableMap<UUID, Session> = mutableMapOf()
}

data class Session (
    val users: List<User> = listOf(),
    val pokerRounds: List<PokerRound> = listOf()
)

data class PokerRound (
    val roundName: String,
    val votes: List<Vote> = listOf()
)

data class Vote (
    val user: User,
    val vote: Int
)
