package me.sahmad.planningpoker.models

import io.ktor.server.websocket.WebSocketServerSession
import java.util.Collections
import java.util.UUID

object SessionStorage {
    val sessions: MutableMap<UUID, Session> = mutableMapOf()
}

data class Session (
    val users: List<User> = emptyList(),
    val completedPokerRounds: List<PokerRound> = emptyList(),
    val webSocketSessions: MutableList<WebSocketServerSession> = mutableListOf()
)

data class PokerRound (
    val storyName: String,
    val votes: List<Vote> = listOf()
)

data class Vote (
    val user: User,
    val estimate: Int
)
